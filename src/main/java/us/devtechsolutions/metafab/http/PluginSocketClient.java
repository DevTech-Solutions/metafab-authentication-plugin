package us.devtechsolutions.metafab.http;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.http.message.ServerConnection;
import us.devtechsolutions.metafab.player.PlayerManager;
import us.devtechsolutions.metafab.provider.PluginProvider;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class PluginSocketClient {
	private static final Gson GSON = new Gson();

	public static boolean connected = false;
	public static boolean reconnecting = false;

	private WebSocket webSocket;

	public PluginSocketClient(@NotNull JavaPlugin plugin, @NotNull PlayerManager playerManager) {
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
			try {
				final URI uri = new URI("wss://api.cubecolony.net");
				this.webSocket = new WebSocket(plugin, playerManager, uri);
				this.webSocket.setConnectionLostTimeout(0);

				this.webSocket.connect();

				while (!connected)
					Thread.onSpinWait();

				final PluginProvider provider = PluginProvider.of();
				final ServerConnection serverConnection = new ServerConnection(provider.serverId(), UUID.fromString(provider.gameId()));
				this.send(serverConnection);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}
		});
	}

	public void close() {
		if (Objects.isNull(this.webSocket))
			return;

		this.webSocket.close();
	}

	public void send(@NotNull Object object) {
		if (Objects.isNull(this.webSocket))
			return;

		this.webSocket.send(GSON.toJson(object));
	}
}
