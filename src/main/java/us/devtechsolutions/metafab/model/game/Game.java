package us.devtechsolutions.metafab.model.game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Game permits BaseGame {

	/**
	 * Get the game's id.
	 *
	 * @return the game's id
	 */
	@NotNull UUID id();

	/**
	 * Get the name of the game.
	 *
	 * @return the game name
	 */
	@Nullable String name();

	/**
	 * Get the published key for the game.
	 *
	 * @return the game's published key
	 */
	@NotNull String publishedKey();

	/**
	 * Get the redirect uri's for the game.
	 *
	 * @return the game's redirect uri's
	 */
	@NotNull String[] redirectUris();

	/**
	 * Get the discord client id for the game.
	 *
	 * @return the game's discord client id
	 */
	@NotNull String discordClientId();
}
