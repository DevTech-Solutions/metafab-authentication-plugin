package us.devtechsolutions.metafab.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.model.collection.Collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class CollectionManager {

	private final Map<UUID, Collection> collectionIdMap = new HashMap<>();
	private final Map<String, Collection> collectionNameMap = new HashMap<>();

	public CollectionManager(@NotNull List<Collection> collectionList) {
		collectionList.forEach(c -> {
			this.collectionIdMap.put(c.id(), c);
			this.collectionNameMap.put(c.name(), c);
		});
	}

	public @NotNull List<Collection> getCollections() {
		return collectionIdMap.values().stream().toList();
	}

	public @Nullable Collection getCollection(@NotNull String name) {
		return this.collectionNameMap.get(name);
	}

	public @Nullable Collection getCollection(@NotNull UUID id) {
		return this.collectionIdMap.get(id);
	}
}
