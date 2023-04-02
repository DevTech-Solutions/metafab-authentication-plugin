package us.devtechsolutions.metafab.model.contract;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.contract.abi.Abi;
import us.devtechsolutions.metafab.model.contract.abi.BaseAbi;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseContract implements Contract {

	private final String id;
	private final String gameId;
	private final String chain;
	private final List<BaseAbi> abi;
	private final String type;
	private final String address;
	private final String forwarderAddress;
	private final Date updatedAt;
	private final Date createdAt;

	public BaseContract(@NotNull String id, @NotNull String gameId, @NotNull String chain,
	                    @NotNull List<BaseAbi> abi, @NotNull String type, @NotNull String address,
	                    @NotNull String forwarderAddress, @NotNull Date updatedAt, @NotNull Date createdAt) {
		this.id = id;
		this.gameId = gameId;
		this.chain = chain;
		this.abi = abi;
		this.type = type;
		this.address = address;
		this.forwarderAddress = forwarderAddress;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}

	/**
	 * Get the id of the contract.
	 *
	 * @return contract id
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
	 * Get the contract chain.
	 *
	 * @return contract chain
	 */
	@Override
	public @NotNull String chain() {
		return this.chain;
	}

	/**
	 * Get the abi for the contract.
	 *
	 * @return contract abi
	 */
	@Override
	public @NotNull List<Abi> abi() {
		return this.abi.stream().map(a -> (Abi) a).collect(Collectors.toList());
	}

	/**
	 * Get the type of the contract.
	 *
	 * @return contract type
	 */
	@Override
	public @NotNull String type() {
		return this.type;
	}

	/**
	 * Get the address of the contract.
	 *
	 * @return contract address
	 */
	@Override
	public @NotNull String address() {
		return this.address;
	}

	/**
	 * Get the contract's forwarder address.
	 *
	 * @return contract forwarder address
	 */
	@Override
	public @NotNull String forwarderAddress() {
		return this.forwarderAddress;
	}

	/**
	 * Get the updated at date/time of the contract.
	 *
	 * @return contract updated date/time
	 */
	@Override
	public @NotNull Date updatedAt() {
		return this.updatedAt;
	}

	/**
	 * Get the created at date/time of the contract.
	 *
	 * @return contract created date/time
	 */
	@Override
	public @NotNull Date createdAt() {
		return this.createdAt;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "BaseContract{" +
				"id='" + id + '\'' +
				", gameId='" + gameId + '\'' +
				", chain='" + chain + '\'' +
				", abi=" + abi +
				", type='" + type + '\'' +
				", address='" + address + '\'' +
				", forwarderAddress='" + forwarderAddress + '\'' +
				", updatedAt=" + updatedAt +
				", createdAt=" + createdAt +
				'}';
	}
}
