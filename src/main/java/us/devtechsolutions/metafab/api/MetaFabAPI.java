package us.devtechsolutions.metafab.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.manager.*;
import us.devtechsolutions.metafab.model.ecosystem.EcoSystem;
import us.devtechsolutions.metafab.model.game.Game;
import us.devtechsolutions.metafab.player.PlayerManager;

import java.util.Objects;

/**
 * @author LBuke (Teddeh)
 */
@ApiStatus.NonExtendable
public final class MetaFabAPI {
	private static MetaFabAPI singleton;

	private final EcoSystem ecoSystem;
	private final Game game;

	/**
	 * This constructor is NOT designed to be instantiated outside MetaFab Authentication Plugin.
	 * Attempting to instantiate this constructor will throw a runtime exception.
	 */
	@ApiStatus.Internal
	public MetaFabAPI(@NotNull AuthenticationManager authenticationManager, @NotNull PlayerManager playerManager,
					  @NotNull CollectionManager collectionManager, @NotNull ItemManager itemManager,
					  @NotNull ContractManager contractManager, @NotNull CurrencyManager currencyManager,
	                  @Nullable EcoSystem ecoSystem, @NotNull Game game) {
		if (!Objects.isNull(singleton))
			throw new RuntimeException("MetaFabAPI is not intended to be instantiated.");

		singleton = this;

		this.ecoSystem = ecoSystem;
		this.game = game;

		new UserAPI(authenticationManager, playerManager);
		new CollectionAPI(collectionManager);
		new ItemAPI(itemManager);
		new ContractAPI(contractManager);
		new CurrencyAPI(currencyManager);
	}

	/**
	 * Get the EcoSystem.
	 *
	 * @return the EcoSystem
	 */
	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static @Nullable EcoSystem getEcoSystem() {
		return singleton.ecoSystem;
	}

	/**
	 * Get the Game.
	 *
	 * @return the Game
	 */
	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static @NotNull Game getGame() {
		return singleton.game;
	}
}
