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
     * Proses login admin dengan maksimal 3 kali percobaan
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
                case 1:
                    konsolService.menuKelola();
                    break;
                case 2:
                    penyewaService.menuKelola();
                    break;
                case 3:
                    transaksiService.menuKelola();
                    break;
                case 0:
                    System.out.println("Logout berhasil.");
                    adminLoop = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
