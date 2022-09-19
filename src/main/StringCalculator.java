package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {
        int result = 0;
        if (numbers.isEmpty()) {
            return 0;
        }

        List<String> delimiters = findDelimiter(numbers, findDigit(numbers));
        result = checkDelimiters(numbers, result, delimiters);
        if (result != -1) {
            List<Integer> numbers_array = findNumbers(numbers, delimiters);
            result = sum(numbers_array, result);
        }
        return result;
    }

    private int sum(List<Integer> numbers_array, int result) {
        result = checkNegative(result, numbers_array);
        if (result != -1) {
            for (Integer integer : numbers_array) {
                if (integer <= 1000) {
                    result += integer;
                }
            }
        }
        return result;
    }

    private int checkDelimiters(String numbers, int result, List<String> delimiters) {
        delimiters.sort(Comparator.reverseOrder());
        int first_digit = findDigit(numbers);
        String numbers_str = numbers.substring(first_digit);
        String temp;
        for (String delimiter : delimiters) {
            for (String s : delimiters) {
                temp = delimiter.concat(s);
                if (numbers_str.contains(temp) && !delimiters.contains(temp)) {
                    System.out.println("\nНеправильне використання роздільників");
                    return -1;
                }
            }
        }
        return result;
    }

    private List<Integer> findNumbers(String numbers, List<String> delimiters) {
        String numbers_str = numbers.substring(findDigit(numbers));
        String[] temp_nums = numbers_str.split(Pattern.quote(delimiters.get(0)));
        for (int i = 1; i < delimiters.size(); i++) {
            temp_nums = splitNumbers(temp_nums, delimiters, i).toArray(new String[0]);
        }

        List<Integer> numbers_list = new ArrayList<>();
        for (String temp_num : temp_nums) {
            if (temp_num == null) {
                return null;
            }
            numbers_list.add(Integer.parseInt(temp_num));
        }
        return numbers_list;
    }

    private List<String> splitNumbers(String[] numbers_str, List<String> delimiters, int i) {
        List<String> split_nums = new ArrayList<>();
        for (String s : numbers_str) {
            String[] temp_array = s.split(Pattern.quote(delimiters.get(i)));
            addToArray(split_nums, temp_array);
        }
        return split_nums;
    }

    private void addToArray(List<String> split_nums, String[] temp_array) {
        for (String s : temp_array) {
            split_nums.add(0, s);
        }
    }

    private List<String> findDelimiter(String numbers, int first_digit) {
        List<String> delimiters = new ArrayList<>();
        delimiters.add(",");
        delimiters.add("\\n");
        if (first_digit != 0) {
            String delimiters_str = numbers.substring(2, first_digit - 2);
            if (!delimiters_str.startsWith("[")) {
                delimiters.add(delimiters_str);
            } else {
                String[] delimiters_temp = delimiters_str.split("]");
                for (String del : delimiters_temp) {
                    delimiters.add(del.substring(1));
                }
            }
        }
        return delimiters;
    }

    private int findDigit(String nums) {
        char[] chars = nums.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                return i;
            }
        }
        return 0;
    }

    private int checkNegative(int result, List<Integer> numbers_array) {
        int flag = 0, temp_neg = 0;
        List<Integer> negative_list = new ArrayList<>();
        for (int temp : numbers_array) {
            if (temp < 0 && flag == 0) {
                System.out.println("Hедозволені від’ємні числа");
                negative_list.add(temp_neg, temp);
                temp_neg += 1;
                flag += 1;
            } else if (temp < 0) {
                negative_list.add(temp_neg, temp);
                temp_neg += 1;
            }
        }
        if (flag == 1) {
            return printList(negative_list);
        }
        return result;
    }

    public int printList(List<Integer> list) {
        for (int el : list) {
            if (el != 0) {
                System.out.printf("%d ", el);
            }
        }
        System.out.println("\n");
        return -1;
    }
}
