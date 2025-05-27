package rajakonsol.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rajakonsol.util.DBUtil;
import rajakonsol.util.InputHelper;

public class KasirService {

    private final TransaksiService transaksiService;

    /**
     * Konstruktor meng-inject semua service yang dibutuhkan oleh kasir
     */
    public KasirService(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }

    /**
     * Menu utama untuk kasir, memanggil service sesuai pilihan
     */
    public void menuKasir() {
        boolean adminLoop = true;
        while (adminLoop) {
            System.out.println("\n--- Selamat Datang Di Penyewaan Raja Konsol ---");
            System.out.println("[1] Lakukan Penyewaan Konsol");
            System.out.println("[2] Urut Berdasarkan Kategori Ascending");
            System.out.println("[3] Urut Berdasarkan Kategori Descending");
            System.out.println("[4] Filter Konsol Berdasarkan Kategori");
            System.out.println("[0] Logout");
            int pilihan = InputHelper.inputInt("Pilih menu");

            switch (pilihan) {
                case 1 -> transaksiService.tambahTransaksi();
                case 2 -> TampilkanKonsol("ASC");
                case 3 -> TampilkanKonsol("DESC");
                case 4 -> filterKonsolByKategori();
                case 0 -> {
                    System.out.println("Logout berhasil.");
                    adminLoop = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void TampilkanKonsol(String urutan) {
        System.out.println("\nDaftar Konsol:");
        String sql = "SELECT * FROM konsol WHERE isdeleted IS NULL ORDER BY kategori " + urutan;

        try (Connection conn = DBUtil.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-15s %-15s %-15s %-15s\n", "ID", "Kategori", "Harga Sewa", "Status");
            while (rs.next()) {
                System.out.printf("%-15s %-15s %-15s %-15s\n",
                        rs.getString("id_konsol"),
                        rs.getString("kategori"),
                        rs.getString("harga_sewa"),
                        rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data konsol: " + e.getMessage());
        }
        InputHelper.tungguEnter();
    }

    public void filterKonsolByKategori() {
        String kategori = InputHelper.inputString("Masukkan Kategori Konsol (PS1, PS2, PS3, PS4, PS5): ");
        System.out.println("\nDaftar Konsol Kategori " + kategori + ":");
        String sql = "SELECT * FROM konsol WHERE kategori = ? AND isdeleted IS NULL";

        try (Connection conn = DBUtil.connect();
                var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kategori);
            ResultSet rs = stmt.executeQuery();

            boolean dataFound = false;
            System.out.printf("%-15s %-15s %-15s %-15s\n", "ID", "Kategori", "Harga Sewa", "Status");
            while (rs.next()) {
                dataFound = true;
                System.out.printf("%-15s %-15s %-15s %-15s\n",
                        rs.getString("id_konsol"),
                        rs.getString("kategori"),
                        rs.getString("harga_sewa"),
                        rs.getString("status"));
            }

            if (!dataFound) {
                System.out.println("Kategori " + kategori + " tidak ditemukan!");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data konsol: " + e.getMessage());
        }
        InputHelper.tungguEnter();
    }

}
