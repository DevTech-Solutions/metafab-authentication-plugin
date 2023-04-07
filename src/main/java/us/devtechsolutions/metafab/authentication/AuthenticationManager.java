package us.devtechsolutions.metafab.authentication;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.constant.SuccessHtmlConstant;
import us.devtechsolutions.metafab.model.player.BaseUser;
import us.devtechsolutions.metafab.player.PlayerManager;
import us.devtechsolutions.metafab.provider.PluginProvider;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static spark.Spark.get;

/**
 * @author LBuke (Teddeh)
 */
public final class AuthenticationManager {
	private final Map<UUID, UUID> playerCodeMap = ExpiringMap.builder()
			.expiration(5, TimeUnit.MINUTES)
			.expirationPolicy(ExpirationPolicy.CREATED)
			.build();

	private final PlayerManager playerManager;

	public AuthenticationManager(@NotNull PlayerManager playerManager) {
		this.playerManager = playerManager;

		// Listen to get requests at '<domain>/metafab/success'
		get("/metafab/success", "application/json", (req, res) -> {
			final String code = req.queryParams("code");
			final String accessToken = req.queryParams("accessToken");
			final String playerId = req.queryParams("id");
			final String walletDecryptKey = req.queryParams("walletDecryptKey");

			// The code in this case is our custom url parameter we passed in.
			if (Objects.isNull(code) || code.isEmpty()) {
				// FIXME: Something went wrong, the code was null.
				return SuccessHtmlConstant.HTML;
			}

			final UUID playerUniqueId = this.getPlayerFromCode(UUID.fromString(code));
			if (Objects.isNull(playerUniqueId)) {
				// FIXME: Something went wrong, player unique id is null.
				return SuccessHtmlConstant.HTML;
			}

			final PluginProvider provider = PluginProvider.of();
			if (!provider.isPlayerOnline(playerUniqueId)) {
				// FIXME: Player is no longer online.
				return SuccessHtmlConstant.HTML;
			}

			// Remove the 'player -> code' cache
			this.playerCodeMap.remove(playerUniqueId);

			provider.runAsync(() -> {
				final BaseUser player = (BaseUser) this.playerManager.fetchUser(playerUniqueId, playerId);
				player.accessToken(accessToken);
				player.walletDecryptKey(walletDecryptKey);

				provider.sendPlayerMessage(playerUniqueId, "Hi, %s!".formatted(player.username()));
				provider.sendPlayerMessage(playerUniqueId, "You've successfully verified with MetaFab!");
				System.out.println(player);
			});

			return SuccessHtmlConstant.HTML;
		});
	}

	/**
	 * Generate an OAuth link containing the unique code for the player.
	 *
	 * @param uniqueId - The player unique id to generate the link for
	 * @return The generated auth link
	 */
	public @NotNull String generateLink(@NotNull UUID uniqueId) {
		this.playerCodeMap.remove(uniqueId);

		// generate and cache the code.
		final UUID code = UUID.randomUUID();
		this.playerCodeMap.put(uniqueId, code);

		// Get the provider (software) running this plugin.
		final PluginProvider provider = PluginProvider.of();

		// Build the redirect uri.
		final Map<String, String> requestParams = new HashMap<>();
		requestParams.put("data", "%s!%s".formatted(code, provider.redirect() + "/metafab/success"));
		final String encodedURL = requestParams.keySet().stream()
				.map(key -> key + "=" + URLEncoder.encode(requestParams.get(key), StandardCharsets.UTF_8))
				.collect(Collectors.joining("&", "https://smp-store-3df8b.web.app/auth/?", ""));
		requestParams.clear();

		return ("https://connect.trymetafab.com/" +
				"?flow=forceConnect" +
				"&chain=MATIC" +
				"&game=%s" +
				"&redirectUri=%s"
		).formatted(provider.gameId(), encodedURL);
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
