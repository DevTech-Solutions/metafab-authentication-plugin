package us.devtechsolutions.metafab.model.game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseGame implements Game {

	private final String id;
	private final String name;
	private final String publishedKey;
	private final String[] redirectUris;
	private final String discordClientId;

	/**
	 * Construct a new MetaFab Game.
	 *
	 * @param id              - the game's id
	 * @param name            - the game name
	 * @param publishedKey    - the game's published key
	 * @param redirectUris    - the game's redirect uri's
	 * @param discordClientId - the game's discord client id
	 */
	public BaseGame(@NotNull String id, @NotNull String name, @NotNull String publishedKey,
	                @NotNull String[] redirectUris, @NotNull String discordClientId) {
		this.id = id;
		this.name = name;
		this.publishedKey = publishedKey;
		this.redirectUris = redirectUris;
		this.discordClientId = discordClientId;
	}

	/**
	 * Get the game's id.
	 *
	 * @return the game's id
	 */
	@Override
	public @NotNull UUID id() {
		return UUID.fromString(this.id);
	}

	/**
	 * Get the name of the game.
	 *
	 * @return the game name
	 */
	@Override
	public @Nullable String name() {
		return this.name;
	}

	/**
	 * Get the published key for the game.
	 *
	 * @return the game's published key
	 */
	@Override
	public @NotNull String publishedKey() {
		return this.publishedKey;
	}

	/**
	 * Get the redirect uri's for the game.
	 *
	 * @return the game's redirect uri's
	 */
	@Override
	public @NotNull String[] redirectUris() {
		return this.redirectUris;
	}

	/**
	 * Get the discord client id for the game.
	 *
	 * @return the game's discord client id
	 */
	@Override
	public @NotNull String discordClientId() {
		return this.discordClientId;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "BaseMetaFabGame{\n" +
				" id='" + id + "',\n" +
				" name='" + name + "',\n" +
				" publishedKey='" + publishedKey + "',\n" +
				" redirectUris='" + Arrays.toString(redirectUris) + "',\n" +
				" discordClientId='" + discordClientId + "',\n" +
				'}';
	}
}
