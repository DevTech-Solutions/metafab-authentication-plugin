package us.devtechsolutions.metafab.player;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.model.player.User;
import us.devtechsolutions.metafab.util.EndpointUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class PlayerManager {
	private static final Map<UUID, PlayerData> playerMap = new HashMap<>();

	public PlayerManager() {}

	public void invalidate() {
		playerMap.clear();
	}

	@Blocking
	public @NotNull User fetchUser(@NotNull UUID playerUniqueId, @NotNull String metaFabPlayerId) {
		final PlayerData data = playerMap.getOrDefault(playerUniqueId, new PlayerData(playerUniqueId));
		data.metaFabId(metaFabPlayerId);

		final User user = EndpointUtil.fetchUser(metaFabPlayerId);
		data.metaFabPlayer(user);

		playerMap.put(playerUniqueId, data);
		return user;
	}

	public @Nullable User getMetaFabUser(@NotNull UUID playerUniqueId) {
		final PlayerData data = playerMap.get(playerUniqueId);
		return Objects.isNull(data) ? null : data.metaFabPlayer();
	}
}
