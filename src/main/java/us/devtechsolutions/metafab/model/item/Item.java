package us.devtechsolutions.metafab.model.item;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.contract.Contract;
import us.devtechsolutions.metafab.model.item.attribute.Attribute;
import us.devtechsolutions.metafab.model.item.data.Data;

import java.util.Date;
import java.util.UUID;

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
