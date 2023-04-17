package us.devtechsolutions.metafab.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.model.currency.Currency;

import java.util.*;

/**
 * @author LBuke (Teddeh)
 */
public final class CurrencyManager {

	private final Map<UUID, Currency> currencyIdMap = new HashMap<>();
	private final Map<String, Currency> currencyNameMap = new HashMap<>();

	public CurrencyManager(@NotNull List<Currency> currencyList) {
		currencyList.forEach(c -> {
			this.currencyIdMap.put(c.id(), c);
			this.currencyNameMap.put(c.name(), c);
		});
	}

	public @NotNull List<Currency> getCurrencies() {
		return currencyIdMap.values().stream().toList();
	}

	public @Nullable Currency getCurrency(@NotNull String name) {
		return this.currencyNameMap.get(name);
	}

	public @Nullable Currency getCurrency(@NotNull UUID id) {
		return this.currencyIdMap.get(id);
	}
}
