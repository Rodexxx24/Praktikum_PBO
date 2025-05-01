package rajakonsol.app;

import rajakonsol.service.AdminService;
import rajakonsol.service.KonsolService;
import rajakonsol.service.PenyewaService;
import rajakonsol.service.TransaksiInterface;
import rajakonsol.service.TransaksiService;
import rajakonsol.util.InputHelper;

public class Main {
    public static void main(String[] args) {
        KonsolService konsolService = new KonsolService();
    PenyewaService penyewaService = new PenyewaService();
    TransaksiService transaksiService = new TransaksiService(konsolService, penyewaService);
    AdminService adminService = new AdminService(konsolService, penyewaService, transaksiService);


        boolean running = true;
        while (running) {
            System.out.println("\n===== SELAMAT DATANG DI RAJA KONSOL SAMARINDA =====");
            System.out.println("Silahkan pilih menu menggunakan angka:");
            System.out.println("[1] Login Admin");
            System.out.println("[2] Penyewaan Konsol");
            System.out.println("[0] Keluar");
            int pilihan = InputHelper.inputInt("Pilih menu [1/2/0]");

            switch (pilihan) {
                case 1 -> {
                    if (adminService.login()) {
                        adminService.menuAdmin();
                    }
                }
                case 2 -> menuPenyewaan(konsolService, transaksiService);
                case 0 -> {
                    System.out.println("Terima kasih telah menggunakan Raja Konsol!");
                    running = false;
                }
                default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void menuPenyewaan(KonsolService konsolService, TransaksiInterface transaksiService) {
        boolean loop = true;
        while (loop) {
            System.out.println("\n--- Daftar Konsol ---");
            konsolService.tampilkanKonsol();

            System.out.println("\n===== MENU PENYEWAAN =====");
            System.out.println("[1] Sewa Konsol");
            System.out.println("[2] Kategori Konsol");
            System.out.println("[3] Harga Sewa Terendah");
            System.out.println("[4] Harga Sewa Tertinggi");
            System.out.println("[0] Keluar");
            int pilihan = InputHelper.inputInt("Pilih menu [1/2/3/4/0]");

            switch (pilihan) {
                case 1 -> {
                    transaksiService.registrasiDanTambahTransaksi();
                }
                case 2 -> {
                    String kategori = InputHelper.inputPilihan("Pilih kategori [PS/Xb]", "PS", "Xb").toUpperCase();
                    konsolService.tampilkanKategori(kategori);
                }
                case 3 -> konsolService.tampilkanHargaTerendah();
                case 4 -> konsolService.tampilkanHargaTertinggi();
                case 0 -> loop = false;
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
