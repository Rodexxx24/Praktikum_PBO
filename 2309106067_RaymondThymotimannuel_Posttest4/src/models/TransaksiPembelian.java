package models;

import java.time.LocalDate;

public class TransaksiPembelian extends Transaksi {
    private double hargaBeli;

    public TransaksiPembelian(int id, Pengguna pengguna, Konsol konsol, LocalDate tanggalSewa, LocalDate tanggalKembali, double hargaBeli) {
        super(id, pengguna, konsol, tanggalSewa, tanggalKembali);
        this.hargaBeli = hargaBeli;
    }

    public double getHargaBeli() { return hargaBeli; }

    // Override method dari superclass
    @Override
    public double hitungBiaya() {
        return hargaBeli;
    }

    // Override toString untuk menambahkan informasi harga beli
    @Override
    public String toString() {
        return super.toString() + ", Harga Beli: " + hargaBeli;
    }
}
