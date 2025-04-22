package rajakonsol.model;

/**
 * Subclass dari User yang merepresentasikan Admin
 */
public class Admin extends User {

    // Konstruktor Admin mengikuti konstruktor User
    public Admin(String id, String nama, String noTelepon, String alamat) {
        super(id, nama, noTelepon, alamat);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("=== Informasi Admin ===");
        System.out.println("ID       : " + getId());
        System.out.println("Nama     : " + getNama());
        System.out.println("Telepon  : " + getNoTelepon());
        System.out.println("Alamat   : " + getAlamat());
    }
}
