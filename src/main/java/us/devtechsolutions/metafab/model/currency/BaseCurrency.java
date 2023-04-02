package us.devtechsolutions.metafab.model.currency;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.contract.BaseContract;
import us.devtechsolutions.metafab.model.contract.Contract;

import java.util.Date;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseCurrency implements Currency {

	private final String id;
	private final String gameId;
	private final String contractId;
	private final String name;
	private final String symbol;
	private final long supplyCap;
	private final Date updatedAt;
	private final Date createdAt;
	private final BaseContract contract;

	public BaseCurrency(@NotNull String id, @NotNull String gameId, @NotNull String contractId, @NotNull String name,
	                    @NotNull String symbol, long supplyCap, @NotNull Date updatedAt, @NotNull Date createdAt,
	                    @NotNull BaseContract contract) {
		this.id = id;
		this.gameId = gameId;
		this.contractId = contractId;
		this.name = name;
		this.symbol = symbol;
		this.supplyCap = supplyCap;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.contract = contract;
	}

	/**
	 * Get the id of the currency.
	 *
	 * @return currency id
	 */
	@Override
	public @NotNull UUID id() {
		return UUID.fromString(this.id);
	}

	/**
	 * Get the id of the game.
	 *
	 * @return game id
	 */
	@Override
	public @NotNull UUID gameId() {
		return UUID.fromString(this.gameId);
	}

	/**
	 * Get the id of the contract.
	 *
	 * @return contract id
	 */
	@Override
	public @NotNull UUID contractId() {
		return UUID.fromString(this.contractId);
	}

	/**
	 * Get the name of the currency.
	 *
	 * @return currency name
	 */
	@Override
	public @NotNull String name() {
		return this.name;
	}

	/**
	 * Get the symbol of the currency.
	 *
	 * @return currency symbol
	 */
	@Override
	public @NotNull String symbol() {
		return this.symbol;
	}

	/**
	 * Get the supply cap for the currency.
	 *
	 * @return currency supply cap
	 */
	@Override
	public long supplyCap() {
		return this.supplyCap;
	}

	/**
	 * Get the updated at date/time of the currency.
	 *
	 * @return currency update date/time
	 */
	@Override
	public @NotNull Date updatedAt() {
		return this.updatedAt;
	}

	/**
	 * Get the created at date/time of the currency.
	 *
	 * @return currency created date/time
	 */
	@Override
	public @NotNull Date createdAt() {
		return this.createdAt;
	}

	/**
	 * Get the contract for the currency.
	 *
	 * @return currency contract
	 */
	@Override
	public @NotNull Contract contract() {
		return this.contract;
	}

	@Override
	public String toString() {
		return "BaseCurrency{" +
				"id='" + id + '\'' +
				", gameId='" + gameId + '\'' +
				", contractId='" + contractId + '\'' +
				", name='" + name + '\'' +
				", symbol='" + symbol + '\'' +
				", supplyCap=" + supplyCap +
				", updatedAt=" + updatedAt +
				", createdAt=" + createdAt +
				", contract=" + contract +
				'}';
	}
}
