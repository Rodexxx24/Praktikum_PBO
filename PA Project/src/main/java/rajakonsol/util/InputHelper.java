package rajakonsol.util;

import java.util.Scanner;

/**
 * Kelas pembantu untuk input yang aman dari user.
 */
public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static String formatError(String msg) {
        return ERROR_PREFIX + msg;
    }

    public static String inputString(String label) {
        System.out.print(label + " : ");
        return scanner.nextLine().trim();
    }

    public static String inputNonEmptyString(String label) {
        while (true) {
            String input = inputString(label);
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println(formatError("Input tidak boleh kosong. Silakan coba lagi."));
        }
    }

    public static int inputInt(String label) {
        while (true) {
            try {
                String input = inputString(label);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(formatError("Input harus berupa angka bulat. Silakan coba lagi."));
            }
        }
    }

    public static double inputDouble(String label) {
        while (true) {
            try {
                String input = inputString(label);
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(formatError("Input harus berupa angka desimal. Silakan coba lagi."));
            }
        }
    }

    public static String inputPilihan(String label, String... pilihanValid) {
        while (true) {
            String input = inputString(label);
            for (String p : pilihanValid) {
                if (input.equalsIgnoreCase(p)) {
                    return p; // kembalikan sesuai pilihan valid, bukan input user
                }
            }
            System.out.println(formatError("Pilihan tidak valid. Pilihan yang tersedia:"));
            for (String p : pilihanValid) {
                System.out.println("- " + p);
            }
        }
    }

    public static boolean inputYaTidak(String prompt) {
        while (true) {
            System.out.print(prompt + " [y/n]: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("ya")) return true;
            if (input.equals("n") || input.equals("tidak")) return false;
            System.out.println(formatError("Input tidak valid. Masukkan 'y' atau 'n'."));
        }
    }

    public static void tungguEnter() {
        System.out.println("\nTekan ENTER untuk melanjutkan...");
        scanner.nextLine();
    }
}
