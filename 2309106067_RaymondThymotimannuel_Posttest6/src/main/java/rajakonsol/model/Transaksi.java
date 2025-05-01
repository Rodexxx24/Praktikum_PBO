package rajakonsol.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Model Transaksi untuk mencatat proses penyewaan
 */
public class Transaksi {
    private final String idTransaksi;
    private final Penyewa penyewa;
    private final Konsol konsol;
    private final int durasi; // dalam jam atau hari sesuai tipe sewa
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
    }

    public Transaksi(int durasi, String idTransaksi, Konsol konsol, Penyewa penyewa, String tipeSewa, LocalDateTime waktuMulai, LocalDateTime waktuSelesai) {
        this.durasi = durasi;
        this.idTransaksi = idTransaksi;
        this.konsol = konsol;
        this.penyewa = penyewa;
        this.tipeSewa = tipeSewa;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
    }

    private LocalDateTime hitungWaktuSelesai() {
        return tipeSewa.equalsIgnoreCase("jam") ? waktuMulai.plusHours(durasi) : waktuMulai.plusDays(durasi);
    }

    private double hitungBiaya() {
        return durasi * konsol.getHargaSewa(tipeSewa); // Pastikan untuk memberikan tipeSewa sebagai parameter
    }
    

    public void prosesPengembalian(LocalDateTime waktuPengembalian) {
        this.dikembalikan = true;
        if (waktuPengembalian.isAfter(waktuSelesai)) {
            long keterlambatan;
            if (tipeSewa.equalsIgnoreCase("jam")) {
                keterlambatan = java.time.Duration.between(waktuSelesai, waktuPengembalian).toHours();
                if (keterlambatan == 0) keterlambatan = 1; // denda dihitung mulai 1 jam keterlambatan
                this.denda = keterlambatan * 10000;
            } else {
                keterlambatan = java.time.Duration.between(waktuSelesai, waktuPengembalian).toHours();
                if (keterlambatan == 0) keterlambatan = 1;
                this.denda = keterlambatan * 50000;
            }
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

    // Getter
    public String getIdTransaksi() {
        return idTransaksi;
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public Konsol getKonsol() {
        return konsol;
    }

    public int getDurasi() {
        return durasi;
    }

    public String getTipeSewa() {
        return tipeSewa;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public double getDenda() {
        return denda;
    }

    public boolean isDikembalikan() {
        return dikembalikan;
    }

    public LocalDateTime getWaktuSelesai() {
        return waktuSelesai;
    }
}
