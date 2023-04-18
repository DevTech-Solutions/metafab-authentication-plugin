package us.devtechsolutions.metafab.bukkit.inventory.impl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.api.CollectionAPI;
import us.devtechsolutions.metafab.api.ItemAPI;
import us.devtechsolutions.metafab.api.UserAPI;
import us.devtechsolutions.metafab.bukkit.config.ConfigManager;
import us.devtechsolutions.metafab.bukkit.inventory.BaseContainer;
import us.devtechsolutions.metafab.bukkit.inventory.ContainerFlag;
import us.devtechsolutions.metafab.bukkit.inventory.button.ClickableItem;
import us.devtechsolutions.metafab.bukkit.item.ItemBuilder;
import us.devtechsolutions.metafab.model.collection.Collection;
import us.devtechsolutions.metafab.model.item.Item;
import us.devtechsolutions.metafab.model.player.User;
import us.devtechsolutions.metafab.model.transaction.Transaction;
import us.devtechsolutions.metafab.model.wallet.Wallet;
import us.devtechsolutions.metafab.provider.PluginProvider;
import us.devtechsolutions.metafab.util.C;
import us.devtechsolutions.metafab.util.StringUtil;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author LBuke (Teddeh)
 */
public final class RedeemInventory extends BaseContainer {

	/**
	 * Construct a new Menu.
	 */
	public RedeemInventory(@NotNull JavaPlugin plugin, @NotNull ConfigManager configManager, @NotNull Player player) {
		super(6, StringUtil.getCenteredMenuText("Redeemable Items"), ContainerFlag.CLOSE_ON_NULL_CLICK);

		final ItemBuilder gray = ItemBuilder.of(Material.LIGHT_GRAY_STAINED_GLASS_PANE).model(0).name(C.RESET);
		setAll(gray.build());

		final User user = UserAPI.getMetaFabUser(player.getUniqueId());
		if (Objects.isNull(user))
			return;

		System.out.println("player.wallet = " + user.wallet().address());
		System.out.println("player.custodialWallet = " + user.custodialWallet().address());

		List<Wallet> wallets = List.of(user.wallet(), user.custodialWallet());

		PluginProvider provider = PluginProvider.of();
		provider.runAsync(() -> {
			int index = 0;

			for (Collection collection : CollectionAPI.getCollections()) {
				final Set<Item> items = ItemAPI.getItems(collection.id());
				if (Objects.isNull(items))
					continue;

				for (Item item : items) {
					for (Wallet wallet : wallets) {
						final int balance = ItemAPI.getItemBalance(collection.id().toString(), item.id(), wallet.address());

						if (balance <= 0)
							continue;

						ConfigManager.ConfigItem configItem = configManager.getItem(collection.id(), item.id());
						if (Objects.isNull(configItem))
							continue;

						if (configItem.isRedeemed())
							continue;

						if (!configItem.isRedeemable())
							continue;

						ItemBuilder itemBuilder = ItemBuilder.of(configItem.getItem().clone());
						itemBuilder.amount(Math.min(balance, 64));
						itemBuilder.lore("", "&6➢ &eClick to redeem!");

						addItem(new ClickableItem(getInsideSlots()[index++], itemBuilder.build(), (clicker, clickType) -> {
							provider.runSync(() -> {
								final ConfirmationContainer confirmationContainer = new ConfirmationContainer((confirmed) -> {
									clicker.closeInventory();

									if (!confirmed)
										return;

									configItem.setRedeemed(true);
									clicker.sendMessage(C.translate("&eYou successfully redeemed &6%s".formatted(item.name())));

									provider.runAsync(() -> {
										ItemAPI.burnCollectionItem(
												collection.id().toString(),
												item.id(),
												1,
												user.accessToken(),
												user.walletDecryptKey()
										);
									});

									for (String command : configItem.getCommands()) {
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("\\{PLAYER}", clicker.getName()));
									}
								});
								confirmationContainer.open(clicker);
							});
						}));
					}
				}
			}
		});
	}
}
