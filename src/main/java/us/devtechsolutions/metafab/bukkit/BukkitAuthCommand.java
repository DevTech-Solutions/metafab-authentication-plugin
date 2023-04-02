package us.devtechsolutions.metafab.bukkit;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.devtechsolutions.metafab.authentication.AuthenticationManager;

/**
 * @author LBuke (Teddeh)
 */
public final class BukkitAuthCommand implements CommandExecutor {

	private final AuthenticationManager authenticationManager;

	public BukkitAuthCommand(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {
		if (!(sender instanceof Player player))
			return true;

		final String url = this.authenticationManager.generateLink(player.getUniqueId());

		final Component component = Component.text("Authentication Link, Click me to open")
				.style(style -> style.color(NamedTextColor.AQUA))
				.hoverEvent(HoverEvent.showText(
						Component.text("Left click to open url")
				))
				.clickEvent(ClickEvent.clickEvent(
						ClickEvent.Action.OPEN_URL, url
				));

		player.sendMessage(component);
		return true;
	}
}
