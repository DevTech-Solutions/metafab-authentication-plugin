package us.devtechsolutions.metafab.manager;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.model.Code;
import us.devtechsolutions.metafab.model.player.BaseUser;
import us.devtechsolutions.metafab.player.PlayerManager;
import us.devtechsolutions.metafab.provider.PluginProvider;
import us.devtechsolutions.metafab.util.Callback;
import us.devtechsolutions.metafab.util.EndpointUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author LBuke (Teddeh)
 */
public final class AuthenticationManager {
	private final Map<UUID, UUID> playerCodeMap = ExpiringMap.builder()
			.expiration(5, TimeUnit.MINUTES)
			.expirationPolicy(ExpirationPolicy.CREATED)
			.build();

	/**
	 * Generate an OAuth link containing the unique code for the player.
	 *
	 * @param uniqueId - The player unique id to generate the link for
	 * @return The generated auth link
	 */
	public void generateLink(@NotNull UUID uniqueId, @NotNull String name, @NotNull Callback<String> callback) {
		this.playerCodeMap.remove(uniqueId);

		// Get the provider (software) running this plugin.
		final PluginProvider provider = PluginProvider.of();

		provider.runAsync(() -> {
			final Code code = EndpointUtil.fetchCode(uniqueId, name, provider.serverId().toString(), provider.gameId());

			this.playerCodeMap.put(uniqueId, code.getCode());

			// Build the redirect uri.
			final Map<String, String> requestParams = new HashMap<>();
			requestParams.put("data", "%s!%s".formatted(
					code.getCode().toString(),
					"unused"
			));
			final String encodedURL = requestParams.keySet().stream()
					.map(key -> key + "=" + URLEncoder.encode(requestParams.get(key), StandardCharsets.UTF_8))
					.collect(Collectors.joining("&", "https://cubecolony.net/auth/?", ""));
			requestParams.clear();

			if (provider.hasEcosystem()) {
				callback.call("https://accounts.trymetafab.com/?chain=MATIC&ecosystem=%s&game=%s&redirectUri=%s"
						.formatted(provider.ecosystemId(), provider.gameId(), encodedURL));
				return;
			}

			callback.call("https://accounts.trymetafab.com/?chain=MATIC&game=%s&redirectUri=%s"
					.formatted(provider.gameId(), encodedURL));
		});
	}

	public @Nullable UUID getCodeFromPlayer(UUID uniqueId) {
		return this.playerCodeMap.getOrDefault(uniqueId, null);
	}

	public @Nullable UUID getPlayerFromCode(UUID code) {
		for (Map.Entry<UUID, UUID> entry : this.playerCodeMap.entrySet()) {
			if (!entry.getValue().equals(code))
				continue;

			return entry.getKey();
		}

		return null;
	}
}
