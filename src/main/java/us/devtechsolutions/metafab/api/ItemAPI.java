package us.devtechsolutions.metafab.api;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.collection.CollectionManager;
import us.devtechsolutions.metafab.item.ItemManager;
import us.devtechsolutions.metafab.model.item.Item;
import us.devtechsolutions.metafab.model.transaction.Transaction;
import us.devtechsolutions.metafab.util.EndpointUtil;

import java.util.*;

/**
 * @author LBuke (Teddeh)
 */
public final class ItemAPI {
	private static ItemAPI singleton;

	private final ItemManager itemManager;

	ItemAPI(@NotNull ItemManager itemManager) {
		singleton = this;

		this.itemManager = itemManager;
	}

	public static @NotNull Set<Item> getItems() {
		return singleton.itemManager.getItems();
	}

	public static @Nullable Set<Item> getItems(@NotNull UUID collectionId) {
		return singleton.itemManager.getItems(collectionId);
	}

	public static Optional<Item> getItem(@NotNull UUID collectionId, long id) {
		return singleton.itemManager.getItem(collectionId, id);
	}

	@Blocking
	public static int getItemBalance(@NotNull String collectionId, long itemId, @NotNull String address) {
		return EndpointUtil.fetchCollectionItemBalance(collectionId, itemId, address);
	}

	@Blocking
	public static Transaction burnCollectionItem(@NotNull String collectionId,
	                                             long itemId,
	                                             int quantity,
	                                             @NotNull String accessToken,
	                                             @NotNull String walletDecryptKey) {
		return EndpointUtil.burnCollectionItem(collectionId, itemId, quantity, accessToken, walletDecryptKey);
	}
}
