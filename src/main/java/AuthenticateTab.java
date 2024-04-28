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
    private JLabel keyLabel;
    private JLabel secretKeyLabel;
    private String welcomeMessage = ("Welcome to SCTrade! Enter your Alpaca API keys to login.");


    public JPanel makeTab(){

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        authenticateJPanel = new JPanel();
        authenticateJPanel.setLayout(new GridLayout(6, 2));

        createTabHeader = new JTextArea(welcomeMessage);
        createTabHeader.setPreferredSize(new Dimension(50, 50));
        createTabHeader.setFont(new Font("Arial", Font.PLAIN, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        jPanel.add(createTabHeader, BorderLayout.NORTH);

        keyLabel = new JLabel("key ID:");
        keyLabel.setPreferredSize(new Dimension(25, 25));
        authenticateJPanel.add(keyLabel);

        keyTextField = new JTextField(40);
        keyTextField.setSize(10, 10);
        authenticateJPanel.add(keyTextField);

        secretKeyLabel = new JLabel("key ID:");
        secretKeyLabel.setPreferredSize(new Dimension(25, 25));
        authenticateJPanel.add(secretKeyLabel);

        secretKeyTextField = new JTextField(40);
        secretKeyTextField.setPreferredSize(new Dimension(25, 25));
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

}

