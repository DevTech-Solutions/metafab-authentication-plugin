package us.devtechsolutions.metafab.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.http.message.MessageType;
import us.devtechsolutions.metafab.http.message.PlayerAuthentication;
import us.devtechsolutions.metafab.model.player.CCPlayer;
import us.devtechsolutions.metafab.player.PlayerManager;
import us.devtechsolutions.metafab.provider.PluginProvider;
import us.devtechsolutions.metafab.util.EndpointUtil;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class WebSocket extends WebSocketClient {
	private static final Gson GSON = new Gson();
	private static final int MAX_RETRIES = 30;

	private final JavaPlugin plugin;
	private final PlayerManager playerManager;

	private BukkitTask task;

	public WebSocket(@NotNull JavaPlugin plugin, @NotNull PlayerManager playerManager, @NotNull URI uri) {
		super(uri);
		this.plugin = plugin;
		this.playerManager = playerManager;
	}

	/** {@inheritDoc} */
	@Override
	public void onOpen(ServerHandshake handshake) {
		this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(this.plugin, () -> {
			if (PluginSocketClient.connected) {
				this.sendPing();
			}
		}, 20 * 10L, 20 * 10L);

		PluginSocketClient.connected = true;
	}

	/** {@inheritDoc} */
	@Override
	public void onMessage(String message) {
		final JsonElement jsonElement = JsonParser.parseString(message);
		final JsonObject jsonObject = jsonElement.getAsJsonObject();
		if (!jsonObject.has("type"))
			return;

		final MessageType type = MessageType.valueOf(jsonObject.get("type").getAsString());

		switch (type) {
			case PLAYER_AUTHENTICATION -> {
				final PlayerAuthentication authentication = GSON.fromJson(jsonObject, PlayerAuthentication.class);

				final PluginProvider provider = PluginProvider.of();
				if (!provider.serverId().equals(authentication.serverId()))
					return;

				provider.runAsync(() -> {
					final CCPlayer ccPlayer = EndpointUtil.fetchUserFromCC(authentication.playerUniqueId(), provider.gameId());
					if (ccPlayer.metafabId().isEmpty()) {
						return; // Hasn't verified.
					}

					final UUID playerUniqueId = UUID.fromString(authentication.playerUniqueId());
					final Player player = Bukkit.getPlayer(playerUniqueId);
					if (Objects.isNull(player))
						return;

					this.playerManager.fetchUser(playerUniqueId, ccPlayer);

					provider.runSync(() -> {
						player.sendMessage("Successfully Authenticated, %s".formatted(authentication.metafabUsername()));
					});
				});
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void onClose(int code, String reason, boolean remote) {
		connect();
	}

	/** {@inheritDoc} */
	@Override
	public void onError(Exception exception) {
		exception.printStackTrace();
	}

	/** {@inheritDoc} */
	@Override
	public void onClosing(int code, String reason, boolean remote) {
		PluginSocketClient.connected = false;

		if (!Objects.isNull(this.task)) {
			this.task.cancel();
		}
	}
}
