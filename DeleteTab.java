import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

public class DeleteTab extends View {

    public JPanel makeTab(){

        jPanel = new JPanel();
        listModel = new DefaultListModel<String>();
        jList = new JList<String>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPanel.add(jList);

        deleteButton = new JButton("DELETE");
        jPanel.add(deleteButton);
        return jPanel;

    }


    public void setListSelectionListener(ListSelectionListener sl) { updateList.addListSelectionListener(sl); }

    public void setDeleteButtonActionListener(ActionListener al) { deleteButton.addActionListener(al); }

    public void viewController() {}

}

