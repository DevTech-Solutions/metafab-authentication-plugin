package us.devtechsolutions.metafab.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.bukkit.config.ConfigManager;
import us.devtechsolutions.metafab.bukkit.inventory.impl.RedeemInventory;

/**
 * @author LBuke (Teddeh)
 */
public final class RedeemableCommand implements CommandExecutor {

	private final JavaPlugin plugin;
	private final ConfigManager configManager;

	public RedeemableCommand(@NotNull JavaPlugin plugin, @NotNull ConfigManager configManager) {
		this.plugin = plugin;
		this.configManager = configManager;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if (!(sender instanceof Player player))
			return true;

		new RedeemInventory(this.plugin, this.configManager, player).open(player);
		return true;
	}
}
