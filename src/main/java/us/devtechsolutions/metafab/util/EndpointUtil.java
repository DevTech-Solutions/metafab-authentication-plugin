package us.devtechsolutions.metafab.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.devtechsolutions.metafab.model.Code;
import us.devtechsolutions.metafab.model.collection.BaseCollection;
import us.devtechsolutions.metafab.model.collection.Collection;
import us.devtechsolutions.metafab.model.contract.BaseContract;
import us.devtechsolutions.metafab.model.contract.Contract;
import us.devtechsolutions.metafab.model.contract.write.BaseContractWrite;
import us.devtechsolutions.metafab.model.contract.write.ContractWrite;
import us.devtechsolutions.metafab.model.currency.BaseCurrency;
import us.devtechsolutions.metafab.model.currency.Currency;
import us.devtechsolutions.metafab.model.currency.fees.BaseCurrencyFees;
import us.devtechsolutions.metafab.model.currency.fees.CurrencyFees;
import us.devtechsolutions.metafab.model.ecosystem.BaseEcoSystem;
import us.devtechsolutions.metafab.model.ecosystem.EcoSystem;
import us.devtechsolutions.metafab.model.game.BaseGame;
import us.devtechsolutions.metafab.model.game.Game;
import us.devtechsolutions.metafab.model.item.BaseItem;
import us.devtechsolutions.metafab.model.item.Item;
import us.devtechsolutions.metafab.model.player.BaseUser;
import us.devtechsolutions.metafab.model.player.CCPlayer;
import us.devtechsolutions.metafab.model.player.User;
import us.devtechsolutions.metafab.model.transaction.BaseTransaction;
import us.devtechsolutions.metafab.model.transaction.Transaction;
import us.devtechsolutions.metafab.provider.PluginProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author LBuke (Teddeh)
 */
@SuppressWarnings("DuplicatedCode")
@ApiStatus.NonExtendable
public final class EndpointUtil {
	private static final Gson GSON = new Gson();
	private static final OkHttpClient CLIENT = new OkHttpClient();

	@Blocking
	@ApiStatus.Internal
	public static @NotNull Code fetchCode(@NotNull UUID playerUniqueId, @NotNull String username,
	                                      @NotNull String serverId, @NotNull String gameId) {
		final String url = "https://api.cubecolony.net/v1/metafab/code?id=%s&username=%s&server_id=%s&game_id=%s"
				.formatted(playerUniqueId.toString(), username, serverId, gameId);
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, Code.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @Nullable EcoSystem fetchEcoSystem(@NotNull String ecoSystemId) {
		final PluginProvider provider = PluginProvider.of();
		if (!provider.hasEcosystem())
			return null;

		final String url = "https://api.trymetafab.com/v1/ecosystems/%s".formatted(ecoSystemId);
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, BaseEcoSystem.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull Game fetchGame(@NotNull String gameId) {
		final String url = "https://api.trymetafab.com/v1/games/%s".formatted(gameId);
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, BaseGame.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull User fetchUser(@NotNull String playerId) {
		final String url = "https://api.trymetafab.com/v1/players/%s".formatted(playerId);
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, BaseUser.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull CCPlayer fetchUserFromCC(@NotNull String playerId, @NotNull String gameId) {
		final String url = "https://api.cubecolony.net/v1/user?id=%s&game_id=%s".formatted(playerId, gameId);
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, CCPlayer.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull List<Contract> fetchContracts(@NotNull String gamePublishedKey) {
		final String url = "https://api.trymetafab.com/v1/contracts";
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("X-Game-Key", gamePublishedKey)
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			final List<BaseContract> list = GSON.fromJson(json, TypeToken.getParameterized(List.class, BaseContract.class).getType());
			return list.stream().map(c -> (Contract) c).collect(Collectors.toList());
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull List<Currency> fetchCurrencies(@NotNull String gamePublishedKey) {
		final String url = "https://api.trymetafab.com/v1/currencies";
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("X-Game-Key", gamePublishedKey)
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			final List<BaseCurrency> list = GSON.fromJson(json, TypeToken.getParameterized(List.class, BaseCurrency.class).getType());
			return list.stream().map(c -> (Currency) c).collect(Collectors.toList());
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull List<Collection> fetchCollections(@NotNull String gamePublishedKey) {
		final String url = "https://api.trymetafab.com/v1/collections";
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("X-Game-Key", gamePublishedKey)
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			final List<BaseCollection> list = GSON.fromJson(json, TypeToken.getParameterized(List.class, BaseCollection.class).getType());
			return list.stream().map(c -> (Collection) c).collect(Collectors.toList());
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull List<Item> fetchItems(@NotNull String collectionId) {
		final String url = "https://api.trymetafab.com/v1/collections/%s/items".formatted(collectionId);
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			final List<BaseItem> list = GSON.fromJson(json, TypeToken.getParameterized(List.class, BaseItem.class).getType());
			return list.stream().map(i -> (Item) i).collect(Collectors.toList());
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static double fetchCurrencyBalance(@NotNull String currencyId, @NotNull String address) {
		final String url = "https://api.trymetafab.com/v1/currencies/%s/balances?address=%s".formatted(currencyId, address);
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String value = body.string();
			return Double.parseDouble(value);
		} catch (IOException | NumberFormatException exception) {
			if (exception instanceof NumberFormatException)
				return 0.0;
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull Transaction burnUserCurrency(@NotNull String currencyId, @NotNull String accessToken,
	                                                    @NotNull String walletDecryptKey, double amount) {
		final String url = "https://api.trymetafab.com/v1/currencies/%s/burns".formatted(currencyId);
		final String data = "{\"amount\":%s}".formatted(amount);

		final MediaType mediaType = MediaType.parse("application/json");
		final RequestBody requestBody = RequestBody.create(mediaType, data);
		final Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.addHeader("accept", "application/json")
				.addHeader("X-Authorization", accessToken)
				.addHeader("X-Wallet-Decrypt-Key", walletDecryptKey)
				.addHeader("content-type", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, BaseTransaction.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull CurrencyFees fetchCurrencyFees(@NotNull String currencyId) {
		final String url = "https://api.trymetafab.com/v1/currencies/%s/fees".formatted(currencyId);
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, BaseCurrencyFees.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull Transaction mintCurrency(@NotNull String currencyId, @NotNull String address, double amount,
	                                                     @NotNull String gameSecretKey, @NotNull String gameWalletDecryptKey) {
		final String url = "https://api.trymetafab.com/v1/currencies/%s/mints".formatted(currencyId);
		final String data = "{\"amount\":%s,\"address\":\"%s\"}".formatted(amount, address);

		final MediaType mediaType = MediaType.parse("application/json");
		final RequestBody requestBody = RequestBody.create(mediaType, data);
		final Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.addHeader("accept", "application/json")
				.addHeader("X-Authorization", gameSecretKey)
				.addHeader("X-Wallet-Decrypt-Key", gameWalletDecryptKey)
				.addHeader("content-type", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, BaseTransaction.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static int fetchCollectionItemBalance(@NotNull String collectionId, long itemId, @NotNull String address) {
		final String url = "https://api.trymetafab.com/v1/collections/%s/items/%s/balances?address=%s"
				.formatted(collectionId, itemId, address);
		final Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("accept", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			try {
				return Integer.parseInt(body.string().replaceAll("\\u0022", ""));
			} catch (NumberFormatException exception) {
				exception.printStackTrace();
				return 0;
			}
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull ContractWrite writeContractData(@NotNull String contractId, @NotNull Object[] args,
	                                                       @NotNull String functionName, @Nullable Double value) {
		final String url = "https://api.trymetafab.com/v1/contracts/%s/writes".formatted(contractId);

		final StringBuilder parsedArgs = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			final Object arg = args[i];
			if (arg instanceof Byte o) parsedArgs.append(o.byteValue()).append(',');
			else if (arg instanceof Short o) parsedArgs.append(o.shortValue()).append(',');
			else if (arg instanceof Integer o) parsedArgs.append(o.intValue()).append(',');
			else if (arg instanceof Long o) parsedArgs.append(o.longValue()).append(',');
			else if (arg instanceof Double o) parsedArgs.append(o.doubleValue()).append(',');
			else if (arg instanceof Float o) parsedArgs.append(o.floatValue()).append(',');
			else if (arg instanceof String o) parsedArgs.append('"').append(o).append('"').append(',');
			else if (arg instanceof Boolean o) parsedArgs.append(o).append(',');
		}
		parsedArgs.setLength(parsedArgs.length() - 1);

		String data = "{\"args\":[%s], \"func\":\"%s\"}".formatted(parsedArgs, functionName);
		if (!Objects.isNull(value)) {
			data = "{\"args\":[%s], \"func\":\"%s\", \"value\":\"%s\"}".formatted(parsedArgs, functionName, value);
		}

		final MediaType mediaType = MediaType.parse("application/json");
		final RequestBody requestBody = RequestBody.create(mediaType, data);
		final Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.addHeader("accept", "application/json")
				.addHeader("content-type", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, BaseContractWrite.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Blocking
	@ApiStatus.Internal
	public static @NotNull Transaction burnCollectionItem(@NotNull String collectionId,
	                                                        long itemId,
	                                                        int quantity,
	                                                        @NotNull String accessToken,
	                                                        @NotNull String walletDecryptKey) {
		final String url = "https://api.trymetafab.com/v1/collections/%s/items/%s/burns".formatted(collectionId, itemId);

		final MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType, "{\"quantity\":1}");
		final Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
                .addHeader("accept", "application/json")
				.addHeader("X-Authorization", accessToken)
				.addHeader("X-Wallet-Decrypt-Key", walletDecryptKey)
				.addHeader("content-type", "application/json")
				.build();

		try (final Response response = CLIENT.newCall(request).execute()) {
			final ResponseBody body = response.body();

			if (response.code() != 200)
				throw new RuntimeException(body.string());

			final String json = body.string();
			return GSON.fromJson(json, BaseTransaction.class);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
