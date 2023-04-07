package us.devtechsolutions.metafab.bukkit.inventory.example;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.devtechsolutions.metafab.bukkit.inventory.BaseContainer;
import us.devtechsolutions.metafab.bukkit.inventory.button.ClickableItem;
import us.devtechsolutions.metafab.bukkit.inventory.button.ContainerItem;

/**
 * @author LBuke (Teddeh)
 */
public class ExampleUI extends BaseContainer {

    /**
     * Construct a new Menu.
     */
    public ExampleUI() {
        //Without MenuFlags
        super(6, "Example Interface");

        //With MenuFlags
        //super(6, "Example Interface", CoreMenuFlag.CLOSE_ON_NULL_CLICK, CoreMenuFlag.RESET_CURSOR_ON_OPEN);

        //new MenuItem(index, itemstack, allowPickup)
        addItem(new ContainerItem(0, new ItemStack(Material.APPLE), false));

        //new ClickableItem(index, itemstack, menuClickAction)
        addItem(new ClickableItem(1, new ItemStack(Material.ENDER_PEARL, 4), (player, clickType) -> {
            player.sendMessage("You clicked the Ender pearl... nice! (" + clickType.name() + ")");

        }));

        //done.
    }

    //How to open this menu.
    //new ExampleUI().openInventory(player);
}
