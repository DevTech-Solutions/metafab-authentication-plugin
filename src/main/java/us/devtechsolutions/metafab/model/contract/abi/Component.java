package us.devtechsolutions.metafab.model.contract.abi;

import org.jetbrains.annotations.UnknownNullability;

/**
 * @author LBuke (Teddeh)
 */
public sealed interface Component permits BaseComponent {

	/**
	 * Get the name of the component.
	 *
	 * @return component name
	 */
	@UnknownNullability String name();

	/**
	 * Get the type of the component.
	 *
	 * @return component type
	 */
	@UnknownNullability String type();

	/**
	 * Get the internal type of the component.
	 *
	 * @return component internal type
	 */
	@UnknownNullability String internalType();
}
