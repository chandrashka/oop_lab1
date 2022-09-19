package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        StringCalculator calculator = new StringCalculator();
        int test = 0;
        int result;
        while (true) {
            test += 1;
            System.out.printf("\nТестування номер %d.\nВведіть числа: ", test);
            String nums = input.nextLine();
            result = calculator.add(nums);
            if (result != -1) {
                System.out.printf("Результат додавання: %d\n", result);
            }
            System.out.print("Натисніть 1, щоб продовжити програму, або 0, щоб закінчити: ");
            int end = Integer.parseInt(input.nextLine());
            if (end == 0) {
                break;
            }
        }
    }
}

