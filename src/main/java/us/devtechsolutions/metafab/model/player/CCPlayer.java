package us.devtechsolutions.metafab.model.player;

import org.jetbrains.annotations.NotNull;

/**
 * @author LBuke (Teddeh)
 */
public final class CCPlayer {
	private final String metafabId;
	private final String accessToken;
	private final String walletDecryptKey;

	public CCPlayer(@NotNull String metafabId, @NotNull String accessToken, @NotNull String walletDecryptKey) {
		this.metafabId = metafabId;
		this.accessToken = accessToken;
		this.walletDecryptKey = walletDecryptKey;
	}

	public @NotNull String metafabId() {
		return metafabId;
	}

	public @NotNull String accessToken() {
		return accessToken;
	}

	public @NotNull String walletDecryptKey() {
		return walletDecryptKey;
	}
}
