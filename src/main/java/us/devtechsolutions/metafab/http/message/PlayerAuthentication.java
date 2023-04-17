package us.devtechsolutions.metafab.http.message;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class PlayerAuthentication {

	@Since(1.0)
	@SerializedName("type")
	private MessageType type;

	@Since(1.0)
	@SerializedName("serverId")
	private String serverId;

	@Since(1.0)
	@SerializedName("playerUniqueId")
	private String playerUniqueId;

	@Since(1.0)
	@SerializedName("metafabId")
	private String metafabId;

	@Since(1.0)
	@SerializedName("metafabUsername")
	private String metafabUsername;

	@Since(1.0)
	@SerializedName("accessToken")
	private String accessToken;

	@Since(1.0)
	@SerializedName("walletDecryptKey")
	private String walletDecryptKey;

	public PlayerAuthentication(@NotNull MessageType type, @NotNull String serverId,
	                            @NotNull String playerUniqueId, @NotNull String metafabId,
	                            @NotNull String metafabUsername, @NotNull String accessToken,
	                            @NotNull String walletDecryptKey) {
		this.type = type;
		this.serverId = serverId;
		this.playerUniqueId = playerUniqueId;
		this.metafabId = metafabId;
		this.metafabUsername = metafabUsername;
		this.accessToken = accessToken;
		this.walletDecryptKey = walletDecryptKey;
	}

	public PlayerAuthentication(@NotNull String serverId, @NotNull String playerUniqueId,
	                            @NotNull String metafabId, @NotNull String metafabUsername,
	                            @NotNull String accessToken, @NotNull String walletDecryptKey) {
		this.type = MessageType.PLAYER_AUTHENTICATION;
		this.serverId = serverId;
		this.playerUniqueId = playerUniqueId;
		this.metafabId = metafabId;
		this.metafabUsername = metafabUsername;
		this.accessToken = accessToken;
		this.walletDecryptKey = walletDecryptKey;
	}

	public @NotNull MessageType type() {
		return this.type;
	}

	public @NotNull UUID serverId() {
		return UUID.fromString(this.serverId);
	}

	public @NotNull String playerUniqueId() {
		return this.playerUniqueId;
	}

	public @NotNull String metafabId() {
		return this.metafabId;
	}

	public @NotNull String metafabUsername() {
		return this.metafabUsername;
	}

	public @NotNull String accessToken() {
		return this.accessToken;
	}

	public @NotNull String walletDecryptKey() {
		return this.walletDecryptKey;
	}
}
