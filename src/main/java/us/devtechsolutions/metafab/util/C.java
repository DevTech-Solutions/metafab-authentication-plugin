package us.devtechsolutions.metafab.util;

import com.google.common.collect.Maps;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.ApiStatus;

import java.awt.*;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author LBuke (Teddeh)
 */
@ApiStatus.NonExtendable
public final class C {

    private static final Map<Character, String> BY_CHAR = Maps.newHashMap();
    public static final char COLOR_CHAR = '§';
    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + COLOR_CHAR + "[0-9A-FK-OR]");

    public static final String BOLD = COLOR_CHAR + "l";
    public static final String STRIKE = COLOR_CHAR + "m";
    public static final String UNDERLINE = COLOR_CHAR + "n";
    public static final String MAGIC = COLOR_CHAR + "k";
    public static final String ITALIC = COLOR_CHAR + "o";
    public static final String RESET = COLOR_CHAR + "r";

    public static final String BLACK = COLOR_CHAR + "0";
    public static final String DARK_BLUE = COLOR_CHAR + "1";
    public static final String DARK_GREEN = COLOR_CHAR + "2";
    public static final String DARK_AQUA = COLOR_CHAR + "3";
    public static final String DARK_RED = COLOR_CHAR + "4";
    public static final String DARK_PURPLE = COLOR_CHAR + "5";
    public static final String GOLD = COLOR_CHAR + "6";
    public static final String GRAY = COLOR_CHAR + "7";
    public static final String DARK_GRAY = COLOR_CHAR + "8";
    public static final String BLUE = COLOR_CHAR + "9";
    public static final String GREEN = COLOR_CHAR + "a";
    public static final String AQUA = COLOR_CHAR + "b";
    public static final String RED = COLOR_CHAR + "c";
    public static final String LIGHT_PURPLE = COLOR_CHAR + "d";
    public static final String YELLOW = COLOR_CHAR + "e";
    public static final String WHITE = COLOR_CHAR + "f";

    public static final String LIME = ChatColor.of(new Color(183, 255, 0)).toString();

    public static final String INFO = ChatColor.of(new Color(247, 255, 191)).toString();
    public static final String CROSS = ChatColor.of(new Color(255, 190, 190)).toString();
    public static final String TICK = ChatColor.of(new Color(190, 255, 190)).toString();

    static {
        BY_CHAR.put('l', BOLD);
        BY_CHAR.put('m', STRIKE);
        BY_CHAR.put('n', UNDERLINE);
        BY_CHAR.put('k', MAGIC);
        BY_CHAR.put('o', ITALIC);
        BY_CHAR.put('r', RESET);

        BY_CHAR.put('0', BLACK);
        BY_CHAR.put('1', DARK_BLUE);
        BY_CHAR.put('2', DARK_GREEN);
        BY_CHAR.put('3', DARK_AQUA);
        BY_CHAR.put('4', DARK_RED);
        BY_CHAR.put('5', DARK_PURPLE);
        BY_CHAR.put('6', GOLD);
        BY_CHAR.put('7', GRAY);
        BY_CHAR.put('8', DARK_GRAY);
        BY_CHAR.put('9', BLUE);
        BY_CHAR.put('a', GREEN);
        BY_CHAR.put('b', AQUA);
        BY_CHAR.put('c', RED);
        BY_CHAR.put('d', LIGHT_PURPLE);
        BY_CHAR.put('e', YELLOW);
        BY_CHAR.put('f', WHITE);
    }

    public static String getByChar(char code) {
        return BY_CHAR.get(code);
    }

    public static boolean isColor(char code) {
        switch (code) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
                return true;

            default:
                return false;
        }
    }

    public static String strip(String input) {
        return input == null ? null : STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    public static String translate(String textToTranslate) {
        if (textToTranslate == null)
            return "";

        char[] b = textToTranslate.toCharArray();

        for(int i = 0; i < b.length - 1; ++i) {
            if (b[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = 167;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

    public static String getLastColors(String input) {
        StringBuilder result = new StringBuilder();
        int length = input.length();

        for(int index = length - 1; index > -1; --index) {
            char section = input.charAt(index);
            if (section == 167 && index < length - 1) {
                char c = input.charAt(index + 1);
                String color = getByChar(c);
                if (color != null) {
                    result.insert(0, color);
                    if (isColor(c) || color.equals(RESET)) {
                        break;
                    }
                }
            }
        }

        return result.toString();
    }

    public static void info(CommandSender commandSender, String message) {
        commandSender.sendMessage(C.translate("&e&lINFO&r &f» " + INFO + message));
        //[!!] INFO » Message here
    }

    public static void cross(CommandSender commandSender, String message) {
        commandSender.sendMessage(C.translate("&c&lCRITICAL&r &f» " + CROSS + message));
        //[✖] CRITICAL » Message here
    }

    public static void tick(CommandSender commandSender, String message) {
        commandSender.sendMessage(C.translate("&a&lSUCCESS&r &f» " + TICK + message));
        //[✔] SUCCESS » Message here
    }

    public static void crate(CommandSender commandSender, String message) {
        commandSender.sendMessage(C.translate("&d&lCRATES&r &f» &f" + message));
        //[✔] CRATES » Message here
    }
}

