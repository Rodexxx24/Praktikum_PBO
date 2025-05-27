package rajakonsol.model;

public class Konsol {
    private String id;
    private String kategori;
    private int harga_sewa;
    private boolean status;

    public Konsol(String id, String kategori, int harga_sewa, boolean status) {
        this.id = id;
        this.kategori = kategori;
        this.harga_sewa = harga_sewa;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getHarga_sewa() {
        return harga_sewa;
    }

    public void setHarga_sewa(int harga_sewa) {
        this.harga_sewa = harga_sewa;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
