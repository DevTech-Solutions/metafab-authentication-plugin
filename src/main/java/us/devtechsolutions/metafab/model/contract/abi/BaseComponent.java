package us.devtechsolutions.metafab.model.contract.abi;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

/**
 * @author LBuke (Teddeh)
 */
@ApiStatus.NonExtendable
public final class BaseComponent implements Component {

	private final String name;
	private final String type;
	private final String internalType;

	public BaseComponent(@NotNull String name, @NotNull String type, @NotNull String internalType) {
		this.name = name;
		this.type = type;
		this.internalType = internalType;
	}

	/**
	 * Get the name of the component.
	 *
	 * @return component name
	 */
	@Override
	public @UnknownNullability String name() {
		return this.name;
	}

	/**
	 * Get the type of the component.
	 *
	 * @return component type
	 */
	@Override
	public @UnknownNullability String type() {
		return this.type;
	}

	/**
	 * Get the internal type of the component.
	 *
	 * @return component internal type
	 */
	@Override
	public @UnknownNullability String internalType() {
		return this.internalType;
	}

	@Override
	public String toString() {
		return "BaseComponent{" +
				"name='" + name + '\'' +
				", type='" + type + '\'' +
				", internalType='" + internalType + '\'' +
				'}';
	}
}
