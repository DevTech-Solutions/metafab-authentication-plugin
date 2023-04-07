package us.devtechsolutions.metafab.bukkit.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.bukkit.item.ItemBuilder;
import us.devtechsolutions.metafab.util.C;

import java.util.*;

/**
 * @author LBuke (Teddeh)
 */
public final class ConfigManager {
	private final Map<UUID, List<ConfigItem>> itemMap = new HashMap<>();

	public ConfigManager(@NotNull JavaPlugin plugin) {
		final FileConfiguration config = plugin.getConfig();
		for (String collectionId : config.getConfigurationSection("items").getKeys(false)) {
			final UUID collectionUuid = UUID.fromString(collectionId);
			List<ConfigItem> list = new ArrayList<>();

			for (String itemId : config.getConfigurationSection("items.%s".formatted(collectionId)).getKeys(false)) {
				final Material material = Material.getMaterial(config.getString("items.%s.%s.material".formatted(collectionId, itemId)));
				final int model = config.getInt("items.%s.%s.model".formatted(collectionId, itemId));
				final String name = config.getString("items.%s.%s.name".formatted(collectionId, itemId));
				final boolean redeemable = config.getBoolean("items.%s.%s.redeemable".formatted(collectionId, itemId));
				final List<String> lore = config.getStringList("items.%s.%s.lore".formatted(collectionId, itemId));
				final List<String> commands = config.getStringList("items.%s.%s.redeem-command".formatted(collectionId, itemId));
				final List<String> existsInWalletCommands = config.getStringList("items.%s.%s.exists-in-wallet-commands".formatted(collectionId, itemId));
				final List<String> removedFromWalletCommands = config.getStringList("items.%s.%s.removed-from-wallet-commands".formatted(collectionId, itemId));

				final ItemStack itemStack = ItemBuilder.of(material)
						.model(model)
						.name(name)
						.lore(lore.stream().map(C::translate).toList())
						.build();

				final ConfigItem configItem = new ConfigItem(
						Long.parseLong(itemId),
						itemStack,
						redeemable,
						commands,
						existsInWalletCommands,
						removedFromWalletCommands
				);
				list.add(configItem);
			}

			this.itemMap.put(collectionUuid, list);
		}
	}

	public @Nullable ConfigItem getItem(@NotNull UUID collectionId, long itemId) {
		final List<ConfigItem> configItems = this.itemMap.get(collectionId);
		if (Objects.isNull(configItems))
			return null;

		return configItems.stream().filter(i -> i.id == itemId).findFirst().orElse(null);
	}

	public static class ConfigItem {
		private final long id;
		private final ItemStack item;
		private final boolean redeemable;
		private final List<String> commands;
		private final List<String> existsInWalletCommands;
		private final List<String> removedFromWalletCommands;

		private boolean redeemed;

		public ConfigItem(long id, ItemStack item, boolean redeemable, List<String> commands,
		                  List<String> existsInWalletCommands, List<String> removedFromWalletCommands) {
			this.id = id;
			this.item = item;
			this.redeemable = redeemable;
			this.commands = commands;
			this.existsInWalletCommands = existsInWalletCommands;
			this.removedFromWalletCommands = removedFromWalletCommands;
		}

		public long getId() {
			return id;
		}

		public @NotNull ItemStack getItem() {
			return item;
		}

		public boolean isRedeemable() {
			return redeemable;
		}

		public List<String> getCommands() {
			return commands;
		}

		public List<String> getExistsInWalletCommands() {
			return existsInWalletCommands;
		}

		public List<String> getRemovedFromWalletCommands() {
			return removedFromWalletCommands;
		}

		public boolean isRedeemed() {
			return redeemed;
		}

		public void setRedeemed(boolean redeemed) {
			this.redeemed = redeemed;
		}
	}
}
