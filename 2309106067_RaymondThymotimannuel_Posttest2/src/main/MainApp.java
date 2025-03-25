package main;
import models.Konsol;
import services.KonsolService;
import utils.InputHelper;

public class MainApp {
    public static void main(String[] args) {
        KonsolService konsolService = new KonsolService();

        while (true) {
            System.out.println("Selamat datang di RajaKonsol");
            System.out.println("=== Silahkan Pilih Menu ===");
            System.out.println("1. Tambah Konsol");
            System.out.println("2. Lihat Konsol");
            System.out.println("3. Hapus Konsol");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = InputHelper.nextInt();
            InputHelper.nextLine();

            switch (pilihan) {
                case 1 -> {
                    System.out.print("Nama Konsol: ");
                    String nama = InputHelper.nextLine();
                    System.out.print("Harga Sewa: ");
                    double harga = InputHelper.nextDouble();
                    konsolService.tambahKonsol(new Konsol(konsolService.getSize() + 1, nama, harga));
                }
                case 2 -> konsolService.tampilkanKonsol();
                case 3 -> {
                    System.out.print("ID Konsol yang dihapus: ");
                    int id = InputHelper.nextInt();
                    konsolService.hapusKonsol(id);
                }
                case 4 -> {
                    System.out.println("Terima kasih!");
                    return;
                }
            }
        }
    }
}
