package rajakonsol.service;

import rajakonsol.util.InputHelper;

/**
 * Service untuk autentikasi dan menu admin
 */
public class AdminService {
    private static final String USERNAME = "raymond067";
    private static final String PASSWORD = "2309106067";

    private final KonsolService konsolService;
    private final PenyewaService penyewaService;
    private final TransaksiService transaksiService;

    /**
     * Konstruktor meng-inject semua service yang dibutuhkan oleh admin
     */
    public AdminService(KonsolService konsolService, PenyewaService penyewaService, TransaksiService transaksiService) {
        this.konsolService = konsolService;
        this.penyewaService = penyewaService;
        this.transaksiService = transaksiService;
    }

    /**
     * Proses login admin dengan maksimal 3 kali percobaan via CLI
     * @return true jika login sukses
     */
    public boolean login() {
        int attempts = 0;
        while (attempts < 3) {
            String inputUser = InputHelper.inputString("Username");
            String inputPass = InputHelper.inputString("Password");
            if (USERNAME.equals(inputUser) && PASSWORD.equals(inputPass)) {
                System.out.println("Login berhasil. Selamat datang, Admin.");
                return true;
            } else {
                attempts++;
                System.out.println("Username atau password salah. Sisa kesempatan: " + (3 - attempts));
            }
        }
        System.out.println("Kesempatan login telah habis. Program dihentikan.");
        System.exit(0);
        return false;
    }

    /**
     * Metode tambahan untuk login via GUI
     * @param username username input
     * @param password password input
     * @return true jika sesuai
     */
    public boolean login(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }

    /**
     * Menu utama untuk admin, memanggil service sesuai pilihan
     */
    public void menuAdmin() {
        boolean adminLoop = true;
        while (adminLoop) {
            System.out.println("\n--- Menu Admin ---");
            System.out.println("[1] Kelola Konsol");
            System.out.println("[2] Kelola Penyewa");
            System.out.println("[3] Kelola Transaksi");
            System.out.println("[0] Logout");
            int pilihan = InputHelper.inputInt("Pilih menu");

            switch (pilihan) {
                case 1 -> konsolService.menuKelola();
                case 2 -> penyewaService.menuKelola();
                case 3 -> transaksiService.menuKelola();
                case 0 -> {
                    System.out.println("Logout berhasil.");
                    adminLoop = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
