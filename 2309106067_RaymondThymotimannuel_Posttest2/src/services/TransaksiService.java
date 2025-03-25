package services;
import models.Transaksi;
import java.util.ArrayList;

public class TransaksiService {
    private ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

    public void tambahTransaksi(Transaksi transaksi) {
        daftarTransaksi.add(transaksi);
        System.out.println("Transaksi berhasil ditambahkan.");
    }

    public void tampilkanTransaksi() {
        if (daftarTransaksi.isEmpty()) {
            System.out.println("Tidak ada transaksi.");
        } else {
            for (Transaksi t : daftarTransaksi) {
                System.out.println(t);
            }
        }
    }
}
