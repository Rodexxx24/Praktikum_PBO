package rajakonsol.model;

public class Konsol {
    private String idKonsol;
    private String nama;
    private int jumlah;
    private double hargaPerJam;
    private double hargaPerHari;

    public Konsol(String idKonsol, String nama, int jumlah, double hargaPerJam, double hargaPerHari) {
        this.idKonsol = idKonsol;
        this.nama = nama;
        this.jumlah = jumlah;
        this.hargaPerJam = hargaPerJam;
        this.hargaPerHari = hargaPerHari;
    }

    // Metode untuk mendapatkan harga sewa berdasarkan tipe
    public double getHargaSewa(String tipeSewa) {
        if ("jam".equalsIgnoreCase(tipeSewa)) {
            return hargaPerJam;
        } else if ("hari".equalsIgnoreCase(tipeSewa)) {
            return hargaPerHari;
        }
        return 0; // Jika tipe sewa tidak dikenali
    }

    // Getter dan Setter lainnya
    public String getIdKonsol() {
        return idKonsol;
    }

    public String getNama() {
        return nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getHargaPerJam() {
        return hargaPerJam;
    }

    public double getHargaPerHari() {
        return hargaPerHari;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setHargaPerJam(double hargaPerJam) {
        this.hargaPerJam = hargaPerJam;
    }

    public void setHargaPerHari(double hargaPerHari) {
        this.hargaPerHari = hargaPerHari;
    }

    public boolean isTersedia() {
        return jumlah > 0;
    }
}
