package us.devtechsolutions.metafab.model.contract.abi;

import org.jetbrains.annotations.UnknownNullability;

import java.util.List;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Output permits BaseOutput {

	/**
	 * Get the name of the output.
	 *
	 * @return output name
	 */
	@UnknownNullability String name();

	/**
	 * Get the type of the output.
	 *
	 * @return output type
	 */
	@UnknownNullability String type();

	/**
	 * Get the internal type of the output.
	 *
	 * @return output internal type
	 */
	@UnknownNullability String internalType();

	/**
	 * Get a list of component for the output.
	 *
	 * @return output components
	 */
	@UnknownNullability List<Component> components();
}
