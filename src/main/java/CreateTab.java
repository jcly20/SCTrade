import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateTab extends View  {
    private JTextField keyTextField;
    private JTextField secretKeyTextField;


    public JPanel makeTab(){

        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,2));
        keyTextField = new JTextField(40);
        secretKeyTextField = new JTextField(40);
        jPanel.add(new JLabel("key ID:"));
        jPanel.add(keyTextField);
        jPanel.add(new JLabel("Secret Key:"));
        jPanel.add(secretKeyTextField);

        createButton = new JButton("Authenticate");
        jPanel.add(createButton);
        return jPanel;
    }

    public JTextField getSecretKeyTextField() {
        return secretKeyTextField;
    }

    public JTextField getKeyTextField() {
        return keyTextField;
    }



    public void setCreateButtonActionListener(ActionListener al) {
        createButton.addActionListener(al);
    }

    public void viewController() {}

}

