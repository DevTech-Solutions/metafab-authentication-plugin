package us.devtechsolutions.metafab.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.contract.BaseContract;
import us.devtechsolutions.metafab.model.contract.Contract;
import us.devtechsolutions.metafab.model.currency.BaseCurrency;
import us.devtechsolutions.metafab.model.currency.Currency;
import us.devtechsolutions.metafab.model.ecosystem.BaseEcoSystem;
import us.devtechsolutions.metafab.model.ecosystem.EcoSystem;
import us.devtechsolutions.metafab.model.game.BaseGame;
import us.devtechsolutions.metafab.model.game.Game;
import us.devtechsolutions.metafab.model.player.BaseUser;
import us.devtechsolutions.metafab.model.player.User;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LBuke (Teddeh)
 */
@ApiStatus.NonExtendable
public final class EndpointUtil {
	private static final Gson GSON = new Gson();
	private static final OkHttpClient CLIENT = new OkHttpClient();

	@Blocking
	@ApiStatus.Internal
	public static @NotNull EcoSystem fetchEcoSystem(@NotNull String ecoSystemId) {
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
			List<BaseContract> list = GSON.fromJson(json, TypeToken.getParameterized(List.class, BaseContract.class).getType());
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
			List<BaseCurrency> list = GSON.fromJson(json, TypeToken.getParameterized(List.class, BaseCurrency.class).getType());
			return list.stream().map(c -> (Currency) c).collect(Collectors.toList());
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
