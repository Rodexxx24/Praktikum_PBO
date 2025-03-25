package models;

public class Review {
    private int id;
    private Pengguna pengguna;
    private Konsol konsol;
    private int rating;
    private String komentar;

    public Review(int id, Pengguna pengguna, Konsol konsol, int rating, String komentar) {
        this.id = id;
        this.pengguna = pengguna;
        this.konsol = konsol;
        this.rating = rating;
        this.komentar = komentar;
    }

    public int getId() { return id; }
    public Pengguna getPengguna() { return pengguna; }
    public Konsol getKonsol() { return konsol; }
    public int getRating() { return rating; }
    public String getKomentar() { return komentar; }

    @Override
    public String toString() {
        return "Review [ID: " + id + ", Pengguna: " + pengguna.getNama() + 
               ", Konsol: " + konsol.getNama() + ", Rating: " + rating + 
               ", Komentar: " + komentar + "]";
    }
}
