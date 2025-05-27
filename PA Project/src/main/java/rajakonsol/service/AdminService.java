package rajakonsol.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rajakonsol.util.DBUtil;
import rajakonsol.util.InputHelper;

/**
 * Service untuk autentikasi dan menu admin
 */
public class AdminService {

    private final KonsolService konsolService;

    /**
     * Konstruktor meng-inject semua service yang dibutuhkan oleh admin
     */
    public AdminService(KonsolService konsolService) {
        this.konsolService = konsolService;
    }

    public void menuAdmin() {
        boolean adminLoop = true;
        while (adminLoop) {
            System.out.println("\n--- Selamat Datang Admin! ---");
            System.out.println("[1] Kelola Konsol");
            System.out.println("[2] Lihat Tabel Akun Kasir");
            System.out.println("[3] Lihat Tabel Transaksi");
            System.out.println("[0] Logout");
            int pilihan = InputHelper.inputInt("Pilih menu");

            switch (pilihan) {
                case 1 -> konsolService.menuKelola();
                case 2 -> lihatTabelAkunKasir();
                case 3 -> tampilkanTransaksi();
                case 0 -> {
                    System.out.println("Logout berhasil.");
                    adminLoop = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void lihatTabelAkunKasir() {
        System.out.println("\n--- Tabel Akun Kasir ---");
        String query = """
                select *
                FROM akun WHERE role = 'Kasir'
                """;

        try (Connection conn = DBUtil.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.printf("%-15s %-20s %-20s %-10s%n",
                    "ID Akun", "Username", "Nama Lengkap", "No Telp");

            while (rs.next()) {
                System.out.printf("%-15s %-20s %-20s %-10s%n",
                        rs.getString("id_akun"),
                        rs.getString("username"),
                        rs.getString("nama_lengkap"),
                        rs.getString("no_telp"));
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data transaksi: " + e.getMessage());
        }
    }

    public void tampilkanTransaksi() {
        System.out.println("\n--- Daftar Transaksi ---");
        String query = """
                select s.id_sewa as id,
                       s.nama_penyewa,
                       k.kategori as nama_konsol,
                       s.durasi,
                       s.total,
                       s.tanggal_sewa,
                       s.jenis_sewa,
                       a.nama_lengkap as nama_kasir
                FROM sewa s
                JOIN konsol k ON s.id_konsol = k.id_konsol
                JOIN akun a ON s.id_kasir = a.id_akun
                """;

        try (Connection conn = DBUtil.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.printf("%-5s %-20s %-20s %-10s %-10s %-25s %-25s %-25s%n",
                    "ID", "Nama Penyewa", "Konsol", "Total", "Tanggal", "Durasi", "Jenis Sewa", "Kasir");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-20s Rp%-10.0f %-10s %-25s %-25s %-25s%n",
                        rs.getInt("id"),
                        rs.getString("nama_penyewa"),
                        rs.getString("nama_konsol"),
                        rs.getDouble("total"),
                        rs.getDate("tanggal_sewa").toString(),
                        rs.getInt("durasi"),
                        rs.getString("jenis_sewa"),
                        rs.getString("nama_kasir"));
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data transaksi: " + e.getMessage());
        }
    }

}