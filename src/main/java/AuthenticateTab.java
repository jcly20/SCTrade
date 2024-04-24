import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.UUID;

public class AuthenticateTab extends View  {
    private JTextField keyTextField;
    private JTextField secretKeyTextField;
    private JTextArea createTabHeader;
    private JPanel authenticateJPanel;
    private JButton loginButton;
    private JButton logoutButton;
    private String welcomeMessage = ("Welcome to SCTrade! Enter your Alpaca API keys to login.");


    public JPanel makeTab(){

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        authenticateJPanel = new JPanel();
        authenticateJPanel.setBackground(Color.BLACK);
        authenticateJPanel.setLayout(new GridLayout(3, 2));
        keyTextField = new JTextField(40);
        createTabHeader = new JTextArea(welcomeMessage);
        createTabHeader.setPreferredSize(new Dimension(50, 50));
        createTabHeader.setFont(new Font("Arial", 20, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        secretKeyTextField = new JTextField(40);
        jPanel.add(createTabHeader, BorderLayout.NORTH);
        authenticateJPanel.add(new JLabel("key ID:"));
        authenticateJPanel.add(keyTextField);
        authenticateJPanel.add(new JLabel("Secret Key:"));
        authenticateJPanel.add(secretKeyTextField);
        loginButton = new JButton("Login");
        authenticateJPanel.add(loginButton);

        logoutButton = new JButton("Logout");
        authenticateJPanel.add(this.logoutButton);
        jPanel.add(authenticateJPanel, BorderLayout.CENTER);
        return jPanel;
    }

    public JTextField getSecretKeyTextField() {
        return secretKeyTextField;
    }

    public JTextField getKeyTextField() {
        return keyTextField;
    }

    public void setLoginButtonActionListener(ActionListener al) {
        this.loginButton.addActionListener(al);
    }

    public void setLogoutButtonActionListener(ActionListener al){
        logoutButton.addActionListener(al);
    }

    protected void printInvalidKeysError(){
          createTabHeader.setText(welcomeMessage + "\nERROR: Invalid Keys! Could not login.");
    }
    protected void printSuccessfulLogin(UUID uuid){
        createTabHeader.setText("Welcome, you have successfully logged in!\n" +
                                "Acount UUID: " + uuid);
    }

    protected void logout(){
        keyTextField.setText("");
        secretKeyTextField.setText("");
        createTabHeader.setText(welcomeMessage);
    }

    public void viewController() {}

}

