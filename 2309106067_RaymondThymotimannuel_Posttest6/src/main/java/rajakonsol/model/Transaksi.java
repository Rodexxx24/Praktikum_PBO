package rajakonsol.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Model Transaksi untuk mencatat proses penyewaan
 */
public class Transaksi {
    private static int jumlahTransaksi = 0; // 🔸 Static variable

    private final String idTransaksi;
    private final Penyewa penyewa;
    private final Konsol konsol;
    private final int durasi;
    private final String tipeSewa;
    private final LocalDateTime waktuMulai;
    private final LocalDateTime waktuSelesai;
    private double totalBiaya;
    private double denda;
    private boolean dikembalikan;

    public Transaksi(String idTransaksi, Penyewa penyewa, Konsol konsol, int durasi, String tipeSewa, LocalDateTime waktuMulai) {
        this.idTransaksi = idTransaksi;
        this.penyewa = penyewa;
        this.konsol = konsol;
        this.durasi = durasi;
        this.tipeSewa = tipeSewa;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = hitungWaktuSelesai();
        this.totalBiaya = hitungBiaya();
        this.denda = 0;
        this.dikembalikan = false;
        jumlahTransaksi++; // 🔸 Tambah counter setiap objek dibuat
    }

    public Transaksi(int durasi, String idTransaksi, Konsol konsol, Penyewa penyewa, String tipeSewa, LocalDateTime waktuMulai, LocalDateTime waktuSelesai) {
        this.durasi = durasi;
        this.idTransaksi = idTransaksi;
        this.konsol = konsol;
        this.penyewa = penyewa;
        this.tipeSewa = tipeSewa;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        jumlahTransaksi++;
    }

    private LocalDateTime hitungWaktuSelesai() {
        return tipeSewa.equalsIgnoreCase("jam") ? waktuMulai.plusHours(durasi) : waktuMulai.plusDays(durasi);
    }

    private double hitungBiaya() {
        return durasi * konsol.getHargaSewa(tipeSewa);
    }

    public void prosesPengembalian(LocalDateTime waktuPengembalian) {
        try {
            this.dikembalikan = true;
            if (waktuPengembalian.isAfter(waktuSelesai)) {
                long keterlambatan;
                if (tipeSewa.equalsIgnoreCase("jam")) {
                    keterlambatan = java.time.Duration.between(waktuSelesai, waktuPengembalian).toHours();
                    if (keterlambatan == 0) keterlambatan = 1;
                    this.denda = keterlambatan * 10000;
                } else {
                    keterlambatan = java.time.Duration.between(waktuSelesai, waktuPengembalian).toHours();
                    if (keterlambatan == 0) keterlambatan = 1;
                    this.denda = keterlambatan * 50000;
                }
            }
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat memproses pengembalian: " + e.getMessage());
        }
    }

    public void tampilkanInfo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.printf(
                "ID: %s | Penyewa: %s | Konsol: %s | Durasi: %d %s | Total: Rp%.0f | Denda: Rp%.0f | Status: %s\n",
                idTransaksi,
                penyewa.getNama(),
                konsol.getNama(),
                durasi,
                tipeSewa,
                totalBiaya,
                denda,
                (dikembalikan ? "Sudah Dikembalikan" : "Belum")
        );
        System.out.println("Waktu Sewa : " + waktuMulai.format(dtf));
        System.out.println("Waktu Kembali (seharusnya): " + waktuSelesai.format(dtf));
    }

    // 🔸 Static method untuk mengambil jumlah total transaksi
    public static int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    // Getter
    public String getIdTransaksi() { return idTransaksi; }
    public Penyewa getPenyewa() { return penyewa; }
    public Konsol getKonsol() { return konsol; }
    public int getDurasi() { return durasi; }
    public String getTipeSewa() { return tipeSewa; }
    public double getTotalBiaya() { return totalBiaya; }
    public double getDenda() { return denda; }
    public boolean isDikembalikan() { return dikembalikan; }
    public LocalDateTime getWaktuSelesai() { return waktuSelesai; }
}
