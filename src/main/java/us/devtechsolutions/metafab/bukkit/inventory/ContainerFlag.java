package us.devtechsolutions.metafab.bukkit.inventory;

/**
 * @author LBuke (Teddeh)
 */
public enum ContainerFlag {

    /**
     * When a user clicks outside of the inventory ui,
     * the menu will close.
     * <p>
     * ! This does not account for empty slots. !
     */
    CLOSE_ON_NULL_CLICK,

    /**
     * When a user opens the specific inventory,
     * the cursor will be reset back to its
     * default position. (X:Center - Y:Bottom)
     * <p>
     * ! This only accounts for opening !
     */
    RESET_CURSOR_ON_OPEN,
}
