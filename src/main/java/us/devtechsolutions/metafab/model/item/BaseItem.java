package us.devtechsolutions.metafab.model.item;

import org.jetbrains.annotations.NotNull;
import us.devtechsolutions.metafab.model.item.attribute.Attribute;
import us.devtechsolutions.metafab.model.item.attribute.BaseAttribute;
import us.devtechsolutions.metafab.model.item.data.BaseData;
import us.devtechsolutions.metafab.model.item.data.Data;

/**
 * @author LBuke (Teddeh)
 */
public final class BaseItem implements Item {

	private final long id;
	private final String image;
	private final String name;
	private final String description;
	private final String externalUrl;
	private final BaseAttribute attribute;
	private final BaseData data;

	public BaseItem(long id, @NotNull String image, @NotNull String name, @NotNull String description,
	                @NotNull String externalUrl, @NotNull BaseAttribute attribute, @NotNull BaseData data) {
		this.id = id;
		this.image = image;
		this.name = name;
		this.description = description;
		this.externalUrl = externalUrl;
		this.attribute = attribute;
		this.data = data;
	}

	@Override
	public long id() {
		return this.id;
	}

	@Override
	public @NotNull String image() {
		return this.image;
	}

	@Override
	public @NotNull String name() {
		return this.name;
	}

	@Override
	public @NotNull String description() {
		return this.description;
	}

	@Override
	public @NotNull String externalUrl() {
		return this.externalUrl;
	}

	@Override
	public @NotNull Attribute attribute() {
		return this.attribute;
	}

	@Override
	public @NotNull Data data() {
		return this.data;
	}

	@Override
	public String toString() {
		return "BaseItem{" +
				"id=" + id +
				", image='" + image + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", externalUrl='" + externalUrl + '\'' +
				", attribute=" + attribute +
				", data=" + data +
				'}';
	}
}
