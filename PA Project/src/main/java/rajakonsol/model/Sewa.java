package rajakonsol.model;

import java.util.Date;

public class Sewa {
    private String id_sewa;
    private Kasir kasir;
    private Konsol konsol;
    private Penyewa penyewa;
    private String jenis_sewa;
    private int durasi;
    private Date tanggal_sewa;

    public Sewa(String id_sewa, Kasir kasir, Konsol konsol, Penyewa penyewa, String jenis_sewa, int durasi,
            Date tanggal_sewa) {
        this.id_sewa = id_sewa;
        this.kasir = kasir;
        this.konsol = konsol;
        this.penyewa = penyewa;
        this.jenis_sewa = jenis_sewa;
        this.durasi = durasi;
        this.tanggal_sewa = tanggal_sewa;
    }

    public String getId_sewa() {
        return id_sewa;
    }

    public void setId_sewa(String id_sewa) {
        this.id_sewa = id_sewa;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }

    public Konsol getKonsol() {
        return konsol;
    }

    public void setKonsol(Konsol konsol) {
        this.konsol = konsol;
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public void setPenyewa(Penyewa penyewa) {
        this.penyewa = penyewa;
    }

    public String getJenis_sewa() {
        return jenis_sewa;
    }

    public void setJenis_sewa(String jenis_sewa) {
        this.jenis_sewa = jenis_sewa;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public Date getTanggal_sewa() {
        return tanggal_sewa;
    }

    public void setTanggal_sewa(Date tanggal_sewa) {
        this.tanggal_sewa = tanggal_sewa;
    }

}
