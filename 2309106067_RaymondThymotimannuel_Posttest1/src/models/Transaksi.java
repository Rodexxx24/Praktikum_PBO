package models;
import java.time.LocalDate;

public class Transaksi {
    private int id;
    private Pengguna pengguna;
    private Konsol konsol;
    private LocalDate tanggalSewa;
    private LocalDate tanggalKembali;

    public Transaksi(int id, Pengguna pengguna, Konsol konsol, LocalDate tanggalSewa, LocalDate tanggalKembali) {
        this.id = id;
        this.pengguna = pengguna;
        this.konsol = konsol;
        this.tanggalSewa = tanggalSewa;
        this.tanggalKembali = tanggalKembali;
    }

    public int getId() { return id; }
    public Pengguna getPengguna() { return pengguna; }
    public Konsol getKonsol() { return konsol; }
    public LocalDate getTanggalSewa() { return tanggalSewa; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }

    @Override
    public String toString() {
        return "Transaksi [ID: " + id + ", Pengguna: " + pengguna.getNama() + 
               ", Konsol: " + konsol.getNama() + ", Tanggal Sewa: " + tanggalSewa + 
               ", Tanggal Kembali: " + tanggalKembali + "]";
    }
}
