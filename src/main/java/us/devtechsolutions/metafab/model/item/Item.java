package us.devtechsolutions.metafab.model.item;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.item.attribute.Attribute;
import us.devtechsolutions.metafab.model.item.data.Data;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Item permits BaseItem {

	long id();

	@NotNull String image();

	@NotNull String name();

	@NotNull String description();

	@NotNull String externalUrl();

	@NotNull Attribute attribute();

	@NotNull Data data();
}
