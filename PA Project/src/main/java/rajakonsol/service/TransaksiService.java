package rajakonsol.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import rajakonsol.model.Kasir;
import rajakonsol.model.Konsol;
import rajakonsol.util.DBUtil;
import rajakonsol.util.InputHelper;

public class TransaksiService {
    private final KonsolService konsolService;
    private final Kasir kasir;

    public TransaksiService(KonsolService konsolService, Kasir kasir) {
        this.konsolService = konsolService;
        this.kasir = kasir;
    }

    public void tambahTransaksi() {
        try (Connection conn = DBUtil.connect()) {
            System.out.println("\n--- Tambah Transaksi ---");
            String namaPenyewa = InputHelper.inputString("Nama Penyewa: ");
            String noHp = InputHelper.inputString("No HP: ");
            String alamat = InputHelper.inputString("Alamat: ");
            if (namaPenyewa.isBlank() || noHp.isBlank() || alamat.isBlank()) {
                System.out.println("Semua data wajib diisi.");
                return;
            }
            konsolService.tampilkanKonsol();

            String idKonsol = InputHelper.inputString("ID Konsol: ");
            Konsol konsol = cariKonsol(idKonsol);
            if (konsol == null) {
                System.out.println("Konsol tidak ditemukan.");
                return;
            }

            boolean perHari = InputHelper.inputYaTidak("Sewa per hari? (y/n): ");
            String jenisSewa = perHari ? "Jam" : "Hari";
            int durasi = InputHelper.inputInt(perHari ? "Durasi sewa (hari): " : "Durasi sewa (jam): ");
            if (durasi <= 0) {
                System.out.println("Durasi tidak valid.");
                return;
            }

            int total = (int) (perHari ? konsol.getHarga_sewa() * 24 * durasi * 0.8 // Diskon 20% untuk sewa per hari
                    : konsol.getHarga_sewa() * durasi); // Sewa per jam

            String sql = "INSERT INTO sewa (id_konsol, id_kasir, nama_penyewa, alamat_penyewa, no_telp, jenis_sewa, durasi, tanggal_sewa, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, idKonsol);
                stmt.setString(2, kasir.getId());
                stmt.setString(3, namaPenyewa);
                stmt.setString(4, alamat);
                stmt.setString(5, noHp);
                stmt.setString(6, jenisSewa);
                stmt.setInt(7, durasi);
                stmt.setDate(8, Date.valueOf(LocalDateTime.now().toLocalDate()));
                stmt.setInt(9, total);
                int inserted = stmt.executeUpdate();

                if (inserted > 0) {
                    // Update stok konsol
                    String updateKonsol = "UPDATE konsol SET status = 'Disewa' WHERE id_konsol = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateKonsol)) {
                        updateStmt.setString(1, idKonsol);
                        updateStmt.executeUpdate();
                    }

                    System.out.println("Transaksi berhasil ditambahkan.");
                    // System.out.println("Total Bayar: Rp" + total);
                } else {
                    System.out.println("Gagal menambahkan transaksi.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan transaksi: " + e.getMessage());
        }
    }

    // @Override
    public void tampilkanTransaksi() {
        System.out.println("\n--- Daftar Transaksi ---");
        String query = """
                SELECT t.id, p.nama AS nama_penyewa, k.nama AS nama_konsol,
                       t.durasi, t.total, t.tanggal_sewa
                FROM transaksi t
                JOIN penyewa p ON t.id_penyewa = p.id
                JOIN konsol k ON t.id_konsol = k.id
                """;

        try (Connection conn = DBUtil.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.printf("%-5s %-20s %-20s %-10s %-10s %-15s%n",
                    "ID", "Nama Penyewa", "Konsol", "Durasi", "Total", "Tanggal");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-20s %-10d Rp%-10.0f %-15s%n",
                        rs.getInt("id"),
                        rs.getString("nama_penyewa"),
                        rs.getString("nama_konsol"),
                        rs.getInt("durasi"),
                        rs.getDouble("total"),
                        rs.getDate("tanggal_sewa").toString());
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data transaksi: " + e.getMessage());
        }
    }

    public Konsol cariKonsol(String id) {
        String sql = "SELECT * FROM konsol WHERE id_konsol = ?";
        try (Connection conn = DBUtil.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String idKonsol = rs.getString("id_konsol");
                String kategori = rs.getString("kategori");
                int hargaSewa = rs.getInt("harga_sewa");
                // Konversi status string ke boolean
                boolean status = "Tersedia".equalsIgnoreCase(rs.getString("status"));
                return new Konsol(idKonsol, kategori, hargaSewa, status);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari konsol: " + e.getMessage());
        }
        return null;
    }

}
