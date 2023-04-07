package us.devtechsolutions.metafab.model.contract.write;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface ContractWrite permits BaseContractWrite {

	@NotNull UUID id();

	@NotNull UUID contractId();

	@NotNull UUID walletId();

	@NotNull String function();

	@NotNull String hash();

	@NotNull Date updatedAt();

	@NotNull Date createdAt();
}
