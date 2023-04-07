package us.devtechsolutions.metafab.model.currency.fees;

import org.jetbrains.annotations.NotNull;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface CurrencyFees permits BaseCurrencyFees {

	@NotNull String recipientAddress();

	double basisPoints();

	double fixedAmount();

	double capAmount();
}
