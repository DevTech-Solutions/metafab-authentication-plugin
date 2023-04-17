package us.devtechsolutions.metafab.http.message;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class ServerConnection implements Serializable {

	@Since(1.0)
	@SerializedName("type")
	private final MessageType type;

	@Since(1.0)
	@SerializedName("time")
	private final long time;

	@Since(1.0)
	@SerializedName("serverId")
	private final UUID serverId;

	@Since(1.0)
	@SerializedName("gameId")
	private final UUID gameId;

	public ServerConnection(@NotNull UUID serverId, @NotNull UUID gameId) {
		this.type = MessageType.CONNECTION;
		this.time = System.currentTimeMillis();
		this.serverId = serverId;
		this.gameId = gameId;
	}

	public @NotNull MessageType type() {
		return this.type;
	}

	public long time() {
		return this.time;
	}

	public @NotNull UUID serverId() {
		return this.serverId;
	}

	public @NotNull UUID gameId() {
		return this.gameId;
	}
}
