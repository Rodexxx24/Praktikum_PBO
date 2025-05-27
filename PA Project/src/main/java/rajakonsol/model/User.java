package rajakonsol.model;

public abstract class User {
    private String id;
    private String role;
    private String username;
    private String password;
    private String namaLengkap;
    private String noTelp;

    public User(String id, String role, String username, String password, String namaLengkap, String noTelp) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.noTelp = noTelp;
    }

    // getter and setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public abstract void display();
}