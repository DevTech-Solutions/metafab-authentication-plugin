package us.devtechsolutions.metafab.provider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public interface PluginProvider {
	PluginProvider[] PROVIDER = { null };

	@NotNull String ecosystemId();
	@Nullable default String ecosystemSecret() {
		return System.getenv("ecosystem-secret");
	}

	@NotNull String gameId();
	@Nullable default String gameSecret() {
		return System.getenv("game-secret");
	}

	@NotNull String redirect();

	void sendPlayerMessage(@NotNull UUID playerUniqueId, @NotNull String message);
	boolean isPlayerOnline(@NotNull UUID playerUniqueId);
	void runAsync(Runnable runnable);

	default void initProvider(PluginProvider provider) {
		PROVIDER[0] = provider;
	}

	static PluginProvider of() {
		return PROVIDER[0];
	}
}
