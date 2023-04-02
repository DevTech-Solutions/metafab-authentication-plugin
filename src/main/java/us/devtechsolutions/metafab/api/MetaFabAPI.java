package us.devtechsolutions.metafab.api;

import org.jetbrains.annotations.*;
import us.devtechsolutions.metafab.authentication.AuthenticationManager;
import us.devtechsolutions.metafab.model.contract.Contract;
import us.devtechsolutions.metafab.model.currency.Currency;
import us.devtechsolutions.metafab.model.ecosystem.EcoSystem;
import us.devtechsolutions.metafab.model.game.Game;
import us.devtechsolutions.metafab.model.player.User;
import us.devtechsolutions.metafab.player.PlayerManager;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
@ApiStatus.NonExtendable
public final class MetaFabAPI {
	private static MetaFabAPI singleton;

	private final AuthenticationManager authenticationManager;
	private final PlayerManager playerManager;
	private final EcoSystem ecoSystem;
	private final Game game;
	private final List<Currency> currencies;
	private final List<Contract> contracts;

	/**
	 * This constructor is NOT designed to be instantiated outside MetaFab Authentication Plugin.
	 * Attempting to instantiate this constructor will throw a runtime exception.
	 */
	@ApiStatus.Internal
	public MetaFabAPI(@NotNull AuthenticationManager authenticationManager, @NotNull PlayerManager playerManager,
	                  @NotNull EcoSystem ecoSystem, @NotNull Game game, @NotNull List<Currency> currencies,
	                  @NotNull List<Contract> contracts) {
		if (!Objects.isNull(singleton))
			throw new RuntimeException("MetaFabAPI is not intended to be instantiated.");

		singleton = this;

		this.authenticationManager = authenticationManager;
		this.playerManager = playerManager;
		this.ecoSystem = ecoSystem;
		this.game = game;
		this.currencies = currencies;
		this.contracts = contracts;
	}

	/**
	 * Get the EcoSystem.
	 *
	 * @return the EcoSystem
	 */
	@NonBlocking
	@ApiStatus.AvailableSince("1.0")
	public static @NotNull EcoSystem getEcoSystem() {
		return singleton.ecoSystem;
	}

	/**
	 * Get the Game.
	 *
	 * @return the Game
	 */
	@NonBlocking
	@ApiStatus.AvailableSince("1.0")
	public static @NotNull Game getGame() {
		return singleton.game;
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

	/**
	 * Get the MetaFabPlayer object from the Minecraft player unique id.
	 *
	 * @param playerUniqueId - Minecraft player unique id
	 * @return the MetaFabPlayer object
	 */
	@NonBlocking
	@ApiStatus.AvailableSince("1.0")
	public static @NotNull User playerById(@NotNull UUID playerUniqueId) {
		return null;
	}

	/**
	 * Get the MetaFabPlayer object from the MetaFab user id.
	 *
	 * @param metaFabUserId - MetaFab user id
	 * @return the MetaFabPlayer object
	 */
	@NonBlocking
	@ApiStatus.AvailableSince("1.0")
	public static @NotNull User playerByUniqueId(@NotNull UUID metaFabUserId) {
		return null;
	}
}
