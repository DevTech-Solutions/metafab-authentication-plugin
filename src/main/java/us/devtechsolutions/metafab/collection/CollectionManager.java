package us.devtechsolutions.metafab.collection;

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

	public CollectionManager(List<Collection> collectionList) {
		collectionList.forEach(c -> {
			this.collectionIdMap.put(c.id(), c);
			this.collectionNameMap.put(c.name(), c);
		});
	}

	public List<Collection> getCollections() {
		return collectionIdMap.values().stream().toList();
	}

	public @Nullable Collection getCollection(String name) {
		return this.collectionNameMap.get(name);
	}

	public @Nullable Collection getCollection(UUID id) {
		return this.collectionIdMap.get(id);
	}
}
