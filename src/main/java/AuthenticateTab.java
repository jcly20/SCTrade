import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.UUID;

public class AuthenticateTab extends View  {
    private JTextField keyTextField;
    private JTextField secretKeyTextField;
    private JTextArea createTabHeader;
    private JPanel enterKeysJPanel;
    private JButton loginButton;
    private JButton logoutButton;
    private String welcomeMessage = ("Welcome to SCTrade! Enter your Alpaca api keys to login.");


    public JPanel makeTab(){

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        enterKeysJPanel = new JPanel();
        enterKeysJPanel.setLayout(new GridLayout(3, 2));
        keyTextField = new JTextField(40);
        createTabHeader = new JTextArea(welcomeMessage);
        createTabHeader.setPreferredSize(new Dimension(50, 50));
        createTabHeader.setFont(new Font("Arial", 20, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        secretKeyTextField = new JTextField(40);
        jPanel.add(createTabHeader, BorderLayout.NORTH);
        enterKeysJPanel.add(new JLabel("key ID:"));
        enterKeysJPanel.add(keyTextField);
        enterKeysJPanel.add(new JLabel("Secret Key:"));
        enterKeysJPanel.add(secretKeyTextField);
        loginButton = new JButton("Login");
        enterKeysJPanel.add(loginButton);

        logoutButton = new JButton("Logout");
        enterKeysJPanel.add(this.logoutButton);
        jPanel.add(enterKeysJPanel, BorderLayout.CENTER);
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

