package us.devtechsolutions.metafab.model.player;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.model.profile.BaseProfile;
import us.devtechsolutions.metafab.model.profile.Profile;
import us.devtechsolutions.metafab.model.wallet.BaseWallet;
import us.devtechsolutions.metafab.model.wallet.Wallet;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseUser implements User {

	private final String id;
	private final String gameId;
	private final String walletId;
	private final String connectedWalletId;
	private final String profileId;
	private final String username;
	private final String accessTokenExpiresAt;
	private final BaseWallet wallet;
	private final BaseWallet custodialWallet;
	private final BaseProfile profile;
	private String accessToken;
	private String walletDecryptKey;

	/**
	 * Construct a new MetaFab Player.
	 *
	 * @param id                   - the users id
	 * @param gameId               - the game id
	 * @param walletId             - the wallet id
	 * @param connectedWalletId    - the connected wallet id
	 * @param profileId            - the profile id
	 * @param username             - the username
	 * @param accessTokenExpiresAt - the access token expiry
	 * @param wallet               - the wallet
	 * @param custodialWallet      - the custodial wallet
	 * @param profile              - the profile
	 * @param accessToken          - the users access token
	 * @param walletDecryptKey     - the users wallet decryption key
	 */
	public BaseUser(@NotNull String id, @NotNull String gameId, @NotNull String walletId,
	                @NotNull String connectedWalletId, @NotNull String profileId, @NotNull String username,
	                @NotNull String accessTokenExpiresAt, @NotNull Wallet wallet, @NotNull Wallet custodialWallet,
	                @NotNull Profile profile, @NotNull String accessToken, @NotNull String walletDecryptKey) {
		this.id = id;
		this.gameId = gameId;
		this.walletId = walletId;
		this.connectedWalletId = connectedWalletId;
		this.profileId = profileId;
		this.username = username;
		this.accessTokenExpiresAt = accessTokenExpiresAt;
		this.wallet = (BaseWallet) wallet;
		this.custodialWallet = (BaseWallet) custodialWallet;
		this.profile = (BaseProfile) profile;
		this.accessToken = accessToken;
		this.walletDecryptKey = walletDecryptKey;
	}

	/**
	 * Get the users metafab id.
	 *
	 * @return the users id
	 */
	@Override
	public @NotNull UUID id() {
		return UUID.fromString(this.id);
	}

	/**
	 * Get the game id for the player.
	 *
	 * @return the game id
	 */
	@Override
	public @NotNull UUID gameId() {
		return UUID.fromString(this.gameId);
	}

	/**
	 * Get the wallet id for the player.
	 *
	 * @return the wallet id
	 */
	@Override
	public @NotNull UUID walletId() {
		return UUID.fromString(this.walletId);
	}

	/**
	 * Get the connected wallet id for the player.
	 *
	 * @return the connected wallet id
	 */
	@Override
	public @Nullable UUID connectedWalletId() {
		return UUID.fromString(this.connectedWalletId);
	}

	/**
	 * Get the profile id for the player.
	 *
	 * @return the profile id
	 */
	@Override
	public @NotNull UUID profileId() {
		return UUID.fromString(this.profileId);
	}

	/**
	 * Get the username for the player.
	 *
	 * @return the username
	 */
	@Override
	public @NotNull String username() {
		return this.username;
	}

	/**
	 * Get the access token expiry for the player.
	 *
	 * @return the access token expiry
	 */
	@Override
	public long accessTokenExpiresAt() {
		if (this.accessTokenExpiresAt.isEmpty())
			return -1L;

		try {
			return Long.parseLong(this.accessTokenExpiresAt);
		} catch (NumberFormatException exception) {
			return -1L;
		}
	}

	/**
	 * Get the wallet for the player.
	 *
	 * @return the wallet
	 */
	@Override
	public @NotNull Wallet wallet() {
		return this.wallet;
	}

	/**
	 * Get the custodial wallet for the player.
	 *
	 * @return the custodial wallet
	 */
	@Override
	public @NotNull Wallet custodialWallet() {
		return this.custodialWallet;
	}

	/**
	 * Get the profile for the player.
	 *
	 * @return the profile
	 */
	@Override
	public @NotNull Profile profile() {
		return this.profile;
	}

	/**
	 * Get the users access token.
	 *
	 * @return the users access token
	 */
	@Override
	public @NotNull String accessToken() {
		return this.accessToken;
	}

	@ApiStatus.Internal
	public void accessToken(@NotNull String value) {
		this.accessToken = value;
	}

	/**
	 * Get the users wallet decryption key.
	 *
	 * @return the users wallet decryption key
	 */
	@Override
	public @NotNull String walletDecryptKey() {
		return this.walletDecryptKey;
	}

	@ApiStatus.Internal
	public void walletDecryptKey(@NotNull String value) {
		this.walletDecryptKey = value;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "BaseMetaFabPlayer{\n" +
				" id='" + id + "',\n" +
				" gameId='" + gameId + "',\n" +
				" walletId='" + walletId + "',\n" +
				" connectedWalletId='" + connectedWalletId + "',\n" +
				" profileId='" + profileId + "',\n" +
				" username='" + username + "',\n" +
				" accessTokenExpiresAt='" + accessTokenExpiresAt + "',\n" +
				" wallet='" + wallet + "',\n" +
				" custodialWallet='" + custodialWallet + "',\n" +
				" profile='" + profile + "',\n" +
				" accessToken='" + accessToken + "',\n" +
				" walletDecryptKey='" + walletDecryptKey + "',\n" +
				'}';
	}
}
