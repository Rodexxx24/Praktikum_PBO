package rajakonsol.service;

import java.util.ArrayList;
import java.util.List;

import rajakonsol.model.Penyewa;
import rajakonsol.util.InputHelper;

/**
 * Service untuk mengelola data Penyewa (CRUD)
 */
public class PenyewaService {
    private final List<Penyewa> daftarPenyewa = new ArrayList<>();

    /**
     * Getter untuk list penyewa, digunakan oleh TransaksiService
     */
    public List<Penyewa> getDaftarPenyewa() {
        return daftarPenyewa;
    }

    public PenyewaService() {
        // Data awal (opsional)
        daftarPenyewa.add(new Penyewa("P001", "Ali Akbar", "08123456789", "Jl. Merdeka No.10"));
        daftarPenyewa.add(new Penyewa("P002", "Siti Aminah", "08987654321", "Jl. Pahlawan No.20"));
    }

    /**
     * Menu interaktif untuk mengelola penyewa
     */
    public void menuKelola() {
        boolean loop = true;
        while (loop) {
            System.out.println("\n--- Kelola Penyewa ---");
            System.out.println("1. Tambah Penyewa");
            System.out.println("2. Lihat Daftar Penyewa");
            System.out.println("3. Update Penyewa");
            System.out.println("4. Hapus Penyewa");
            System.out.println("0. Kembali");

            int pilihan = InputHelper.inputInt("Pilih menu");
            switch (pilihan) {
                case 1:
                    tambahPenyewa();
                    break;
                case 2:
                    tampilkanPenyewa();
                    break;
                case 3:
                    updatePenyewa();
                    break;
                case 4:
                    hapusPenyewa();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void tambahPenyewa() {
        System.out.println("\nTambah Penyewa Baru");
        String id = InputHelper.inputString("ID Penyewa");
        String nama = InputHelper.inputString("Nama Penyewa");
        String telepon = InputHelper.inputString("Nomor Telepon");
        String alamat = InputHelper.inputString("Alamat");

        Penyewa p = new Penyewa(id, nama, telepon, alamat);
        daftarPenyewa.add(p);
        System.out.println("Penyewa berhasil ditambahkan.");
    }

    private void tampilkanPenyewa() {
        System.out.println("\nDaftar Penyewa:");
        if (daftarPenyewa.isEmpty()) {
            System.out.println("Belum ada data penyewa.");
            return;
        }
        System.out.printf("%-5s %-20s %-15s %-30s\n", "ID", "Nama", "Telepon", "Alamat");
        for (Penyewa p : daftarPenyewa) {
            System.out.printf("%-5s %-20s %-15s %-30s\n",
                    p.getId(), p.getNama(), p.getNoTelepon(), p.getAlamat());
        }
    }

    private void updatePenyewa() {
        System.out.println("\nUpdate Data Penyewa");
        String id = InputHelper.inputString("Masukkan ID Penyewa yang diubah");
        for (Penyewa p : daftarPenyewa) {
            if (p.getId().equals(id)) {
                String namaBaru = InputHelper.inputString("Nama Baru");
                String teleponBaru = InputHelper.inputString("Nomor Telepon Baru");
                String alamatBaru = InputHelper.inputString("Alamat Baru");

                p.setNama(namaBaru);
                p.setNoTelepon(teleponBaru);
                p.setAlamat(alamatBaru);
                System.out.println("Data penyewa berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Penyewa dengan ID tersebut tidak ditemukan.");
    }

    private void hapusPenyewa() {
        System.out.println("\nHapus Penyewa");
        String id = InputHelper.inputString("Masukkan ID Penyewa yang akan dihapus");
        for (int i = 0; i < daftarPenyewa.size(); i++) {
            if (daftarPenyewa.get(i).getId().equals(id)) {
                daftarPenyewa.remove(i);
                System.out.println("Penyewa berhasil dihapus.");
                return;
            }
        }
        System.out.println("Penyewa dengan ID tersebut tidak ditemukan.");
    }
}
