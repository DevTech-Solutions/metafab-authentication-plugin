package us.devtechsolutions.metafab.model.wallet;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseWallet implements Wallet {

	private final String id;
	private final String address;

	/**
	 * Construct a new Wallet.
	 *
	 * @param id      - the Wallet's id
	 * @param address - the Wallet's address
	 */
	public BaseWallet(@NotNull String id, @NotNull String address) {
		this.id = id;
		this.address = address;
	}

	/**
	 * Get the Wallet's id.
	 *
	 * @return the Wallet's id
	 */
	@Override
	public @NotNull UUID id() {
		return UUID.fromString(this.id);
	}

	/**
	 * Get the Wallet's address.
	 *
	 * @return the Wallet's address
	 */
	@Override
	public @NotNull String address() {
		return this.address;
	}
}
