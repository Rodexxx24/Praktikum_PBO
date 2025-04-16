package models;

public class Pengguna {
    private int id;
    private String nama;
    private String email;

    // Konstruktor dengan 3 parameter (id, nama, email)
    public Pengguna(int id, String nama, String email) {
        this.id = id;
        this.nama = nama;
        this.email = email;
    }

    // Konstruktor dengan 1 parameter (nama) untuk mendukung penggunaan sederhana
    public Pengguna(String nama) {
        this.nama = nama;
        this.id = 0; // Default id jika tidak diberikan
        this.email = ""; // Default email jika tidak diberikan
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

    public void setId(int id) {
        this.id = id;
    }
}
