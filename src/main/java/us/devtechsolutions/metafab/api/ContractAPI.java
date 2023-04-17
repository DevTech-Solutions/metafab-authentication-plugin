package us.devtechsolutions.metafab.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.manager.ContractManager;
import us.devtechsolutions.metafab.model.contract.Contract;

import java.util.List;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class ContractAPI {
	private static ContractAPI singleton;

	private final ContractManager contractManager;

	@ApiStatus.Internal
	ContractAPI(@NotNull ContractManager contractManager) {
		singleton = this;

		this.contractManager = contractManager;
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public @NotNull List<Contract> getContracts() {
		return singleton.contractManager.getContracts();
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public @Nullable Contract getContract(@NotNull UUID id) {
		return singleton.contractManager.getContract(id);
	}
}
