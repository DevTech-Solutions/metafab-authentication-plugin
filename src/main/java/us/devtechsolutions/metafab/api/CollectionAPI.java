package us.devtechsolutions.metafab.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.manager.CollectionManager;
import us.devtechsolutions.metafab.model.collection.Collection;

import java.util.List;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class CollectionAPI {
	private static CollectionAPI singleton;

	private final CollectionManager collectionManager;

	@ApiStatus.Internal
	CollectionAPI(@NotNull CollectionManager collectionManager) {
		singleton = this;

		this.collectionManager = collectionManager;
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static @NotNull List<Collection> getCollections() {
		return singleton.collectionManager.getCollections();
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static @Nullable Collection getCollection(@NotNull String name) {
		return singleton.collectionManager.getCollection(name);
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public static @Nullable Collection getCollection(@NotNull UUID id) {
		return singleton.collectionManager.getCollection(id);
	}
}
