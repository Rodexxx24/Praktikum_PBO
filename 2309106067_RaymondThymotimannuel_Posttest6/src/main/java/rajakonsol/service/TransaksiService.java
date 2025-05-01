package rajakonsol.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import rajakonsol.model.Konsol;
import rajakonsol.model.Penyewa;
import rajakonsol.model.Transaksi;
import rajakonsol.util.InputHelper;

public class TransaksiService implements TransaksiInterface {
    private final List<Transaksi> daftarTransaksi = new ArrayList<>();
    private final KonsolService konsolService;
    private final PenyewaService penyewaService;

    // Static field untuk mencatat total transaksi di semua instance
    private static int totalTransaksi = 0;

    public TransaksiService(KonsolService konsolService, PenyewaService penyewaService) {
        if (konsolService == null || penyewaService == null) {
            throw new IllegalArgumentException("KonsolService dan PenyewaService tidak boleh null");
        }
        this.konsolService = konsolService;
        this.penyewaService = penyewaService;
    }
    

    public void menuKelola() {
        boolean loop = true;
        while (loop) {
            System.out.println("\n--- Kelola Transaksi ---");
            System.out.println("1. Tambah Transaksi");
            System.out.println("2. Lihat Semua Transaksi");
            System.out.println("3. Proses Pengembalian");
            System.out.println("4. Lihat Total Transaksi (Static)");
            System.out.println("0. Kembali");
            int pilihan = InputHelper.inputInt("Pilih menu");
            switch (pilihan) {
                case 1 -> tambahTransaksi();
                case 2 -> tampilkanTransaksi();
                case 3 -> prosesPengembalian();
                case 4 -> System.out.println("Total transaksi: " + totalTransaksi);
                case 0 -> loop = false;
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void tambahTransaksi() {
        System.out.println("\n--- Tambah Transaksi ---");
        try {
            String idPenyewa = InputHelper.inputString("ID Penyewa");
            Penyewa penyewa = cariPenyewa(idPenyewa);
            if (penyewa == null) {
                System.out.println("Penyewa tidak ditemukan.");
                return;
            }

            String idKonsol = InputHelper.inputString("ID Konsol");
            Konsol konsol = cariKonsol(idKonsol);
            if (konsol == null || konsol.getJumlah() <= 0) {
                System.out.println("Konsol tidak tersedia.");
                return;
            }

            boolean perHari = InputHelper.inputYaTidak("Sewa per hari? (jika tidak, dihitung per jam)");
            int durasi = InputHelper.inputInt(perHari ? "Lama sewa (hari)" : "Lama sewa (jam)");

            String tipeSewa = perHari ? "hari" : "jam";
            String idTransaksi = "TRX-" + (daftarTransaksi.size() + 1);
            LocalDateTime waktuMulai = LocalDateTime.now();

            Transaksi transaksi = new Transaksi(idTransaksi, penyewa, konsol, durasi, tipeSewa, waktuMulai);
            daftarTransaksi.add(transaksi);

            konsol.setJumlah(konsol.getJumlah() - 1);
            totalTransaksi++; // menambah static counter

            System.out.println("Transaksi berhasil ditambahkan. Total bayar: Rp" + transaksi.getTotalBiaya());
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambahkan transaksi: " + e.getMessage());
        }
    }

    @Override
    public void tampilkanTransaksi() {
        System.out.println("\n--- Daftar Transaksi ---");
        if (daftarTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        System.out.printf("%-5s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
                "No.", "ID Pen.", "ID Kons.", "Durasi", "Tipe", "Bayar", "Denda", "Status");
        int idx = 1;
        for (Transaksi t : daftarTransaksi) {
            String status = t.isDikembalikan() ? "Kembali" : "Dipinjam";
            System.out.printf("%-5d %-10s %-10s %-10d %-10s %-10.0f %-10.0f %-10s\n",
                    idx++, t.getPenyewa().getId(), t.getKonsol().getIdKonsol(),
                    t.getDurasi(), t.getTipeSewa(), t.getTotalBiaya(), t.getDenda(), status);
        }
    }

    @Override
    public void registrasiDanTambahTransaksi() {
        System.out.println("\n--- Registrasi Penyewa Baru ---");
        String id = InputHelper.inputString("ID Penyewa");
        if (cariPenyewa(id) != null) {
            System.out.println("ID penyewa sudah terdaftar.");
            return;
        }

        String nama = InputHelper.inputString("Nama Penyewa");
        String telepon = InputHelper.inputString("No. Telepon");
        String alamat = InputHelper.inputString("Alamat");

        Penyewa penyewaBaru = new Penyewa(id, nama, telepon, alamat);
        penyewaService.getDaftarPenyewa().add(penyewaBaru);

        System.out.println("Registrasi berhasil. Melanjutkan ke proses transaksi...");
        tambahTransaksi(); // lanjut ke input ID penyewa, dst
    }

    private void prosesPengembalian() {
        System.out.println("\n--- Proses Pengembalian ---");
        int nomor = InputHelper.inputInt("Masukkan nomor transaksi (lihat daftar untuk nomor)");
        if (nomor < 1 || nomor > daftarTransaksi.size()) {
            System.out.println("Nomor transaksi tidak valid.");
            return;
        }
        Transaksi t = daftarTransaksi.get(nomor - 1);
        if (t.isDikembalikan()) {
            System.out.println("Transaksi sudah dikembalikan.");
            return;
        }
        LocalDateTime waktuKembali = LocalDateTime.now();
        t.prosesPengembalian(waktuKembali);

        // Tambahkan kembali stok konsol
        Konsol k = t.getKonsol();
        k.setJumlah(k.getJumlah() + 1);

        System.out.println("Pengembalian berhasil. Denda: Rp" + t.getDenda());
    }

    private Penyewa cariPenyewa(String id) {
        if (penyewaService == null || penyewaService.getDaftarPenyewa() == null) return null;
        return penyewaService.getDaftarPenyewa().stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }
    
    private Konsol cariKonsol(String id) {
        if (konsolService == null || konsolService.getDaftarKonsol() == null) return null;
        return konsolService.getDaftarKonsol().stream()
                .filter(k -> k.getIdKonsol().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }    

    public List<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public static int getTotalTransaksi() {
        return totalTransaksi;
    }
}
