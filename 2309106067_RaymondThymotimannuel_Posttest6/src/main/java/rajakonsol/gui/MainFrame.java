package rajakonsol.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import rajakonsol.service.AdminService;
import rajakonsol.service.KonsolService;
import rajakonsol.service.PenyewaService;
import rajakonsol.service.TransaksiService;

public class MainFrame extends JFrame {
    private final AdminService adminService;

    public MainFrame() {
        KonsolService konsolService = new KonsolService();
        PenyewaService penyewaService = new PenyewaService();
        TransaksiService transaksiService = new TransaksiService(konsolService, penyewaService);
        this.adminService = new AdminService(konsolService, penyewaService, transaksiService);

        setTitle("Raja Konsol");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Selamat Datang di Raja Konsol!", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnLoginAdmin = new JButton("Login Admin");
        JButton btnPenyewaan = new JButton("Penyewaan Konsol");
        JButton btnKeluar = new JButton("Keluar");

        buttonPanel.add(btnLoginAdmin);
        buttonPanel.add(btnPenyewaan);
        buttonPanel.add(btnKeluar);

        add(buttonPanel, BorderLayout.CENTER);

        // Aksi tombol
        btnLoginAdmin.addActionListener(e -> {
            LoginAdminDialog loginDialog = new LoginAdminDialog(this, adminService);
            loginDialog.setVisible(true);
        });

        btnPenyewaan.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fitur Penyewaan GUI belum tersedia.");
        });

        btnKeluar.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
