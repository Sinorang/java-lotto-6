package lotto.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lotto.enums.ConstVariable;

public class ValidationUtil {

    public static void isValidWinNumbers(List<Integer> numbers) {
        isValidNumber(numbers);
        isValidCount(numbers);
        hasDistinctNumbers(numbers);
    }

    public static List<Integer> isValidWinNumbers(String input) {
        String[] splits = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String split : splits) {
            makeNumbers(numbers, split.trim());
        }
        isValidNumber(numbers);
        isValidCount(numbers);
        hasDistinctNumbers(numbers);

        return numbers;
    }

    public static void isValidNumber(List<Integer> numbers) {
        Optional<Integer> result = numbers.stream()
                .filter(number -> number < ConstVariable.MIN_NUMBER.getValue()
                        || number > ConstVariable.MAX_NUMBER.getValue())
                .findFirst();

        if (result.isPresent()) {
            throw new IllegalArgumentException();
        }
    }

    public static void isValidCount(List<Integer> numbers) {
        if (numbers.size() != ConstVariable.LOTTO_COUNT.getValue()) {
            throw new IllegalArgumentException();
        }
    }

    public static void hasDistinctNumbers(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    public static void isValidBonusNumber(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }

    public static void isValidAmount(String input) {
        String str = input.trim();
        if (str.isEmpty() || !str.matches("[0-9]*") || Integer.parseInt(str) % ConstVariable.STANDARD.getValue() != 0) {
            throw new IllegalArgumentException();
        }
    }

    private static void makeNumbers(List<Integer> numbers, String str) {
        if (str.isEmpty() || str.matches("[0-9]*")) {
            throw new IllegalArgumentException();
        }
        numbers.add(Integer.parseInt(str));
    }
}
