package us.devtechsolutions.metafab.model.item.attribute;

import org.jetbrains.annotations.NotNull;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Attribute permits BaseAttribute {

	@NotNull String traitType();

	@NotNull String value();
}
