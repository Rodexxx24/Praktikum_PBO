package services;
import models.Pengguna;
import java.util.ArrayList;

public class PenggunaService {
    private ArrayList<Pengguna> daftarPengguna = new ArrayList<>();

    public void tambahPengguna(Pengguna pengguna) {
        daftarPengguna.add(pengguna);
        System.out.println("Pengguna berhasil ditambahkan.");
    }

    public void tampilkanPengguna() {
        if (daftarPengguna.isEmpty()) {
            System.out.println("Tidak ada pengguna terdaftar.");
        } else {
            for (Pengguna p : daftarPengguna) {
                System.out.println(p);
            }
        }
    }
}
