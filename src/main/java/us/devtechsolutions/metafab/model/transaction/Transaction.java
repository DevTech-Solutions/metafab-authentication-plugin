package us.devtechsolutions.metafab.model.transaction;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Date;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Transaction permits BaseTransaction {

	@NotNull UUID id();

	@NotNull UUID contractId();

	@NotNull UUID walletId();

	@NotNull String function();

	@UnknownNullability Object args();

	@NotNull String hash();

	@NotNull Date updatedAt();

	@NotNull Date createdAt();
}
