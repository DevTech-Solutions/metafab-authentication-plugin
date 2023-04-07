package us.devtechsolutions.metafab.util;

import org.jetbrains.annotations.ApiStatus;

/**
 * @author LBuke (Teddeh)
 */
@ApiStatus.NonExtendable
public final class StringUtil {
	private static final int CENTER_PX = 154;
	private static final int CENTER_MENU_PX = 80;

	public static String getCenteredMessage(String input) {
		return getCentered(input, CENTER_PX);
	}

	public static String getCenteredMenuText(String input) {
		return getCentered(input, CENTER_MENU_PX);
	}

	private static String getCentered(String input, int value) {
		int halvedMessageSize = getPixelLength(input) / 2;
		int toCompensate = value - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while (compensated < toCompensate) {
			sb.append(" ");
			compensated += spaceLength;
		}

		return sb.toString() + input;
	}

	public static int getPixelLength(String input) {
		input = C.translate(input);

		int pixels = 0;
		boolean previousCode = false;
		boolean isBold = false;

		for (char c : input.toCharArray()) {
			if (c == C.COLOR_CHAR)
				previousCode = true;

			else if (previousCode) {
				previousCode = false;
				isBold = c == 'l' || c == 'L';
			}

			else {
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				pixels += isBold ? dFI.getBoldLength() : dFI.getLength();
				pixels++;
			}
		}

		return pixels;
	}
}
