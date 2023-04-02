package us.devtechsolutions.metafab.model.ecosystem;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
@ApiStatus.NonExtendable
public sealed interface EcoSystem permits BaseEcoSystem {

	/**
	 * Get the ecosystem id.
	 *
	 * @return the ecosystem id
	 */
	@NotNull UUID id();

	/**
	 * Get the ecosystem name.
	 *
	 * @return the ecosystem name
	 */
	@NotNull String name();

	/**
	 * Get the ecosystem's published key.
	 *
	 * @return the published key
	 */
	@NotNull String publishedKey();

	/**
	 * Get the ecosystem's icon image url.
	 *
	 * @return the icon image url
	 */
	@NotNull String iconImageUrl();

	/**
	 * Get the ecosystem's cover image url.
	 *
	 * @return the cover image url
	 */
	@NotNull String coverImageUrl();

	/**
	 * Get the ecosystem's primary color hex.
	 *
	 * @return the primary color (hex)
	 */
	@NotNull String primaryColorHex();
}
