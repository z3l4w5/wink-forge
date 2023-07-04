package cn.foofun.forge;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

class Conversion {

    private static final String CONVERSION_SPACE_CASE = "space case";
    private static final String CONVERSION_KEBAB_CASE = "kebab-case";
    private static final String CONVERSION_UPPER_SNAKE_CASE = "SNAKE_CASE";
    private static final String CONVERSION_PASCAL_CASE = "CamelCase";
    private static final String CONVERSION_CAMEL_CASE = "camelCase";
    private static final String CONVERSION_PASCAL_CASE_SPACE = "Camel Case";
    private static final String CONVERSION_LOWER_SNAKE_CASE = "snake_case";

    @NotNull
    static String transform(String text,
                            boolean usePascalCaseWithSpace,
                            boolean useSpaceCase,
                            boolean useKebabCase,
                            boolean useUpperSnakeCase,
                            boolean usePascalCase,
                            boolean useCamelCase,
                            boolean useLowerSnakeCase,
                            String[] conversionList) {
        String newText, appendText = "";
        boolean repeat = true;
        int iterations = 0;
        String next = null;

        Pattern p = Pattern.compile("^\\W+");
        Matcher m = p.matcher(text);
        if (m.find()) {
            appendText = m.group(0);
        }
        //remove all special chars
        text = text.replaceAll("^\\W+", "");

        do {
            newText = text;
            boolean isLowerCase = text.equals(text.toLowerCase());
            boolean isUpperCase = text.equals(text.toUpperCase());

            if (isLowerCase && text.contains("_")) {
                // snake_case to space case
                if (next == null) {
                    next = getNext(CONVERSION_LOWER_SNAKE_CASE, conversionList);
                } else {
                    if (next.equals(CONVERSION_SPACE_CASE)) {
                        repeat = !useSpaceCase;
                        next = getNext(CONVERSION_SPACE_CASE, conversionList);
                    }
                }
                newText = text.replace('_', ' ');

            } else if (isLowerCase && text.contains(" ")) {
                // space case to Camel Case
                if (next == null) {
                    next = getNext(CONVERSION_SPACE_CASE, conversionList);
                } else {
                    newText = capitalize(text);
                    if (next.equals(CONVERSION_PASCAL_CASE_SPACE)) {
                        repeat = !usePascalCaseWithSpace;
                        next = getNext(CONVERSION_PASCAL_CASE_SPACE, conversionList);
                    }
                }

            } else if (isUpperCase(text.charAt(0)) && isLowerCase(text.charAt(1)) && text.contains(" ")) {
                // Camel Case to kebab-case
                if (next == null) {
                    next = getNext(CONVERSION_PASCAL_CASE_SPACE, conversionList);
                } else {
                    newText = text.toLowerCase().replace(' ', '-');
                    if (next.equals(CONVERSION_KEBAB_CASE)) {
                        repeat = !useKebabCase;
                        next = getNext(CONVERSION_KEBAB_CASE, conversionList);
                    }
                }

            } else if (isLowerCase && text.contains("-") || (isLowerCase && !text.contains(" "))) {
                // kebab-case to SNAKE_CASE
                if (next == null) {
                    next = getNext(CONVERSION_KEBAB_CASE, conversionList);
                } else {
                    newText = text.replace('-', '_').toUpperCase();
                    if (next.equals(CONVERSION_UPPER_SNAKE_CASE)) {
                        repeat = !useUpperSnakeCase;
                        next = getNext(CONVERSION_UPPER_SNAKE_CASE, conversionList);
                    }
                }

            } else if ((isUpperCase && text.contains("_")) || (isLowerCase && !text.contains("_") && !text.contains(" ")) || (isUpperCase && !text.contains(" "))) {
                // SNAKE_CASE to PascalCase
                if (next == null) {
                    next = getNext(CONVERSION_UPPER_SNAKE_CASE, conversionList);
                } else {
                    newText = Conversion.toCamelCase(text.toLowerCase());
                    if (next.equals(CONVERSION_PASCAL_CASE)) {
                        repeat = !usePascalCase;
                        next = getNext(CONVERSION_PASCAL_CASE, conversionList);
                    }
                }

            } else if (!isUpperCase && text.substring(0, 1).equals(text.substring(0, 1).toUpperCase()) && !text.contains("_")) {
                // PascalCase to camelCase
                if (next == null) {
                    next = getNext(CONVERSION_PASCAL_CASE, conversionList);
                } else {
                    newText = text.substring(0, 1).toLowerCase() + text.substring(1);
                    if (next.equals(CONVERSION_CAMEL_CASE)) {
                        repeat = !useCamelCase;
                        next = getNext(CONVERSION_CAMEL_CASE, conversionList);
                    }
                }
            } else {
                // camelCase to snake_case
                if (next == null) {
                    next = getNext(CONVERSION_CAMEL_CASE, conversionList);
                } else {
                    newText = Conversion.toSnakeCase(text);
                    if (next.equals(CONVERSION_LOWER_SNAKE_CASE)) {
                        repeat = !useLowerSnakeCase;
                        next = getNext(CONVERSION_LOWER_SNAKE_CASE, conversionList);
                    }
                }
            }
            if (iterations++ > 20) {
                repeat = false;
            }
            text = newText;
        } while (repeat);

        return appendText + newText;
    }

    /**
     * Return next conversion (or wrap to first)
     *
     * @param conversion  String
     * @param conversions Array of strings
     * @return next conversion
     */
    private static String getNext(String conversion, String[] conversions) {
        int index;
        index = Arrays.asList(conversions).indexOf(conversion) + 1;
        if (index < conversions.length) {
            return conversions[index];
        } else {
            return conversions[0];
        }
    }

    /**
     * Convert a string (CamelCase) to snake_case
     *
     * @param in CamelCase string
     * @return snake_case String
     */
    private static String toSnakeCase(String in) {
        in = in.replaceAll(" +", "");
        StringBuilder result = new StringBuilder("" + Character.toLowerCase(in.charAt(0)));
        for (int i = 1; i < in.length(); i++) {
            char c = in.charAt(i);
            if (isUpperCase(c)) {
                result.append("_").append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Convert a string (snake_case) to CamelCase
     *
     * @param in snake_case String
     * @return CamelCase string
     */
    private static String toCamelCase(String in) {
        StringBuilder camelCased = new StringBuilder();
        String[] tokens = in.split("_");
        for (String token : tokens) {
            if (token.length() >= 1) {
                camelCased.append(token.substring(0, 1).toUpperCase()).append(token.substring(1));
            } else {
                camelCased.append("_");
            }
        }
        return camelCased.toString();
    }

    public static String capitalize(final String str, final char... delimiters) {
        final int delimLen = delimiters == null ? -1 : delimiters.length;
        if (isEmpty(str) || delimLen == 0) {
            return str;
        }
        final char[] buffer = str.toCharArray();
        boolean capitalizeNext = true;
        for (int i = 0; i < buffer.length; i++) {
            final char ch = buffer[i];
            if (isDelimiter(ch, delimiters)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                buffer[i] = Character.toTitleCase(ch);
                capitalizeNext = false;
            }
        }
        return new String(buffer);
    }

    //-----------------------------------------------------------------------

    /**
     * Is the character a delimiter.
     *
     * @param ch         the character to check
     * @param delimiters the delimiters
     * @return true if it is a delimiter
     */
    private static boolean isDelimiter(final char ch, final char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (final char delimiter : delimiters) {
            if (ch == delimiter) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}