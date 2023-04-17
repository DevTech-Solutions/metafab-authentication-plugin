package us.devtechsolutions.metafab.model;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class Code {
	private final String code;

	public Code(@NotNull String code) {
		this.code = code;
	}

	public @NotNull UUID getCode() {
		return UUID.fromString(this.code);
	}
}
