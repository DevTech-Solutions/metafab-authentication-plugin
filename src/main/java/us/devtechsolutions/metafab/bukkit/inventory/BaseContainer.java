package us.devtechsolutions.metafab.bukkit.inventory;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.bukkit.inventory.button.ClickableItem;
import us.devtechsolutions.metafab.bukkit.inventory.button.ContainerClickAction;
import us.devtechsolutions.metafab.bukkit.inventory.button.ContainerItem;
import us.devtechsolutions.metafab.util.C;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings({"deprecation", "unused"})
public abstract class BaseContainer implements InventoryHolder, ContainerButtonHandler {

    private static final int[][] OUTSIDE_SLOTS = {
            {0,/* 1, 2, 3, 4, 5, 6, 7, */8},
            {0,/* 1, 2, 3, 4, 5, 6, 7, */8,9,/*10,11,12,13,14,15,16,*/17},
            {0, 1, 2, 3, 4, 5, 6, 7, 8,9,/*10,11,12,13,14,15,16,*/17,18,19,20,21,22,23,24,25,26},
            {0, 1, 2, 3, 4, 5, 6, 7, 8,9,/*10,11,12,13,14,15,16,*/17,18,/*19,20,21,22,23,24,25,*/26,27,28,29,30,31,32,33,34,35,},
            {0, 1, 2, 3, 4, 5, 6, 7, 8,9,/*10,11,12,13,14,15,16,*/17,18,/*19,20,21,22,23,24,25,*/26,27,/*28,29,30,31,32,33,34,*/35,36,37,38,39,40,41,42,43,44,},
            {0, 1, 2, 3, 4, 5, 6, 7, 8,9,/*10,11,12,13,14,15,16,*/17,18,/*19,20,21,22,23,24,25,*/26,27,/*28,29,30,31,32,33,34,*/35,36,/*37,38,39,40,41,42,43,*/44,45,46,47,48,49,50,51,52,53},
            {0, 1, 2, 3, 4, 5, 6, 7, 8,9,/*10,11,12,13,14,15,16,*/17,18,/*19,20,21,22,23,24,25,*/26,27,/*28,29,30,31,32,33,34,*/35,36,/*37,38,39,40,41,42,43,*/44,45,/*46,47,48,49,50,51,52,*/53,54,55,56,57,58,59,60,61,62},
    };

    private static final int[][] INSIDE_SLOTS = {
            {1, 2, 3, 4, 5, 6, 7},
            {1, 2, 3, 4, 5, 6, 7,10,11,12,13,14,15,16},
            {10,11,12,13,14,15,16},
            {10,11,12,13,14,15,16,19,20,21,22,23,24,25},
            {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34},
            {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43},
            {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43,46,47,48,49,50,51,52},
    };

    private final HashMap<Integer, ContainerItem> mapItems;
    private final Inventory inventory;

    private final int rows;
    private final String title;
    private ContainerFlag[] menuFlags;
    protected boolean selfHandle = false;

    /**
     * Construct a new Menu.
     */
    public BaseContainer(int rows, String title, ContainerFlag... menuFlags) {
        this(rows, title);
        this.menuFlags = menuFlags;
    }

    /**
     * Construct a new Menu.
     */
    public BaseContainer(int rows, String title) {
        this.mapItems = Maps.newHashMap();

        this.rows = rows;
        this.title = C.translate(title);
        this.inventory = Bukkit.createInventory(this, rows * 9, this.title);
    }

    /**
     * Construct a new Menu.
     */
    public BaseContainer(String title, int rows, InventoryType type, ContainerFlag... menuFlags) {
        this.mapItems = Maps.newHashMap();

        this.rows = rows;
        this.title = C.translate(title);
        this.inventory = Bukkit.createInventory(this, type, this.title);
        this.menuFlags = menuFlags;
    }

    @SuppressWarnings("unused")
    public void setSelfHandle(boolean b) {
        this.selfHandle = b;
    }

    @Override
    public final @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public boolean isSelfHandle() {
        return selfHandle;
    }

    public void selfHandle(InventoryClickEvent event) {
        BaseContainer menu = (BaseContainer) event.getInventory().getHolder();
        Player player = (Player) event.getWhoClicked();

        ContainerItem item = Objects.requireNonNull(menu).getMenuItem(event.getRawSlot());
        if (item == null) return;

        if (!item.isAllowingPickup())
            event.setCancelled(true);

        if (item instanceof ClickableItem) {
            event.setCancelled(true);
            ((ClickableItem) item).getClickAction().onClick(player, event.getClick());
        }
    }

    /**
     * Open the inventory to the specified player.
     *
     * @param player Specified Player
     */
    public void open(Player player) {
        if (hasFlag(ContainerFlag.RESET_CURSOR_ON_OPEN))
            player.closeInventory();

        player.openInventory(this.inventory);
    }

    public final boolean hasFlag(ContainerFlag menuFlag) {
        if (this.menuFlags == null || this.menuFlags.length == 0) return false;
        boolean result = false;
        for (ContainerFlag flag : this.menuFlags) {
            if (flag == menuFlag) {
                result = true;
                break;
            }
        }
        return result;
    }

    @SuppressWarnings("unused")
    public HashMap<Integer, ContainerItem> getMapItems() {
        return mapItems;
    }

    /**
     * This should be overwritten for menus which require the usage of this.
     */
    @SuppressWarnings("unused")
    public void onClose(InventoryCloseEvent event) {
    }

    /**
     * This should be overwritten for menu which require the usage of this. If selfHandle = true then this is not triggered.
     * This is done before the handling of the Clickable / MenuItems.
     * Cancelling this event WILL NOT cancel the handling of the Clickable / Menuitems.
     */
    public void onClick(InventoryClickEvent event) {

    }

    /**
     * This is called on every interaction.
     */
    public void onInteract(BaseContainer menu) {

    }

    /**
     * Add an item to the inventory.
     */
    @Override
    public void addItem(ContainerItem menuItem) {
        this.inventory.setItem(menuItem.getIndex(), menuItem.getItemStack());
        this.mapItems.put(menuItem.getIndex(), menuItem);
    }

    @Override
    public void deleteItem(int index) {
        this.inventory.setItem(index, null);
        this.mapItems.remove(index);
    }

    /**
     * Check if the inventory contains a specific item.
     *
     * @param itemStack Item to search for
     * @return true if item is found
     */
    @Override
    public boolean containsItem(ItemStack itemStack) {
        return this.mapItems.values().stream().anyMatch(item -> item.getItemStack().equals(itemStack));
    }

    /**
     * Check if the inventory slot is in use.
     *
     * @param index slot index
     * @return found status
     */
    @Override
    public boolean containsItem(int index) {
        return this.inventory.getItem(index) != null;
    }

    /**
     * Get the MenuItem from an input ItemStack.
     *
     * @param itemStack Item to search for
     * @return MenuItem version of the found ItemStack
     */
    @Override
    public ContainerItem getMenuItem(ItemStack itemStack) {
        return this.mapItems.values().stream().filter(item -> item.getItemStack().equals(itemStack)).findFirst().orElse(null);
    }

    /**
     * Get the MenuItem from an index input.
     *
     * @param index slot to search
     * @return MenuItem version of the found ItemStack
     */
    @Override
    public ContainerItem getMenuItem(int index) {
        return this.mapItems.getOrDefault(index, null);
    }

    /**
     * Get the amount of rows in the inventory.
     *
     * @return amount of rows
     */
    public final int getRows() {
        return rows;
    }

    /**
     * Get the display title of the inventory ui.
     *
     * @return display title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Get the flags assigned to said inventory.
     *
     * @return array of menu flags
     */
    public final ContainerFlag[] getMenuFlags() {
        return menuFlags;
    }

    protected int[] getEdgeSlots() {
        return OUTSIDE_SLOTS[rows - 1];
    }

    public boolean isSlotBlocked(int slot) {
        for (int i : this.mapItems.keySet()) {
            if (i != slot) continue;
            ContainerItem item = this.mapItems.get(i);
            if (!item.isAllowingPickup()) {
                return true;
            }
        }

        return false;
    }

    public int[] getInsideSlots() {
        return INSIDE_SLOTS[rows - 1];
    }

    public void setAll(ItemStack itemStack, @Nullable ContainerClickAction action) {
        for (int i = 0; i < this.inventory.getSize(); i++) {
            addItem(action == null ? new ContainerItem(i, itemStack, false) : new ClickableItem(i, itemStack, action));
        }
    }

    public void setAll(ItemStack itemStack) {
        this.setAll(itemStack, null);
    }

    public void addItemArray(int[] array, ItemStack itemStack, @Nullable ContainerClickAction action) {
        for (int i : array) addItem(action == null ? new ContainerItem(i, itemStack, false) : new ClickableItem(i, itemStack, action));
    }

    public void addItemArray(int[] array, ItemStack itemStack) {
        for (int i : array) addItem(new ContainerItem(i, itemStack, false));
    }
}
