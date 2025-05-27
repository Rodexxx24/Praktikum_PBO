package rajakonsol.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import rajakonsol.model.Admin;
import rajakonsol.model.Kasir;
import rajakonsol.model.User;
import rajakonsol.service.AdminService;
import rajakonsol.service.KasirService;
import rajakonsol.service.KonsolService;
import rajakonsol.service.TransaksiService;
import rajakonsol.util.DBUtil;
import rajakonsol.util.InputHelper;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil.connect();
            if (conn != null) {
                System.out.println("Berhasil terhubung ke database!");

            } else {
                System.out.println("Gagal terhubung ke database.");
                return;
            }

            KonsolService konsolService = new KonsolService();
            AdminService adminService = new AdminService(konsolService);

            boolean running = true;
            while (running) {
                System.out.println("\n===== SELAMAT DATANG DI RAJA KONSOL SAMARINDA =====");
                System.out.println("Silahkan pilih menu menggunakan angka:");
                System.out.println("[1] Login Sistem");
                System.out.println("[2] Registrasi Akun Kasir");
                System.out.println("[0] Keluar");

                int pilihan = InputHelper.inputInt("Pilih menu [1/2/0]: ");
                Main main = new Main();
                switch (pilihan) {
                    case 1 -> {
                        User user = main.login();
                        if (user != null) {
                            switch (user.getRole()) {
                                case "Admin" -> adminService.menuAdmin();
                                case "Kasir" -> {
                                    // Pastikan user adalah Kasir sebelum casting
                                    if (user instanceof Kasir kasir) {
                                        TransaksiService transaksiService = new TransaksiService(konsolService, kasir);
                                        KasirService kasirService = new KasirService(transaksiService);
                                        kasirService.menuKasir();
                                    } else {
                                        System.out.println("Akun kasir tidak valid.");
                                    }
                                }
                            }
                        }
                    }
                    case 2 -> {
                        boolean isRegistered = main.register();

                        if (isRegistered) {
                            System.out.println("Registrasi berhasil!");
                        } else {
                            System.out.println("Registrasi gagal.");
                        }
                        break;
                    }
                    case 0 -> {
                        System.out.println("Terima kasih telah menggunakan Raja Konsol!");
                        running = false;
                    }
                    default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            }

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    public User login() {
        int attempts = 0;
        while (attempts < 3) {
            String inputUser = InputHelper.inputString("Username");
            String inputPass = InputHelper.inputString("Password");
            String sql = "SELECT * FROM akun WHERE username = ? AND password = ?";
            try (Connection conn = DBUtil.connect();
                    PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, inputUser);
                ps.setString(2, inputPass);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("id_akun");
                    String role = rs.getString("role");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String nama_lengkap = rs.getString("nama_lengkap");
                    String no_telp = rs.getString("no_telp");
                    if ("Admin".equals(role)) {
                        return new Admin(id, role, username, password, nama_lengkap, no_telp);
                    } else {
                        return new Kasir(id, role, username, password, nama_lengkap, no_telp);
                    }
                } else {
                    attempts++;
                    System.out.println("Username atau password salah. Sisa kesempatan: " + (3 - attempts));
                }
            } catch (SQLException e) {
                System.out.println("Gagal memeriksa Akun: " + e.getMessage());
                return null;
            }
        }
        System.out.println("Kesempatan login telah habis. Program dihentikan.");
        InputHelper.tungguEnter();
        return null;
    }

    public Kasir getKasir(String id) {
        String sql = "SELECT * FROM akun WHERE id = ?";
        try (Connection conn = DBUtil.connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String idKasir = rs.getString("id_akun");
                String role = rs.getString("role");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nama_lengkap = rs.getString("nama_lengkap");
                String no_telp = rs.getString("no_telp");

                Kasir kasir = new Kasir(idKasir, role, username, password, nama_lengkap, no_telp);
                return kasir;
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari kasir: " + e.getMessage());
        }
        return null;
    }

    public boolean register() {
        try {
            System.out.println("\n=== Registrasi Akun Kasir ===");
            String username = InputHelper.inputString("Username");
            String password = InputHelper.inputString("Password");
            String namaLengkap = InputHelper.inputString("Nama Lengkap");
            String noTelp = InputHelper.inputString("No Telepon");

            // Check if username already exists
            String checkSql = "SELECT * FROM akun WHERE username = ?";
            try (Connection conn = DBUtil.connect();
                    PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
                checkPs.setString(1, username);
                ResultSet rs = checkPs.executeQuery();
                if (rs.next()) {
                    System.out.println("Username sudah digunakan. Silakan pilih username lain.");
                    return false;
                }
            }

            // Insert new kasir account
            Random random = new Random();
            String idKasir = "KSR" + String.format("%03d", random.nextInt(1000));
            String insertSql = "INSERT INTO akun (id_akun, role, username, password, nama_lengkap, no_telp) VALUES (?, ?, ?, ?, ?, ?)";
            try (Connection conn = DBUtil.connect();
                    PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, idKasir);
                ps.setString(2, "Kasir");
                ps.setString(3, username);
                ps.setString(4, password);
                ps.setString(5, namaLengkap);
                ps.setString(6, noTelp);

                int result = ps.executeUpdate();
                if (result > 0) {
                    System.out.println("Registrasi berhasil!");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal melakukan registrasi: " + e.getMessage());
        }
        return false;
    }
}