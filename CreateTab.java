import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateTab extends View  {

    JTextField addTextFieldName;
    JTextField addTextFieldAge;
    public JPanel makeTab(){

        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,2));
        addTextFieldName = new JTextField(20);
        addTextFieldAge = new JTextField(10);
        jPanel.add(new JLabel("NAME:"));
        jPanel.add(addTextFieldName);
        jPanel.add(new JLabel("AGE:"));
        jPanel.add(addTextFieldAge);

        createButton = new JButton("CREATE");
        jPanel.add(createButton);
        return jPanel;

    }


    public void setCreateButtonActionListener(ActionListener al) {
        createButton.addActionListener(al);
    }

    public void viewController() {}

}

