package rajakonsol.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import rajakonsol.model.Konsol;
import rajakonsol.util.DBUtil;
import rajakonsol.util.InputHelper;

public class KonsolService {

    public void menuKelola() {
        boolean loop = true;
        while (loop) {
            System.out.println("\n--- Kelola Konsol ---");
            System.out.println("1. Tambah Konsol");
            System.out.println("2. Lihat Daftar Konsol");
            System.out.println("3. Update Konsol");
            System.out.println("4. Hapus Konsol");
            System.out.println("0. Kembali");

            int pilihan = InputHelper.inputInt("Pilih menu");
            switch (pilihan) {
                case 1 -> tambahKonsol();
                case 2 -> tampilkanKonsol();
                case 3 -> updateKonsol();
                case 4 -> hapusKonsol();
                case 0 -> loop = false;
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void tambahKonsol() {
        System.out.println("\n--- Tambah Konsol Baru ---");
        String id = InputHelper.inputNonEmptyString("ID Konsol (misal: PSDex001, XbDex001)");
        System.out.println("1. PS1");
        System.out.println("2. PS2");
        System.out.println("3. PS3");
        System.out.println("4. PS4");
        System.out.println("5. PS5");
        int nama = InputHelper.inputInt("Nama Konsol");
        if (nama < 1 || nama > 5) {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            return;
        }
        String namaKonsol;
        switch (nama) {
            case 1 -> namaKonsol = "PS1";
            case 2 -> namaKonsol = "PS2";
            case 3 -> namaKonsol = "PS3";
            case 4 -> namaKonsol = "PS4";
            case 5 -> namaKonsol = "PS5";
            default -> {
                System.out.println("Pilihan tidak valid.");
                return;
            }
        }

        int harga_sewa = InputHelper.inputInt("Harga Sewa");

        String sql = "INSERT INTO konsol (id_konsol, kategori, harga_sewa, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.connect();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, id);
            ps.setString(2, namaKonsol);
            ps.setInt(3, harga_sewa);
            ps.setString(4, "Tersedia");
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("✅ Konsol berhasil ditambahkan dengan ID: " + rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Gagal menambah konsol: " + e.getMessage());
        }
        InputHelper.tungguEnter();
    }

    public void tampilkanKonsol() {
        System.out.println("\nDaftar Konsol:");
        String sql = "SELECT * FROM konsol WHERE isdeleted IS NULL";

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

    public void updateKonsol() {
        tampilkanKonsol();
        System.out.println("\nUpdate Konsol");
        String id = InputHelper.inputString("Masukkan ID Konsol yang akan diubah");
        if (id.isBlank()) {
            System.out.println("ID Konsol tidak boleh kosong.");
            return;
        }
        String sqlid = "SELECT * FROM konsol WHERE id_konsol = ?";
        try (Connection conn = DBUtil.connect();
                PreparedStatement ps = conn.prepareStatement(sqlid)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("ID Konsol tidak ditemukan.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Gagal memeriksa ID Konsol: " + e.getMessage());
            return;
        }
        System.out.println("1. PS1");
        System.out.println("2. PS2");
        System.out.println("3. PS3");
        System.out.println("4. PS4");
        System.out.println("5. PS5");
        int nama = InputHelper.inputInt("Nama Konsol");
        if (nama < 1 || nama > 5) {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            return;
        }
        String namaKonsol;
        switch (nama) {
            case 1 -> namaKonsol = "PS1";
            case 2 -> namaKonsol = "PS2";
            case 3 -> namaKonsol = "PS3";
            case 4 -> namaKonsol = "PS4";
            case 5 -> namaKonsol = "PS5";
            default -> {
                System.out.println("Pilihan tidak valid.");
                return;
            }
        }
        int harga_sewa = InputHelper.inputInt("Harga Sewa Baru");
        System.out.println("1. Tersedia");
        System.out.println("2. Disewa");
        int pilihanStatus = InputHelper.inputInt("Kondisi Status");
        if (pilihanStatus < 1 || pilihanStatus > 2) {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            return;
        }
        String status;
        switch (pilihanStatus) {
            case 1 -> status = "Tersedia";
            case 2 -> status = "Disewa";
            default -> {
                System.out.println("Pilihan tidak valid.");
                return;
            }
        }

        String sql = "UPDATE konsol SET kategori = ?, harga_sewa = ?, status = ? WHERE id_konsol = ?";

        try (Connection conn = DBUtil.connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, namaKonsol);
            ps.setInt(2, harga_sewa);
            ps.setString(3, status);
            ps.setString(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Konsol berhasil diperbarui.");
            } else {
                System.out.println("ID Konsol tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate konsol: " + e.getMessage());
        }
        InputHelper.tungguEnter();
    }

    public void hapusKonsol() {
        tampilkanKonsol();
        System.out.println("\nHapus Konsol");
        String id = InputHelper.inputString("Masukkan ID Konsol yang akan dihapus");

        try (Connection conn = DBUtil.connect();
                PreparedStatement ps = conn.prepareStatement("UPDATE konsol SET isdeleted = 1 WHERE id_konsol = ?")) {

            ps.setString(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Konsol berhasil dihapus.");
            } else {
                System.out.println("ID Konsol tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus konsol: " + e.getMessage());
        }
        InputHelper.tungguEnter();
    }

    public ArrayList<Konsol> getDaftarKonsol() {
        ArrayList<Konsol> list = new ArrayList<>();
        String sql = "SELECT * FROM konsol";

        try (Connection conn = DBUtil.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Konsol k = new Konsol(
                        rs.getString("id_konsol"),
                        rs.getString("kategori"),
                        rs.getInt("harga_sewa"),
                        "Tersedia".equalsIgnoreCase(rs.getString("status")));
                list.add(k);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data konsol: " + e.getMessage());
        }
        return list;
    }

    public Konsol cariKonsol(String id) {
        String sql = "SELECT * FROM konsol WHERE id_konsol = ?";
        try (Connection conn = DBUtil.connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Konsol(
                        rs.getString("id_konsol"),
                        rs.getString("kategori"),
                        rs.getInt("harga_sewa"),
                        "Tersedia".equalsIgnoreCase(rs.getString("status")));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari konsol: " + e.getMessage());
        }
        return null;
    }
}
