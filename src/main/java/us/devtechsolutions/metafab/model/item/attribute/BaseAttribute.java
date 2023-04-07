package us.devtechsolutions.metafab.model.item.attribute;

import org.jetbrains.annotations.NotNull;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseAttribute implements Attribute {

	private final String trait_type;
	private final String value;

	public BaseAttribute(@NotNull String trait_type, @NotNull String value) {
		this.trait_type = trait_type;
		this.value = value;
	}

	@Override
	public @NotNull String traitType() {
		return this.trait_type;
	}

	@Override
	public @NotNull String value() {
		return this.value;
	}
}
