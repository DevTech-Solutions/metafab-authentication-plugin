package us.devtechsolutions.metafab.model.profile;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.wallet.BaseWallet;
import us.devtechsolutions.metafab.model.wallet.Wallet;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseProfile implements Profile {

	private final String id;
	private final BaseWallet wallet;
	private final BaseWallet custodialWallet;

	/**
	 * Construct a new Profile.
	 *
	 * @param id              - the profile id
	 * @param wallet          - the wallet
	 * @param custodialWallet - the custodial wallet
	 */
	public BaseProfile(@NotNull String id, @NotNull Wallet wallet, @NotNull Wallet custodialWallet) {
		this.id = id;
		this.wallet = (BaseWallet) wallet;
		this.custodialWallet = (BaseWallet) custodialWallet;
	}

	/**
	 * Get the profile id.
	 *
	 * @return the profile id
	 */
	@Override
	public @NotNull UUID id() {
		return UUID.fromString(this.id);
	}

	/**
	 * Get the wallet for the profile.
	 *
	 * @return the wallet
	 */
	@Override
	public @NotNull Wallet wallet() {
		return this.wallet;
	}

	/**
	 * Get the custodial wallet for the profile.
	 *
	 * @return the custodial wallet
	 */
	@Override
	public @NotNull Wallet custodialWallet() {
		return this.custodialWallet;
	}
}
