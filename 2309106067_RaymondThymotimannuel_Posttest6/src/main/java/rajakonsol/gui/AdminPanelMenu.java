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

public class AdminPanelMenu extends JPanel {
    private final AdminService adminService;

    public AdminPanelMenu(AdminService adminService) {
        this.adminService = adminService;
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Menu Admin", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton btnKelolaKonsol = new JButton("Kelola Konsol");
        JButton btnKelolaPenyewa = new JButton("Kelola Penyewa");
        JButton btnKelolaTransaksi = new JButton("Kelola Transaksi");
        JButton btnLogout = new JButton("Logout");

        buttonPanel.add(btnKelolaKonsol);
        buttonPanel.add(btnKelolaPenyewa);
        buttonPanel.add(btnKelolaTransaksi);
        buttonPanel.add(btnLogout);

        add(buttonPanel, BorderLayout.CENTER);

        btnKelolaKonsol.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fitur Kelola Konsol (GUI) belum tersedia.");
            // Bisa diganti panel khusus jika fitur GUI sudah dibuat
        });

        btnKelolaPenyewa.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fitur Kelola Penyewa (GUI) belum tersedia.");
        });

        btnKelolaTransaksi.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fitur Kelola Transaksi (GUI) belum tersedia.");
        });

        btnLogout.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new MainPanel(topFrame, adminService));
            topFrame.validate();
        });
    }

    public AdminService getAdminService() {
        return adminService;
    }
}