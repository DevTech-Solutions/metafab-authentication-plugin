package us.devtechsolutions.metafab.model.contract.abi;

import org.jetbrains.annotations.UnknownNullability;

import java.util.List;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Input permits BaseInput {

	/**
	 * Get the name of the input.
	 *
	 * @return input name
	 */
	@UnknownNullability String name();

	/**
	 * Get the type of the input.
	 *
	 * @return input type
	 */
	@UnknownNullability String type();

	/**
	 * Get the internal type of the input.
	 *
	 * @return input internal type
	 */
	@UnknownNullability String internalType();

	/**
	 * Get whether the input is indexed.
	 *
	 * @return true if indexed, otherwise false
	 */
	@UnknownNullability boolean indexed();

	/**
	 * Get a list of component for the input.
	 *
	 * @return input components
	 */
	@UnknownNullability List<Component> components();
}
