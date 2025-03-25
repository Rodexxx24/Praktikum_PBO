package models;

public class Konsol {
    private int id;
    private String nama;
    private double hargaSewa;

    public Konsol(int id, String nama, double hargaSewa) {
        this.id = id;
        this.nama = nama;
        this.hargaSewa = hargaSewa;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public double getHargaSewa() { return hargaSewa; }

    public void setNama(String nama) { this.nama = nama; }
    public void setHargaSewa(double hargaSewa) { this.hargaSewa = hargaSewa; }

    @Override
    public String toString() {
        return "Konsol [ID: " + id + ", Nama: " + nama + ", Harga: " + hargaSewa + "]";
    }
}
