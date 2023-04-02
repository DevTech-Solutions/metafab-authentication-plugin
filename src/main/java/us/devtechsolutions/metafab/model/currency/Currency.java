package us.devtechsolutions.metafab.model.currency;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.contract.Contract;

import java.util.Date;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Currency permits BaseCurrency {

	/**
	 * Get the id of the currency.
	 *
	 * @return currency id
	 */
	@NotNull UUID id();

	/**
	 * Get the id of the game.
	 *
	 * @return game id
	 */
	@NotNull UUID gameId();

	/**
	 * Get the id of the contract.
	 *
	 * @return contract id
	 */
	@NotNull UUID contractId();

	/**
	 * Get the name of the currency.
	 *
	 * @return currency name
	 */
	@NotNull String name();

	/**
	 * Get the symbol of the currency.
	 *
	 * @return currency symbol
	 */
	@NotNull String symbol();

	/**
	 * Get the supply cap for the currency.
	 *
	 * @return currency supply cap
	 */
	long supplyCap();

	/**
	 * Get the updated at date/time of the currency.
	 *
	 * @return currency update date/time
	 */
	@NotNull Date updatedAt();

	/**
	 * Get the created at date/time of the currency.
	 *
	 * @return currency created date/time
	 */
	@NotNull Date createdAt();

	/**
	 * Get the contract for the currency.
	 *
	 * @return currency contract
	 */
	@NotNull Contract contract();
}
