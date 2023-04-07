package us.devtechsolutions.metafab.bukkit.inventory;

import org.bukkit.inventory.ItemStack;
import us.devtechsolutions.metafab.bukkit.inventory.button.ContainerItem;

/**
 * @author LBuke (Teddeh)
 */
public interface ContainerButtonHandler {

    /**
     * Add an item to the inventory.
     */
    void addItem(ContainerItem menuItem);

    /**
     * Delete an item from the inventory.
     */
    void deleteItem(int index);

    /**
     * Check if the inventory contains a specific item.
     *
     * @param itemStack Item to search for
     * @return true if item is found
     */
    boolean containsItem(ItemStack itemStack);

    /**
     * Check if the inventory slot is in use.
     *
     * @param index slot index
     * @return found status
     */
    boolean containsItem(int index);

    /**
     * Get the MenuItem from an input ItemStack.
     *
     * @param itemStack Item to search for
     * @return MenuItem version of the found ItemStack
     */
    ContainerItem getMenuItem(ItemStack itemStack);

    /**
     * Get the MenuItem from an index input.
     *
     * @param index slot to search
     * @return MenuItem version of the found ItemStack
     */
    ContainerItem getMenuItem(int index);


}
