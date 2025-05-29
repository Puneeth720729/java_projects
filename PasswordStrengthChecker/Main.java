import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    private JButton checkButton;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private JCheckBox showPasswordCheck;
    private JButton clearButton;
    private JLabel strengthlabel;

    public Main() {
        setTitle("Password Strength Checker");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel promptLabel = new JLabel("Enter your password:");
        promptLabel.setBounds(20, 20, 150, 25);
        add(promptLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 20, 150, 25);
        add(passwordField);

        checkButton = new JButton("Check Password");
        checkButton.setBounds(100, 60, 140, 30);
        checkButton.addActionListener(this);
        add(checkButton);

        showPasswordCheck = new JCheckBox("Show Password");
        showPasswordCheck.setBounds(250, 60, 150, 30);
        add(showPasswordCheck);
        showPasswordCheck.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });

        strengthlabel = new JLabel("");
        strengthlabel.setBounds(100, 100, 300, 20);
        add(strengthlabel);

        messageLabel = new JLabel("");
        messageLabel.setBounds(100, 125, 300, 40);
        add(messageLabel);

        clearButton = new JButton("Clear");
        clearButton.setBounds(200, 170, 100, 30);
        add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordField.setText("");
                messageLabel.setText("");
                strengthlabel.setText("");
                showPasswordCheck.setSelected(false);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String password = new String(passwordField.getPassword());
        int strength = 0;
        if (password.length() > 8) strength++;
        if (containsUpperCase(password)) strength++;
        if (containsLowerCase(password)) strength++;
        if (containsDigit(password)) strength++;
        if (containsSpecialChar(password)) strength++;

        if (strength <= 2) {
            strengthlabel.setText("Weak password");
            strengthlabel.setForeground(Color.RED);
        } else if (strength == 3 || strength == 4) {
            strengthlabel.setText("Moderate password");
            strengthlabel.setForeground(Color.ORANGE);
        } else {
            strengthlabel.setText("Strong password");
            strengthlabel.setForeground(Color.GREEN);
        }

        if (password.length() < 8) {
            messageLabel.setText("Password too short (min 8 chars)");
        } else if (!containsUpperCase(password)) {
            messageLabel.setText("Must contain at least one uppercase letter");
        } else if (!containsLowerCase(password)) {
            messageLabel.setText("Must contain at least one lowercase letter");
        } else if (!containsDigit(password)) {
            messageLabel.setText("Must contain at least one digit");
        } else if (!containsSpecialChar(password)) {
            messageLabel.setText("Must contain at least one special char");
        } else {
            messageLabel.setText("Password is strong!");
        }
    }

    private boolean containsUpperCase(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) return true;
        }
        return false;
    }

    private boolean containsLowerCase(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) return true;
        }
        return false;
    }

    private boolean containsDigit(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }
        return false;
    }

    private boolean containsSpecialChar(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setVisible(true);
    }
}
