package services;
import java.util.ArrayList;
import models.Konsol;

public class KonsolService {
    private ArrayList<Konsol> daftarKonsol = new ArrayList<>();

    public void tambahKonsol(Konsol konsol) {
        daftarKonsol.add(konsol);
        System.out.println("Konsol berhasil ditambahkan.");
    }

    public void tampilkanKonsol() {
        if (daftarKonsol.isEmpty()) {
            System.out.println("Tidak ada konsol tersedia.");
        } else {
            for (Konsol k : daftarKonsol) {
                System.out.println(k);
            }
        }
    }

    public void hapusKonsol(int id) {
        daftarKonsol.removeIf(k -> k.getId() == id);
        System.out.println("Konsol berhasil dihapus.");
    }
    public int getSize() {
        return daftarKonsol.size();
    }
}
