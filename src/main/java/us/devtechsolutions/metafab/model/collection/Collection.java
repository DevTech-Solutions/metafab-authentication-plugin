package us.devtechsolutions.metafab.model.collection;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.contract.Contract;

import java.util.Date;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Collection permits BaseCollection {

	@NotNull UUID id();

	@NotNull UUID gameId();

	@NotNull UUID contractId();

	@NotNull String name();

	@NotNull Date updatedAt();

	@NotNull Date createdAt();

	@NotNull Contract contract();
}
