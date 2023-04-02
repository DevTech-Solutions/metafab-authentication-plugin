package us.devtechsolutions.metafab.provider;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public interface PluginProvider {
	PluginProvider[] PROVIDER = { null };

	@NotNull String ecosystemId();
	@NotNull String ecosystemSecret(); //<--- todo remove

	@NotNull String gameId();
	@NotNull String gameSecret(); //<--- todo remove

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
