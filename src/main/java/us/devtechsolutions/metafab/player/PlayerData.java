package us.devtechsolutions.metafab.player;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.player.User;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
final class PlayerData {
	private UUID playerUniqueId;
	private String metaFabId;
	private User metaFabUser;

	public PlayerData(@NotNull UUID playerUniqueId, @NotNull String metaFabId, @NotNull User metaFabUser) {
		this.playerUniqueId = playerUniqueId;
		this.metaFabId = metaFabId;
		this.metaFabUser = metaFabUser;
	}

	public PlayerData(@NotNull UUID playerUniqueId) {
		this.playerUniqueId = playerUniqueId;
	}

	public @NotNull UUID playerUniqueId() {
		return playerUniqueId;
	}

	public void playerUniqueId(@NotNull UUID playerUniqueId) {
		this.playerUniqueId = playerUniqueId;
	}

	public @NotNull String metaFabId() {
		return metaFabId;
	}

	public void metaFabId(@NotNull String metaFabId) {
		this.metaFabId = metaFabId;
	}

	public @NotNull User metaFabPlayer() {
		return metaFabUser;
	}

	public void metaFabPlayer(@NotNull User metaFabUser) {
		this.metaFabUser = metaFabUser;
	}
}
