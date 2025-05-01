package rajakonsol.model;

/**
 * Kelas abstrak User sebagai superclass untuk Admin dan Penyewa
 */
public abstract class User {
    private final String id;
    private String nama;
    private String noTelepon;
    private String alamat;

    public User(String id, String nama, String noTelepon, String alamat) {
        this.id = id;
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    // Abstract method untuk diterapkan secara berbeda oleh admin dan penyewa
    public abstract void tampilkanInfo();
}
