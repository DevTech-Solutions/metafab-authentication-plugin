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
public final class BaseAbi implements Abi {

	private final String name;
	private final String type;
	private final String stateMutability;
	private final List<BaseInput> inputs;
	private final List<BaseOutput> outputs;
	private final boolean anonymous;

	public BaseAbi(@NotNull String name, @NotNull String type, @NotNull String stateMutability,
	               @NotNull List<BaseInput> inputs, @NotNull List<BaseOutput> outputs, boolean anonymous) {
		this.name = name;
		this.type = type;
		this.stateMutability = stateMutability;
		this.inputs = inputs;
		this.outputs = outputs;
		this.anonymous = anonymous;
	}

	/**
	 * Get the name of the abi.
	 *
	 * @return abi name
	 */
	@Override
	public @UnknownNullability String name() {
		return this.name;
	}

	/**
	 * Get the type of the abi.
	 *
	 * @return abi type
	 */
	@Override
	public @UnknownNullability String type() {
		return this.type;
	}

	/**
	 * Get the state mutability of the abi.
	 *
	 * @return abi state mutability
	 */
	@Override
	public @UnknownNullability String stateMutability() {
		return this.stateMutability;
	}

	/**
	 * Get the inputs for the abi.
	 *
	 * @return abi inputs
	 */
	@Override
	public @UnknownNullability List<Input> inputs() {
		return this.inputs.stream().map(i -> (Input) i).collect(Collectors.toList());
	}

	/**
	 * Get the outputs for the abi.
	 *
	 * @return abi outputs
	 */
	@Override
	public @UnknownNullability List<Output> outputs() {
		return this.outputs.stream().map(o -> (Output) o).collect(Collectors.toList());
	}

	/**
	 * Get whether the abi is anonymous.
	 *
	 * @return true if anonymous, otherwise false
	 */
	@Override
	public boolean anonymous() {
		return this.anonymous;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "BaseAbi{" +
				"type='" + type + '\'' +
				", inputs=" + inputs +
				", stateMutability='" + stateMutability + '\'' +
				", name='" + name + '\'' +
				", anonymous=" + anonymous +
				", outputs=" + outputs +
				'}';
	}
}
