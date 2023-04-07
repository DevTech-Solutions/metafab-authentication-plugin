package us.devtechsolutions.metafab.model.collection;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.contract.BaseContract;
import us.devtechsolutions.metafab.model.contract.Contract;

import java.util.Date;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseCollection implements Collection {

	private final String id;
	private final String gameId;
	private final String contractId;
	private final String name;
	private final Date updatedAt;
	private final Date createdAt;
	private final BaseContract contract;

	public BaseCollection(@NotNull String id, @NotNull String gameId, @NotNull String contractId, @NotNull String name,
	                      @NotNull Date updatedAt, @NotNull Date createdAt, @NotNull BaseContract contract) {
		this.id = id;
		this.gameId = gameId;
		this.contractId = contractId;
		this.name = name;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.contract = contract;
	}

	@Override
	public @NotNull UUID id() {
		return UUID.fromString(this.id);
	}

	@Override
	public @NotNull UUID gameId() {
		return UUID.fromString(this.gameId);
	}

	@Override
	public @NotNull UUID contractId() {
		return UUID.fromString(this.contractId);
	}

	@Override
	public @NotNull String name() {
		return this.name;
	}

	@Override
	public @NotNull Date updatedAt() {
		return this.updatedAt;
	}

	@Override
	public @NotNull Date createdAt() {
		return this.createdAt;
	}

	@Override
	public @NotNull Contract contract() {
		return this.contract;
	}

	@Override
	public String toString() {
		return "BaseCollection{" +
				"id='" + id + '\'' +
				", gameId='" + gameId + '\'' +
				", contractId='" + contractId + '\'' +
				", name='" + name + '\'' +
				", updatedAt=" + updatedAt +
				", createdAt=" + createdAt +
				", contract=" + contract +
				'}';
	}
}
