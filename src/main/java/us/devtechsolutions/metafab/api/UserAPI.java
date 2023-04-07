package us.devtechsolutions.metafab.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;
import us.devtechsolutions.metafab.authentication.AuthenticationManager;
import us.devtechsolutions.metafab.model.player.User;
import us.devtechsolutions.metafab.player.PlayerManager;

import java.util.Objects;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class UserAPI {
	private static UserAPI singleton;

	private final AuthenticationManager authenticationManager;
	private final PlayerManager playerManager;

	UserAPI(@NotNull AuthenticationManager authenticationManager, @NotNull PlayerManager playerManager) {
		singleton = this;

		this.authenticationManager = authenticationManager;
		this.playerManager = playerManager;
	}

	@NonBlocking
	@ApiStatus.AvailableSince("1.0")
	public static boolean hasValidAuthenticationCode(@NotNull UUID playerUniqueId) {
		return !Objects.isNull(getAuthenticationCode(playerUniqueId));
	}

	@NonBlocking
	@ApiStatus.AvailableSince("1.0")
	public static @UnknownNullability UUID getAuthenticationCode(@NotNull UUID playerUniqueId) {
		return singleton.authenticationManager.getCodeFromPlayer(playerUniqueId);
	}

	@NonBlocking
	@ApiStatus.AvailableSince("1.0")
	public static boolean isAuthenticated(@NotNull UUID playerUniqueId) {
		return !Objects.isNull(getMetaFabUser(playerUniqueId));
	}

	@NonBlocking
	@ApiStatus.AvailableSince("1.0")
	public static @UnknownNullability User getMetaFabUser(@NotNull UUID playerUniqueId) {
		return singleton.playerManager.getMetaFabUser(playerUniqueId);
	}
}
