package us.devtechsolutions.metafab.model.contract.write;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseContractWrite implements ContractWrite {

	private final String id;
	private final String contractId;
	private final String walletId;
	private final String function;
	private final String hash;
	private final Date updatedAt;
	private final Date createdAt;

	public BaseContractWrite(@NotNull String id, @NotNull String contractId, @NotNull String walletId,
	                         @NotNull String function, @NotNull String hash,
	                         @NotNull Date updatedAt, @NotNull Date createdAt) {
		this.id = id;
		this.contractId = contractId;
		this.walletId = walletId;
		this.function = function;
		this.hash = hash;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}

	@Override
	public @NotNull UUID id() {
		return UUID.fromString(this.id);
	}

	@Override
	public @NotNull UUID contractId() {
		return UUID.fromString(this.contractId);
	}

	@Override
	public @NotNull UUID walletId() {
		return UUID.fromString(this.walletId);
	}

	@Override
	public @NotNull String function() {
		return this.function;
	}

	@Override
	public @NotNull String hash() {
		return this.hash;
	}

	@Override
	public @NotNull Date updatedAt() {
		return this.updatedAt;
	}

	@Override
	public @NotNull Date createdAt() {
		return this.createdAt;
	}
}
