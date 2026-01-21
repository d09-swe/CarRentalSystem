package gui;

import model.Clerk;

import javax.swing.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        Clerk clerk = new Clerk("admin", "1234");

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] fields = {
                "Username:", usernameField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(
                null, fields, "Clerk Login", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION &&
                clerk.login(usernameField.getText(),
                        new String(passwordField.getPassword()))) {
            new DashboardFrame();
        } else {
            JOptionPane.showMessageDialog(null, "Login Failed");
            System.exit(0);
        }
    }
}
