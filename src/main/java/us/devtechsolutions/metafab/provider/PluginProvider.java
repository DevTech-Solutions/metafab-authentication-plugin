package us.devtechsolutions.metafab.provider;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public interface PluginProvider {

	@NotNull UUID serverId();

	@NotNull String ecosystemId();
	boolean hasEcosystem();

	@NotNull String gameId();

	void runAsync(@NotNull Runnable runnable);
	void runSync(@NotNull Runnable runnable);

	// Not proud of this, but it'll do.
	PluginProvider[] PROVIDER = { null };
	default void initProvider(PluginProvider provider) {
		PROVIDER[0] = provider;
	}
	static PluginProvider of() {
		return PROVIDER[0];
	}
}
