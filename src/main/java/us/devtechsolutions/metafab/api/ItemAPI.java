package us.devtechsolutions.metafab.api;

import org.jetbrains.annotations.*;
import us.devtechsolutions.metafab.manager.ItemManager;
import us.devtechsolutions.metafab.model.item.Item;
import us.devtechsolutions.metafab.model.transaction.Transaction;
import us.devtechsolutions.metafab.util.EndpointUtil;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class ItemAPI {
	private static ItemAPI singleton;

	private final ItemManager itemManager;

	@ApiStatus.Internal
	ItemAPI(@NotNull ItemManager itemManager) {
		singleton = this;

		this.itemManager = itemManager;
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static @NotNull Set<Item> getItems() {
		return singleton.itemManager.getItems();
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static @Nullable Set<Item> getItems(@NotNull UUID collectionId) {
		return singleton.itemManager.getItems(collectionId);
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static Optional<Item> getItem(@NotNull UUID collectionId, long id) {
		return singleton.itemManager.getItem(collectionId, id);
	}

	@Blocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static int getItemBalance(@NotNull String collectionId, long itemId, @NotNull String address) {
		return EndpointUtil.fetchCollectionItemBalance(collectionId, itemId, address);
	}

	@Blocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static Transaction burnCollectionItem(@NotNull String collectionId, long itemId, int quantity,
	                                             @NotNull String accessToken, @NotNull String walletDecryptKey) {
		return EndpointUtil.burnCollectionItem(collectionId, itemId, quantity, accessToken, walletDecryptKey);
	}
}
