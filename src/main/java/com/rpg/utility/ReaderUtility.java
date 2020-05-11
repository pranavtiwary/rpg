package com.rpg.utility;

import static java.util.Objects.nonNull;

import java.util.function.Predicate;

/**
 * Reader Utility
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class ReaderUtility {

    private static final Predicate<String> NOT_EMPTY =
            s -> nonNull(s) && !s.isEmpty() && s.chars().noneMatch(Character::isWhitespace);

    private static final Predicate<String> IS_NUMERIC =
            s -> nonNull(s) && s.chars().allMatch(Character::isDigit);

    private static final Predicate<String> NON_NEGATIVE = line -> Integer.parseInt(line) > 0;

    private static final Predicate<String> INT_VALID_INPUT_DATA = NOT_EMPTY.and(IS_NUMERIC).and(NON_NEGATIVE);

    public static int readIntegerUntil(int min, int max, String message) {
    	Predicate<String> USER_CONDITION = s -> Integer.parseInt(s) >= min && Integer.parseInt(s) <= max ;
        Predicate<String> retryCondition = INT_VALID_INPUT_DATA.and(USER_CONDITION).negate();
        String line = null;
        do {
        	IOUtility.writeOnConsole(message);
            line = IOUtility.readLine();
        } while (retryCondition.test(line));
        return Integer.parseInt(line);
    }
}
