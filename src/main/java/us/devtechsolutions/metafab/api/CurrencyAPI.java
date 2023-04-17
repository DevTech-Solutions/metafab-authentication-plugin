package us.devtechsolutions.metafab.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.manager.CurrencyManager;
import us.devtechsolutions.metafab.model.currency.Currency;

import java.util.List;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class CurrencyAPI {
	private static CurrencyAPI singleton;

	private final CurrencyManager currencyManager;

	@ApiStatus.Internal
	CurrencyAPI(@NotNull CurrencyManager currencyManager) {
		singleton = this;

		this.currencyManager = currencyManager;
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public @NotNull List<Currency> getCurrencies() {
		return singleton.currencyManager.getCurrencies();
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public @Nullable Currency getCurrency(@NotNull String name) {
		return singleton.currencyManager.getCurrency(name);
	}

	@NonBlocking
	@ApiStatus.Experimental
	@ApiStatus.AvailableSince("1.0")
	public @Nullable Currency getCurrency(@NotNull UUID id) {
		return singleton.currencyManager.getCurrency(id);
	}
}
