import java.util.Scanner;

public class HexConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String hex;
        while (true) {
            System.out.print("Введите шестнадцатеричное число (например, 1A3F): ");
            hex = scanner.nextLine().trim();

            if (isValidHex(hex)) {
                break;
            } else {
                System.out.println("Ошибка: введено недопустимое шестнадцатеричное число. Попробуйте снова.");
            }
        }

        int decimal = Integer.parseInt(hex, 16);

        String binary = Integer.toBinaryString(decimal);

        System.out.println("Десятичное представление: " + decimal);
        System.out.println("Двоичное представление: " + binary);
    }

    private static boolean isValidHex(String hex) {
        return hex.matches("^[0-9A-Fa-f]+$");
    }
}
