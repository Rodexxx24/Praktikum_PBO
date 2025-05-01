package rajakonsol.model;

/**
 * Subclass dari User yang merepresentasikan Penyewa
 */
public class Penyewa extends User {

    // Konstruktor Penyewa mengikuti konstruktor User
    public Penyewa(String id, String nama, String noTelepon, String alamat) {
        super(id, nama, noTelepon, alamat);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("=== Informasi Penyewa ===");
        System.out.println("ID       : " + getId());
        System.out.println("Nama     : " + getNama());
        System.out.println("Telepon  : " + getNoTelepon());
        System.out.println("Alamat   : " + getAlamat());
    }
}
