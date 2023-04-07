package us.devtechsolutions.metafab.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.authentication.AuthenticationManager;
import us.devtechsolutions.metafab.collection.CollectionManager;
import us.devtechsolutions.metafab.model.collection.Collection;
import us.devtechsolutions.metafab.player.PlayerManager;

import java.util.List;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class CollectionAPI {
	private static CollectionAPI singleton;

	private final CollectionManager collectionManager;

	CollectionAPI(@NotNull CollectionManager collectionManager) {
		singleton = this;

		this.collectionManager = collectionManager;
	}


	public static List<Collection> getCollections() {
		return singleton.collectionManager.getCollections();
	}

	public static @Nullable Collection getCollection(String name) {
		return singleton.collectionManager.getCollection(name);
	}

	public static @Nullable Collection getCollection(UUID id) {
		return singleton.collectionManager.getCollection(id);
	}
}
