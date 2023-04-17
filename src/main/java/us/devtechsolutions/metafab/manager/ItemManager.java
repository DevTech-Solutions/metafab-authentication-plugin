package us.devtechsolutions.metafab.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import us.devtechsolutions.metafab.model.item.Item;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LBuke (Teddeh)
 */
public final class ItemManager {

	private final Map<UUID, Set<Item>> itemMap = new HashMap<>();

	public ItemManager() { }

	public void addItems(@NotNull UUID contractId, @NotNull List<Item> itemList) {
		this.itemMap.put(contractId, new HashSet<>(itemList));
	}

	public @NotNull Set<Item> getItems() {
		final Set<Item> set = new HashSet<>();
		this.itemMap.values().forEach(set::addAll);
		return set;
	}

	public @Nullable Set<Item> getItems(@NotNull UUID collectionId) {
		return this.itemMap.get(collectionId);
	}

	public Optional<Item> getItem(@NotNull UUID collectionId, long id) {
		return this.itemMap.get(collectionId).stream().filter(item -> item.id() == id).findFirst();
	}
}
