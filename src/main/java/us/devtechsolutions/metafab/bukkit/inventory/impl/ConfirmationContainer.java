package us.devtechsolutions.metafab.bukkit.inventory.impl;

import org.bukkit.Material;
import us.devtechsolutions.metafab.bukkit.inventory.BaseContainer;
import us.devtechsolutions.metafab.bukkit.inventory.button.ClickableItem;
import us.devtechsolutions.metafab.bukkit.item.ItemBuilder;
import us.devtechsolutions.metafab.util.C;
import us.devtechsolutions.metafab.util.Callback;
import us.devtechsolutions.metafab.util.StringUtil;

/**
 * @author LBuke (Teddeh)
 */
public class ConfirmationContainer extends BaseContainer {
    private static final String TITLE = "&4&lNO&r     Confirmation     &2&lYES";

    public ConfirmationContainer(Callback<Boolean> callback) {
        super(3, StringUtil.getCenteredMenuText(TITLE));

        final ItemBuilder gray = ItemBuilder.of(Material.LIGHT_GRAY_STAINED_GLASS_PANE).model(0).name(C.RESET);
        setAll(gray.build());

        //Cancel
        final ItemBuilder cancel = ItemBuilder.of(Material.PAPER).model(0)
                .name("&c&l❌❌ &cCancel")
                .lore("", "&e&l!! &fClick to decline.");
        addItem(new ClickableItem(11, cancel.build(), (clicker, clickType) -> callback.call(false)));

        //Accept
        final ItemBuilder accept = ItemBuilder.of(Material.PAPER).model(0)
                .name("&a&l✔✔ &aAccept")
                .lore("", "&e&l!! &fClick to accept.");
        addItem(new ClickableItem(15, accept.build(), (clicker, clickType) -> callback.call(true)));
    }
}
