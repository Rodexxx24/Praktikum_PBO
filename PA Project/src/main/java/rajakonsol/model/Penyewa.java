package rajakonsol.model;

public class Penyewa {

    private String id;
    private String namaLengkap;
    private String noTelp;
    private String alamat;

    public Penyewa(String id, String namaLengkap, String noTelp, String alamat) {
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}