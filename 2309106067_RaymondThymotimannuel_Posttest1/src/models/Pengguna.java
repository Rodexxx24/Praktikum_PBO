package models;

public class Pengguna {
    private int id;
    private String nama;
    private String email;

    public Pengguna(int id, String nama, String email) {
        this.id = id;
        this.nama = nama;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getEmail() { return email; }

    public void setNama(String nama) { this.nama = nama; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Pengguna [ID: " + id + ", Nama: " + nama + ", Email: " + email + "]";
    }
}
