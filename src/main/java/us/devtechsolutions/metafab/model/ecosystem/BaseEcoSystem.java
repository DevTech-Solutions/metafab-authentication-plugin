package us.devtechsolutions.metafab.model.ecosystem;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author LBuke (Teddeh)
 */
@ApiStatus.NonExtendable
public final class BaseEcoSystem implements EcoSystem {

	private final String id;
	private final String name;
	private final String publishedKey;
	private final String iconImageUrl;
	private final String coverImageUrl;
	private final String primaryColorHex;

	/**
	 * Construct a new MetaFab Ecosystem.
	 *
	 * @param id           - the ecosystem id
	 * @param name         - the ecosystem name
	 * @param publishedKey - the published key
	 */
	BaseEcoSystem(@NotNull String id, @NotNull String name,
	              @NotNull String publishedKey, @NotNull String iconImageUrl,
	              @NotNull String coverImageUrl, @NotNull String primaryColorHex) {
		this.id = id;
		this.name = name;
		this.publishedKey = publishedKey;
		this.iconImageUrl = iconImageUrl;
		this.coverImageUrl = coverImageUrl;
		this.primaryColorHex = primaryColorHex;
	}

	/**
	 * Get the ecosystem id.
	 *
	 * @return the ecosystem id
	 */
	@Override
	public @NotNull UUID id() {
		return UUID.fromString(this.id);
	}

	/**
	 * Get the ecosystem name.
	 *
	 * @return the ecosystem name
	 */
	@Override
	public @NotNull String name() {
		return this.name;
	}

	/**
	 * Get the ecosystem's published key.
	 *
	 * @return the ecosystem's published key
	 */
	@Override
	public @NotNull String publishedKey() {
		return this.publishedKey;
	}

	/**
	 * Get the ecosystem's icon image url.
	 *
	 * @return the icon image url
	 */
	@Override
	public @NotNull String iconImageUrl() {
		return this.iconImageUrl;
	}

	/**
	 * Get the ecosystem's cover image url.
	 *
	 * @return the cover image url
	 */
	@Override
	public @NotNull String coverImageUrl() {
		return this.coverImageUrl;
	}

	/**
	 * Get the ecosystem's primary color hex.
	 *
	 * @return the primary color (hex)
	 */
	@Override
	public @NotNull String primaryColorHex() {
		return this.primaryColorHex;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "BaseMetaFabEcoSystem{\n" +
				" id='" + id + "',\n" +
				" name='" + name + "',\n" +
				" publishedKey='" + publishedKey + "',\n" +
				" iconImageUrl='" + iconImageUrl + "',\n" +
				" coverImageUrl='" + coverImageUrl + "',\n" +
				" primaryColorHex='" + primaryColorHex + "',\n" +
				'}';
	}
}
