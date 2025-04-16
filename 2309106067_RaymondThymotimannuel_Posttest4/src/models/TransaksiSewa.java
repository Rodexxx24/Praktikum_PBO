package models;

import java.time.LocalDate;

public class TransaksiSewa extends Transaksi {
    private double biayaSewa;

    public TransaksiSewa(int id, Pengguna pengguna, Konsol konsol, LocalDate tanggalSewa, LocalDate tanggalKembali, double biayaSewa) {
        super(id, pengguna, konsol, tanggalSewa, tanggalKembali);
        this.biayaSewa = biayaSewa;
    }

    public double getBiayaSewa() { return biayaSewa; }

    // Override method dari superclass
    @Override
    public double hitungBiaya() {
        return biayaSewa;
    }

    // Override toString untuk menambahkan informasi biaya sewa
    @Override
    public String toString() {
        return super.toString() + ", Biaya Sewa: " + biayaSewa;
    }
}
