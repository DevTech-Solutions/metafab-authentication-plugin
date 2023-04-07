package us.devtechsolutions.metafab.model.currency.fees;

import org.jetbrains.annotations.NotNull;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseCurrencyFees implements CurrencyFees {

	private final String recipientAddress;
	private final double basisPoints;
	private final double fixedAmount;
	private final double capAmount;

	public BaseCurrencyFees(@NotNull String recipientAddress, double basisPoints,
	                        double fixedAmount, double capAmount) {
		this.recipientAddress = recipientAddress;
		this.basisPoints = basisPoints;
		this.fixedAmount = fixedAmount;
		this.capAmount = capAmount;
	}

	@Override
	public @NotNull String recipientAddress() {
		return this.recipientAddress;
	}

	@Override
	public double basisPoints() {
		return this.basisPoints;
	}

	@Override
	public double fixedAmount() {
		return this.fixedAmount;
	}

	@Override
	public double capAmount() {
		return this.capAmount;
	}
}
