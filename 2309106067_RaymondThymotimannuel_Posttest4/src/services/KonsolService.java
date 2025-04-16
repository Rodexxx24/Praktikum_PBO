package services;

import java.util.ArrayList;
import models.Konsol;

public class KonsolService {
    private ArrayList<Konsol> daftarKonsol = new ArrayList<>();

    // Method untuk menambahkan konsol ke daftar
    public void tambahKonsol(Konsol konsol) {
        daftarKonsol.add(konsol);
        System.out.println("Konsol berhasil ditambahkan.");
    }

    // Method untuk menampilkan semua konsol yang tersedia
    public void tampilkanKonsol() {
        if (daftarKonsol.isEmpty()) {
            System.out.println("Tidak ada konsol tersedia.");
        } else {
            for (Konsol k : daftarKonsol) {
                System.out.println(k);
            }
        }
    }

    // Method untuk menghapus konsol berdasarkan ID
    public void hapusKonsol(int id) {
        daftarKonsol.removeIf(k -> k.getId() == id);
        System.out.println("Konsol berhasil dihapus.");
    }

    // Method untuk mencari konsol berdasarkan ID
    public Konsol cariKonsolById(int id) {
        for (Konsol konsol : daftarKonsol) {
            if (konsol.getId() == id) {
                return konsol; // Konsol ditemukan, return konsol
            }
        }
        return null; // Jika tidak ditemukan, return null
    }

    // Method untuk mendapatkan ukuran daftar konsol
    public int getSize() {
        return daftarKonsol.size();
    }
}
