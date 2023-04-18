package us.devtechsolutions.metafab.manager;

import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.model.contract.Contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class ContractManager {

	private final Map<UUID, Contract> contractIdMap = new HashMap<>();

	public ContractManager(@NotNull List<Contract> collectionList) {
		collectionList.forEach(c -> this.contractIdMap.put(c.id(), c));
	}

	@NonBlocking
	public @NotNull List<Contract> getContracts() {
		return contractIdMap.values().stream().toList();
	}

	@NonBlocking
	public @Nullable Contract getContract(@NotNull UUID id) {
		return this.contractIdMap.get(id);
	}
}
