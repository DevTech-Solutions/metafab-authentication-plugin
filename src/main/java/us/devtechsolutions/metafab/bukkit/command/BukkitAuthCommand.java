package us.devtechsolutions.metafab.bukkit.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.manager.AuthenticationManager;
import us.devtechsolutions.metafab.model.player.User;
import us.devtechsolutions.metafab.player.PlayerManager;
import us.devtechsolutions.metafab.util.C;

import java.util.Objects;

/**
 * @author LBuke (Teddeh)
 */
public final class BukkitAuthCommand implements CommandExecutor {

	private final AuthenticationManager authenticationManager;
	private final PlayerManager playerManager;

	public BukkitAuthCommand(@NotNull AuthenticationManager authenticationManager, @NotNull PlayerManager playerManager) {
		this.authenticationManager = authenticationManager;
		this.playerManager = playerManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {
		if (!(sender instanceof Player player))
			return true;

		this.authenticationManager.generateLink(player.getUniqueId(), player.getName(), url -> {
			final User user = this.playerManager.getMetaFabUser(player.getUniqueId());
			String str = "&d&lMETAFAB &e&oClick here Authenticate!";
			if (!Objects.isNull(user)) {
				str = "&d&lMETAFAB &fHi, %s.\n&d&lMETAFAB &e&oClick here to re-authenticate!".formatted(user.username());
			}

			final Component component = Component.text(C.translate(str))
					.hoverEvent(HoverEvent.showText(Component.text("Click to open url")))
					.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, url));

			player.sendMessage(component);
		});

		return true;
	}
}
