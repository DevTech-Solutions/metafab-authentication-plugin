package us.devtechsolutions.metafab.util;

import org.jetbrains.annotations.NotNull;
import spark.Request;
import spark.Response;

/**
 * @author LBuke (Teddeh)
 */
public final class SparkUtil {

	public static @NotNull String format(@NotNull Request req) {
		final String str = """
					--- REQUEST ---
					protocol: %s
					requestMethod: %s
					uri: %s
					params: %s
					body: %s
					""";
		return str.formatted(req.protocol(), req.requestMethod(), req.uri(), req.params(), req.body());
	}

	public static @NotNull String format(@NotNull Response res) {
		final String str = """
					--- RESPONSE ---
					status: %s
					type: %s
					body: %s
					""";
		return str.formatted(res.status(), res.type(), res.body());
	}
}
