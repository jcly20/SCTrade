import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UpdateTab extends View {

    JTextField updateTextFieldName;
    JTextField updateTextFieldAge;
    public JPanel makeTab() {

        jPanel = new JPanel();
        listModel = new DefaultListModel<String>();
        updateList = new JList<String>(listModel);
        updateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jPanel.add(updateList);

        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(2,2));
        updateTextFieldName = new JTextField(10);
        updateTextFieldAge = new JTextField(10);
        inner.add(new JLabel("NAME:"));
        inner.add(updateTextFieldName);
        inner.add(new JLabel("AGE:"));
        inner.add(updateTextFieldAge);
        jPanel.add(inner);

        updateButton = new JButton("UPDATE");
        updateButton.setEnabled(false);
        jPanel.add(updateButton);
        return jPanel;

    }


    public void setUpdateButtonActionListener(ActionListener al) { updateButton.addActionListener(al); }

    public void viewController() {}

}
