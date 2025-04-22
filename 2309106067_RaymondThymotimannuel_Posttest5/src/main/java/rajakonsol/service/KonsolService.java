package rajakonsol.service;

import java.util.ArrayList;
import java.util.Comparator;

import rajakonsol.model.Konsol;
import rajakonsol.util.InputHelper;

/**
 * Service untuk mengelola data Konsol (CRUD)
 */
public class KonsolService {
    private final ArrayList<Konsol> daftarKonsol = new ArrayList<>();

    /**
     * Getter untuk list konsol, digunakan oleh TransaksiService
     */
    public ArrayList<Konsol> getDaftarKonsol() {
        return daftarKonsol;
    }

    public KonsolService() {
        // Data awal (opsional)
        daftarKonsol.add(new Konsol("PS01", "PlayStation 5", 5, 10000, 50000));
        daftarKonsol.add(new Konsol("Xb01", "Xbox Series X", 3, 9000, 45000));
    }

    /**
     * Menu interaktif untuk mengelola konsol
     */
    public void menuKelola() {
        boolean loop = true;
        while (loop) {
            System.out.println("\n--- Kelola Konsol ---");
            System.out.println("1. Tambah Konsol");
            System.out.println("2. Lihat Daftar Konsol");
            System.out.println("3. Update Konsol");
            System.out.println("4. Hapus Konsol");
            System.out.println("0. Kembali");

            int pilihan = InputHelper.inputInt("Pilih menu");
            switch (pilihan) {
                case 1 -> tambahKonsol();
                case 2 -> tampilkanKonsol();
                case 3 -> updateKonsol();
                case 4 -> hapusKonsol();
                case 0 -> loop = false;
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private String generateId(String prefix) {
        long count = daftarKonsol.stream()
            .filter(k -> k.getIdKonsol().startsWith(prefix))
            .count();
        return prefix + String.format("%02d", count + 1);
    }
    
    public void tambahKonsol() {
        System.out.println("\n--- Tambah Konsol Baru ---");
        String kategori = InputHelper.inputPilihan("Kategori [PS/Xb]", "PS", "Xb").toUpperCase();
        String id = generateId(kategori);
        String nama = InputHelper.inputNonEmptyString("Nama Konsol");
        int jumlah = InputHelper.inputInt("Jumlah Unit");
        double hargaPerJam = InputHelper.inputDouble("Harga Sewa per Jam");
        double hargaPerHari = InputHelper.inputDouble("Harga Sewa per Hari");
    
        Konsol konsol = new Konsol(id, nama, jumlah, hargaPerJam, hargaPerHari);
        daftarKonsol.add(konsol);
        System.out.println("Konsol berhasil ditambahkan dengan ID: " + id);
    }    

    public void tampilkanKonsol() {
        System.out.println("\nDaftar Konsol:");
        if (daftarKonsol.isEmpty()) {
            System.out.println("Belum ada data konsol.");
            return;
        }
        System.out.printf("%-5s %-20s %-7s %-12s %-12s %-10s\n", "ID", "Nama", "Unit", "Harga/Jam", "Harga/Hari", "Tersedia");
        for (Konsol k : daftarKonsol) {
            System.out.printf("%-5s %-20s %-7d %-12.0f %-12.0f %-10s\n",
                    k.getIdKonsol(), k.getNama(), k.getJumlah(), k.getHargaPerJam(), k.getHargaPerHari(), k.isTersedia());
        }
    }

    public void updateKonsol() {
        System.out.println("\nUpdate Konsol");
        String id = InputHelper.inputString("Masukkan ID Konsol yang diubah");
        for (Konsol k : daftarKonsol) {
            if (k.getIdKonsol().equals(id)) {
                int jumlah = InputHelper.inputInt("Jumlah Unit Baru");
                double hargaJam = InputHelper.inputDouble("Harga Sewa per Jam Baru");
                double hargaHari = InputHelper.inputDouble("Harga Sewa per Hari Baru");

                k.setJumlah(jumlah);
                k.setHargaPerJam(hargaJam);
                k.setHargaPerHari(hargaHari);
                System.out.println("Data konsol berhasil diupdate.");
                return;
            }
        }
        System.out.println("Konsol dengan ID tersebut tidak ditemukan.");
    }

    public void hapusKonsol() {
        System.out.println("\nHapus Konsol");
        String id = InputHelper.inputString("Masukkan ID Konsol yang akan dihapus");
        for (int i = 0; i < daftarKonsol.size(); i++) {
            if (daftarKonsol.get(i).getIdKonsol().equals(id)) {
                daftarKonsol.remove(i);
                System.out.println("Konsol berhasil dihapus.");
                return;
            }
        }
        System.out.println("Konsol dengan ID tersebut tidak ditemukan.");
    }

    public void tampilkanKategori(String kategori) {
    System.out.println("\nDaftar Konsol Kategori " + (kategori.equalsIgnoreCase("PS") ? "PlayStation" : "Xbox"));
    System.out.printf("%-5s %-20s %-8s %-12s %-12s %-10s\n",
        "ID", "Nama", "Unit", "Harga/Jam", "Harga/Hari", "Tersedia");
    daftarKonsol.stream()
        .filter(k -> k.getIdKonsol().startsWith(kategori))
        .forEach(this::printKonsol);
    }

    public void tampilkanHargaTerendah() {
        System.out.println("\n--- Konsol dengan Harga Sewa per Jam Terendah ---");
        daftarKonsol.stream()
            .sorted(Comparator.comparingDouble(Konsol::getHargaPerJam))
            .forEach(this::printKonsol);
    }

    public void tampilkanHargaTertinggi() {
        System.out.println("\n--- Konsol dengan Harga Sewa per Jam Tertinggi ---");
        daftarKonsol.stream()
            .sorted(Comparator.comparingDouble(Konsol::getHargaPerJam).reversed())
            .forEach(this::printKonsol);
    }

    private void printKonsol(Konsol k) {
        System.out.printf("%-5s %-20s %-8d %-12.0f %-12.0f %-10s\n",
            k.getIdKonsol(), k.getNama(), k.getJumlah(), k.getHargaPerJam(), k.getHargaPerHari(),
            k.getJumlah() > 0 ? "true" : "false");
    }
    
}
