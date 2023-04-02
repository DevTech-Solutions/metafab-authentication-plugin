package us.devtechsolutions.metafab.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.api.MetaFabAPI;
import us.devtechsolutions.metafab.authentication.AuthenticationManager;
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

import static spark.Spark.port;

/**
 * @author LBuke (Teddeh)
 */
public final class BukkitPlugin extends JavaPlugin implements PluginProvider {

	@Override
	public void onEnable() {
		super.saveDefaultConfig();

		port(this.preferredPort());

		// Initialise Managers
		final PlayerManager playerManager = new PlayerManager();
		final AuthenticationManager authenticationManager = new AuthenticationManager(playerManager);

		// Register Commands
		Objects.requireNonNull(this.getCommand("metafab")).setExecutor(new BukkitAuthCommand(authenticationManager));

		// Tell the provider bukkit is running this plugin.
		this.initProvider(this);

		// Fetch the ecosystem & game (from the configured id's)
		final EcoSystem ecoSystem = EndpointUtil.fetchEcoSystem(this.ecosystemId());
		final Game game = EndpointUtil.fetchGame(this.gameId());
		final List<Currency> currencies = EndpointUtil.fetchCurrencies(game.publishedKey());
		final List<Contract> contracts = EndpointUtil.fetchContracts(game.publishedKey());
		new MetaFabAPI(authenticationManager, playerManager, ecoSystem, game, currencies, contracts);

		// Run the web server
//		Bukkit.getScheduler().runTaskAsynchronously(this, () -> );
	}

	@Override
	public void onDisable() {
	}

	private short preferredPort() {
		final String path = "web-server.port";
		if (!super.getConfig().contains(path)) {
			throw new RuntimeException("You haven't configured %s in the config.".formatted(path));
		}

		return (short) super.getConfig().getInt(path);
	}

	@Override
	public @NotNull String ecosystemId() {
		final String path = "ecosystem.id";
		if (!super.getConfig().contains(path)) {
			throw new RuntimeException("You haven't configured %s in the config.".formatted(path));
		}

		return super.getConfig().getString(path, "");
	}

	@Override
	public @NotNull String ecosystemSecret() {
		final String path = "ecosystem.secret";
		if (!super.getConfig().contains(path)) {
			throw new RuntimeException("You haven't configured %s in the config.".formatted(path));
		}

		return super.getConfig().getString(path, "");
	}

	@Override
	public @NotNull String gameId() {
		final String path = "game.id";
		if (!super.getConfig().contains(path)) {
			throw new RuntimeException("You haven't configured %s in the config.".formatted(path));
		}

		return super.getConfig().getString(path, "");
	}

	@Override
	public @NotNull String gameSecret() {
		final String path = "game.secret";
		if (!super.getConfig().contains(path)) {
			throw new RuntimeException("You haven't configured %s in the config.".formatted(path));
		}

		return super.getConfig().getString(path, "");
	}

	@Override
	public @NotNull String redirect() {
		final String path = "web-server.redirect";
		if (!super.getConfig().contains(path)) {
			throw new RuntimeException("You haven't configured %s in the config.".formatted(path));
		}

		final String redirect = super.getConfig().getString("web-server.redirect");
		assert redirect != null;
		if (redirect.endsWith("/")) {
			return redirect.substring(0, redirect.length() - 1);
		}
		return redirect;
	}

	@Override
	public void sendPlayerMessage(@NotNull UUID playerUniqueId, @NotNull String message) {
		final Player player = Bukkit.getPlayer(playerUniqueId);
		if (Objects.isNull(player) || !player.isOnline())
			return;

		player.sendMessage(message);
	}

	@Override
	public boolean isPlayerOnline(@NotNull UUID playerUniqueId) {
		final Player player = Bukkit.getPlayer(playerUniqueId);
		return !Objects.isNull(player) && player.isOnline();
	}

	@Override
	public void runAsync(Runnable runnable) {
		Bukkit.getScheduler().runTaskAsynchronously(this, runnable);
	}
}
