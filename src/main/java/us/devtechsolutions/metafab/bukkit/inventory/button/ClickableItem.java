package us.devtechsolutions.metafab.bukkit.inventory.button;

import org.bukkit.inventory.ItemStack;

/**
 * @author LBuke (Teddeh)
 */
public class ClickableItem extends ContainerItem {

    private ContainerClickAction clickAction;

    /**
     * Construct a new MenuItem
     *
     * @param index     menu slot index
     * @param itemStack menu itemstack
     */
    public ClickableItem(int index, ItemStack itemStack) {
        super(index, itemStack, false);
        this.clickAction = getClickAction();
    }

    /**
     * Construct a new MenuItem
     *
     * @param index     menu slot index
     * @param itemStack menu itemstack
     */
    public ClickableItem(int index, ItemStack itemStack, ContainerClickAction clickAction) {
        super(index, itemStack, false);
        this.clickAction = clickAction;
    }

    /**
     * Construct a new MenuItem
     *
     * @param index     menu slot index
     * @param itemStack menu itemstack
     */
    public ClickableItem(int index, ItemStack itemStack, boolean allowPickup, ContainerClickAction clickAction) {
        super(index, itemStack, allowPickup);
        this.clickAction = clickAction;
    }

    public ClickableItem(int index, ItemStack itemStack, ContainerClickAction clickAction, boolean pickup) {
        super(index, itemStack, pickup);
        this.clickAction = clickAction;
    }

    public ContainerClickAction getClickAction() {
        return clickAction;
    }
}
