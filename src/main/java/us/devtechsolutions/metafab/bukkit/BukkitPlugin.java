package us.devtechsolutions.metafab.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.api.CollectionAPI;
import us.devtechsolutions.metafab.api.ItemAPI;
import us.devtechsolutions.metafab.api.MetaFabAPI;
import us.devtechsolutions.metafab.bukkit.command.BukkitAuthCommand;
import us.devtechsolutions.metafab.bukkit.command.RedeemableCommand;
import us.devtechsolutions.metafab.bukkit.config.ConfigManager;
import us.devtechsolutions.metafab.bukkit.inventory.ContainerHandler;
import us.devtechsolutions.metafab.bukkit.listener.PlayerJoinListener;
import us.devtechsolutions.metafab.http.PluginSocketClient;
import us.devtechsolutions.metafab.manager.*;
import us.devtechsolutions.metafab.model.collection.Collection;
import us.devtechsolutions.metafab.model.contract.Contract;
import us.devtechsolutions.metafab.model.currency.Currency;
import us.devtechsolutions.metafab.model.ecosystem.EcoSystem;
import us.devtechsolutions.metafab.model.game.Game;
import us.devtechsolutions.metafab.player.PlayerManager;
import us.devtechsolutions.metafab.provider.PluginProvider;
import us.devtechsolutions.metafab.util.EndpointUtil;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class BukkitPlugin extends JavaPlugin implements PluginProvider {

	private PlayerManager playerManager;
	private PluginSocketClient socketServer;
	private UUID serverId;

	@Override
	public void onEnable() {
		this.serverId = UUID.randomUUID();

		super.saveDefaultConfig();

		// Tell the provider bukkit is running this plugin.
		this.initProvider(this);

		// Fetch the ecosystem & game (from the configured id's)
		final EcoSystem ecoSystem = EndpointUtil.fetchEcoSystem(this.ecosystemId());
		final Game game = EndpointUtil.fetchGame(this.gameId());
		final List<Currency> currencies = EndpointUtil.fetchCurrencies(game.publishedKey());
		final List<Contract> contracts = EndpointUtil.fetchContracts(game.publishedKey());
		final List<Collection> collections = EndpointUtil.fetchCollections(game.publishedKey());

		// Initialise Managers
		final ConfigManager configManager = new ConfigManager(this);
		this.playerManager = new PlayerManager();
		final AuthenticationManager authenticationManager = new AuthenticationManager();
		final CollectionManager collectionManager = new CollectionManager(collections);
		final ItemManager itemManager = new ItemManager();
		collections.forEach(c -> itemManager.addItems(c.id(), EndpointUtil.fetchItems(c.id().toString())));
		final ContractManager contractManager = new ContractManager(contracts);
		final CurrencyManager currencyManager = new CurrencyManager(currencies);

		// Register Commands
		Objects.requireNonNull(this.getCommand("metafab")).setExecutor(new BukkitAuthCommand(authenticationManager, playerManager));
		Objects.requireNonNull(this.getCommand("redeem")).setExecutor(new RedeemableCommand(this, configManager));

		// Register Listeners
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this, configManager, this.playerManager), this);
		Bukkit.getPluginManager().registerEvents(new ContainerHandler(), this);

		new MetaFabAPI(authenticationManager, this.playerManager, collectionManager, itemManager, contractManager, currencyManager, ecoSystem, game);

		CollectionAPI.getCollections().forEach(c -> System.out.printf("%s - %s%n", c.name(), c.id()));
		ItemAPI.getItems().forEach(item -> System.out.printf("%s - %s%n", item.id(), item.name()));

		this.socketServer = new PluginSocketClient(this, this.playerManager);
	}

	@Override
	public void onDisable() {
		this.playerManager.invalidate();
		this.socketServer.close();
	}

	@Override
	public @NotNull UUID serverId() {
		return this.serverId;
	}

	@Override
	public @NotNull String ecosystemId() {
		final String path = "ecosystem_id";
		if (!super.getConfig().contains(path)) {
			throw new RuntimeException("You haven't configured %s in the config.".formatted(path));
		}

		return super.getConfig().getString(path, "");
	}

	@Override
	public boolean hasEcosystem() {
		return !ecosystemId().isEmpty();
	}

	@Override
	public @NotNull String gameId() {
		final String path = "game_id";
		if (!super.getConfig().contains(path)) {
			throw new RuntimeException("You haven't configured %s in the config.".formatted(path));
		}

		return super.getConfig().getString(path, "");
	}

	@Override
	public void runAsync(@NotNull Runnable runnable) {
		Bukkit.getScheduler().runTaskAsynchronously(this, runnable);
	}

	@Override
	public void runSync(@NotNull Runnable runnable) {
		Bukkit.getScheduler().runTask(this, runnable);
	}
}
