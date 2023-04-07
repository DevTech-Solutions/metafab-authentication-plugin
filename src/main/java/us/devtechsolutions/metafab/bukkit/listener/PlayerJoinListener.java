package us.devtechsolutions.metafab.bukkit.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.api.CollectionAPI;
import us.devtechsolutions.metafab.api.ItemAPI;
import us.devtechsolutions.metafab.api.UserAPI;
import us.devtechsolutions.metafab.bukkit.config.ConfigManager;
import us.devtechsolutions.metafab.model.collection.Collection;
import us.devtechsolutions.metafab.model.item.Item;
import us.devtechsolutions.metafab.model.player.User;
import us.devtechsolutions.metafab.model.wallet.Wallet;
import us.devtechsolutions.metafab.player.PlayerManager;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author LBuke (Teddeh)
 */
public final class PlayerJoinListener implements Listener {

	private final JavaPlugin plugin;
	private final ConfigManager configManager;
	private final PlayerManager playerManager;

	public PlayerJoinListener(@NotNull JavaPlugin plugin,
	                          @NotNull ConfigManager configManager,
	                          @NotNull PlayerManager playerManager) {
		this.plugin = plugin;
		this.configManager = configManager;
		this.playerManager = playerManager;
	}

	@EventHandler
	public void onPlayerConnect(AsyncPlayerPreLoginEvent event) {
		User user = UserAPI.getMetaFabUser(event.getUniqueId());
		if (Objects.isNull(user)) {
			//TODO: Contact backend database for account id.
			boolean exists = false;
			if (exists) {
				user = this.playerManager.fetchUser(event.getUniqueId(), "");
			} else {
				// Hasn't verified.
				return;
			}
		}

		final List<Wallet> wallets = List.of(user.wallet(), user.custodialWallet());

		for (final Collection collection : CollectionAPI.getCollections()) {
			final Set<Item> items = ItemAPI.getItems(collection.id());
			if (Objects.isNull(items))
				continue;

			for (final Item item : items) {
				final int balance = ItemAPI.getItemBalance(collection.id().toString(), item.id(), user.wallet().address());

				final ConfigManager.ConfigItem configItem = configManager.getItem(collection.id(), item.id());
				if (Objects.isNull(configItem))
					continue;

				if (configItem.isRedeemable())
					continue;

				Bukkit.getScheduler().runTask(this.plugin, () -> {
					if (balance > 0) {
						for (String command : configItem.getExistsInWalletCommands()) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("\\{PLAYER}", event.getName()));
						}
					} else {
						for (String command : configItem.getRemovedFromWalletCommands()) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("\\{PLAYER}", event.getName()));
						}
					}
				});
			}
		}
	}
}
