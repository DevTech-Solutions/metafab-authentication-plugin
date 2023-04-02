package us.devtechsolutions.metafab.model.contract;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.contract.abi.Abi;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Contract permits BaseContract {

	/**
	 * Get the id of the contract.
	 *
	 * @return contract id
	 */
	@NotNull UUID id();

	/**
	 * Get the id of the game.
	 *
	 * @return game id
	 */
	@NotNull UUID gameId();

	/**
	 * Get the contract chain.
	 *
	 * @return contract chain
	 */
	@NotNull String chain();

	/**
	 * Get the abi for the contract.
	 *
	 * @return contract abi
	 */
	@NotNull List<Abi> abi();

	/**
	 * Get the type of the contract.
	 *
	 * @return contract type
	 */
	@NotNull String type();

	/**
	 * Get the address of the contract.
	 *
	 * @return contract address
	 */
	@NotNull String address();

	/**
	 * Get the contract's forwarder address.
	 *
	 * @return contract forwarder address
	 */
	@NotNull String forwarderAddress();

	/**
	 * Get the updated at date/time of the contract.
	 *
	 * @return contract updated date/time
	 */
	@NotNull Date updatedAt();

	/**
	 * Get the created at date/time of the contract.
	 *
	 * @return contract created date/time
	 */
	@NotNull Date createdAt();
}
