package rajakonsol.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rajakonsol.service.AdminService;

public class LoginAdminDialog extends JDialog {
    private final AdminService adminService;
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginAdminDialog(Frame parent, AdminService adminService) {
        super(parent, "Login Admin", true);
        this.adminService = adminService;

        setLayout(new BorderLayout(10, 10));
        setSize(350, 200);
        setLocationRelativeTo(parent);

        JPanel panelInput = new JPanel(new GridLayout(2, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panelInput.add(usernameField);

        panelInput.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panelInput.add(passwordField);

        JPanel panelButton = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Batal");

        panelButton.add(loginButton);
        panelButton.add(cancelButton);

        add(panelInput, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> attemptLogin());
        cancelButton.addActionListener(e -> dispose());
    }

    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (adminService.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Login berhasil. Selamat datang, Admin.");
            AdminPanelMenu adminPanel = new AdminPanelMenu(adminService);
            adminPanel.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }
} 
