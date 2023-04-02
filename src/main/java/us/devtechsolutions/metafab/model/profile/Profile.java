package us.devtechsolutions.metafab.model.profile;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.wallet.Wallet;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Profile permits BaseProfile {

	/**
	 * Get the profile id.
	 *
	 * @return the profile id
	 */
	@NotNull UUID id();

	/**
	 * Get the wallet for the profile.
	 *
	 * @return the wallet
	 */
	@NotNull Wallet wallet();

	/**
	 * Get the custodial wallet for the profile.
	 *
	 * @return the custodial wallet
	 */
	@NotNull Wallet custodialWallet();
}
