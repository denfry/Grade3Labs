import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayTransformation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N;

        while (true) {
            System.out.print("Введите размер массива N (положительное число): ");
            try {
                N = scanner.nextInt();
                if (N > 0) break;
                System.out.println("Ошибка: введите положительное целое число.");
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число.");
                scanner.next();
            }
        }

        int[] A = new int[N];

        System.out.println("Введите элементы массива (целые числа):");
        for (int i = 0; i < N; i++) {
            while (true) {
                try {
                    A[i] = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: введите целое число.");
                    scanner.next();
                }
            }
        }

        int[] transformedArray = transformArray(A);

        System.out.println("Преобразованный массив:");
        for (int value : transformedArray) {
            System.out.print(value + " ");
        }
    }

    public static int[] transformArray(int[] A) {
        int N = A.length;
        int[] transformed = new int[N];

        for (int i = 0; i < N; i++) {
            if (A[i] % 2 != 0) {
                transformed[i] = A[i] * 2;
            } else {
                boolean foundOdd = false;
                for (int j = i + 1; j < N; j++) {
                    if (A[j] % 2 != 0) {
                        transformed[i] = A[i] + A[j];
                        foundOdd = true;
                        break;
                    }
                }
                if (!foundOdd) {
                    transformed[i] = A[i];
                }
            }
        }

        return transformed;
    }
}
