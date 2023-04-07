package us.devtechsolutions.metafab.bukkit.inventory.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/**
 * @author LBuke (Teddeh)
 */
public interface ContainerClickAction {

    /**
     * This is fired when an item is interacted with.
     *
     * @param clicker   Player who interacted
     * @param clickType interaction type
     */
    void onClick(Player clicker, ClickType clickType);
}
