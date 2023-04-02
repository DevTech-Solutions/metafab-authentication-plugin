package us.devtechsolutions.metafab.model.wallet;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Wallet permits BaseWallet {

	/**
	 * Get the Wallet's id.
	 *
	 * @return the Wallet's id
	 */
	@NotNull UUID id();

	/**
	 * Get the Wallet's address.
	 *
	 * @return the Wallet's address
	 */
	@NotNull String address();
}
