package us.devtechsolutions.metafab.bukkit.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import us.devtechsolutions.metafab.bukkit.inventory.button.ClickableItem;
import us.devtechsolutions.metafab.bukkit.inventory.button.ContainerItem;

/**
 * @author LBuke (Teddeh)
 */
public class ContainerHandler implements Listener {

    public void destroy() {
        Bukkit.getOnlinePlayers().forEach(HumanEntity::closeInventory);
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() == null) return;
        if (!(event.getInventory().getHolder() instanceof BaseContainer)) return;
        if (!(event.getWhoClicked() instanceof Player)) return;

        BaseContainer menu = (BaseContainer) event.getInventory().getHolder();

        boolean blocked = false;
        for (int slot : event.getRawSlots()) {
            if (menu.isSlotBlocked(slot)) {
                blocked = true;
                break;
            }
        }

        menu.onInteract(menu);

        event.setCancelled(blocked);
    }

    @EventHandler
    protected void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() == null) return;
        if (!(event.getInventory().getHolder() instanceof BaseContainer)) return;
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();

        BaseContainer menu = (BaseContainer) event.getInventory().getHolder();

        if (clicked == null && menu.hasFlag(ContainerFlag.CLOSE_ON_NULL_CLICK)) {
            player.closeInventory();
            return;
        }

        menu.onInteract(menu);

        if (menu.isSelfHandle()) {
            menu.selfHandle(event);
            return;
        }

        menu.onClick(event);
        ContainerItem item = menu.getMenuItem(event.getRawSlot());
        if (item == null) return;

        if (!item.isAllowingPickup())
            event.setCancelled(true);

        if (item instanceof ClickableItem clickableItem) {
            clickableItem.getClickAction().onClick(player, event.getClick());
        }
    }

    @EventHandler
    protected void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() == null)
            return;

        if (!(event.getInventory().getHolder() instanceof BaseContainer menu))
            return;

        if (!(event.getPlayer() instanceof Player))
            return;

        menu.onClose(event);
    }
}
