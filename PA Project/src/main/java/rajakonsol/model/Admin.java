package rajakonsol.model;

/**
 * Subclass dari User yang merepresentasikan Admin
 */
public class Admin extends User {

    public Admin(String id, String role, String username, String password, String namaLengkap, String noTelp) {
        super(id, role, username, password, namaLengkap, noTelp);
    }

    @Override
    public void display() {
    }
}
