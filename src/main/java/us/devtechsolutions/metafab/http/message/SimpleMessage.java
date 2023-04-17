package us.devtechsolutions.metafab.http.message;

import org.jetbrains.annotations.NotNull;

/**
 * @author LBuke (Teddeh)
 */
public final class SimpleMessage {

	private final String message;

	public SimpleMessage(@NotNull String message) {
		this.message = message;
	}

	public @NotNull String message() {
		return this.message;
	}
}
