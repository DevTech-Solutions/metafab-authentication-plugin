package us.devtechsolutions.metafab.model.contract.abi;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LBuke (Teddeh)
 */
@ApiStatus.NonExtendable
public final class BaseInput implements Input {

	private final String name;
	private final String type;
	private final String internalType;
	private final boolean indexed;
	private final List<BaseComponent> components;

	public BaseInput(@NotNull String name, @NotNull String type, @NotNull String internalType,
	                 boolean indexed, @NotNull List<BaseComponent> components) {
		this.name = name;
		this.type = type;
		this.internalType = internalType;
		this.indexed = indexed;
		this.components = components;
	}

	@Override
	public String toString() {
		return "BaseInput{" +
				"name='" + name + '\'' +
				", type='" + type + '\'' +
				", internalType='" + internalType + '\'' +
				", indexed=" + indexed +
				", components=" + components +
				'}';
	}

	/**
	 * Get the name of the input.
	 *
	 * @return input name
	 */
	@Override
	public @UnknownNullability String name() {
		return this.name;
	}

	/**
	 * Get the type of the input.
	 *
	 * @return input type
	 */
	@Override
	public @UnknownNullability String type() {
		return this.type;
	}

	/**
	 * Get the internal type of the input.
	 *
	 * @return input internal type
	 */
	@Override
	public @UnknownNullability String internalType() {
		return this.internalType;
	}

	/**
	 * Get whether the input is indexed.
	 *
	 * @return true if indexed, otherwise false
	 */
	@Override
	public @UnknownNullability boolean indexed() {
		return this.indexed;
	}

	/**
	 * Get a list of component for the input.
	 *
	 * @return input components
	 */
	@Override
	public @UnknownNullability List<Component> components() {
		return this.components.stream().map(c -> (Component) c).collect(Collectors.toList());
	}
}
