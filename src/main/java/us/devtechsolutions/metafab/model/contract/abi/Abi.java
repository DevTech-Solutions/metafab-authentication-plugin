package us.devtechsolutions.metafab.model.contract.abi;

import org.jetbrains.annotations.UnknownNullability;

import java.util.List;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Abi permits BaseAbi {

	/**
	 * Get the name of the abi.
	 *
	 * @return abi name
	 */
	@UnknownNullability String name();

	/**
	 * Get the type of the abi.
	 *
	 * @return abi type
	 */
	@UnknownNullability String type();

	/**
	 * Get the state mutability of the abi.
	 *
	 * @return abi state mutability
	 */
	@UnknownNullability String stateMutability();

	/**
	 * Get the inputs for the abi.
	 *
	 * @return abi inputs
	 */
	@UnknownNullability List<Input> inputs();

	/**
	 * Get the outputs for the abi.
	 *
	 * @return abi outputs
	 */
	@UnknownNullability List<Output> outputs();

	/**
	 * Get whether the abi is anonymous.
	 *
	 * @return true if anonymous, otherwise false
	 */
	boolean anonymous();
}
