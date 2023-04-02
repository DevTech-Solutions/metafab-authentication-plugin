package us.devtechsolutions.metafab.model.player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.model.profile.Profile;
import us.devtechsolutions.metafab.model.wallet.Wallet;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface User permits BaseUser {

	/**
	 * Get the users metafab id.
	 *
	 * @return the users id
	 */
	@NotNull UUID id();

	/**
	 * Get the game id for the player.
	 *
	 * @return the game id
	 */
	@NotNull UUID gameId();

	/**
	 * Get the wallet id for the player.
	 *
	 * @return the wallet id
	 */
	@NotNull UUID walletId();

	/**
	 * Get the connected wallet id for the player.
	 *
	 * @return the connected wallet id
	 */
	@Nullable UUID connectedWalletId();

	/**
	 * Get the profile id for the player.
	 *
	 * @return the profile id
	 */
	@NotNull UUID profileId();

	/**
	 * Get the username for the player.
	 *
	 * @return the username
	 */
	@NotNull String username();

	/**
	 * Get the access token expiry for the player.
	 *
	 * @return the access token expiry
	 */
	long accessTokenExpiresAt();

	/**
	 * Get the wallet for the player.
	 *
	 * @return the wallet
	 */
	@NotNull Wallet wallet();

	/**
	 * Get the custodial wallet for the player.
	 *
	 * @return the custodial wallet
	 */
	@NotNull Wallet custodialWallet();

	/**
	 * Get the profile for the player.
	 *
	 * @return the profile
	 */
	@NotNull Profile profile();

	/**
	 * Get the users access token.
	 *
	 * @return the users access token
	 */
	@NotNull String accessToken();

	/**
	 * Get the users wallet decryption key.
	 *
	 * @return the users wallet decryption key
	 */
	@NotNull String walletDecryptKey();
}
