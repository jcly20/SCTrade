
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

public class View {

    DefaultListModel<String> listModel;
    JFrame jFrame;
    JPanel jPanel;
    JTabbedPane jTabs;
    JList<String> jList;
    JList<String> updateList;
    JButton updateButton;
    final int[] id= new int[1];
    JButton deleteButton;
    JButton createButton;

    public void view() {

        listModel = new DefaultListModel<String>();
        jFrame = new JFrame();
        jTabs = new JTabbedPane();
        jTabs.add("CREATE", CreateTab.makeCreateTab()); // maybe make view child of all tabs
        jTabs.add("READ", ReadTab.makeReadTab());
        jTabs.add("UPDATE", UpdateTab.makeUpdateTab());
        jTabs.add("DELETE", DeleteTab.makeDeleteTab());s

        jFrame.add(jTabs);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);

    }

    public void setChangeTabChangeLister(ChangeListener cl) {

        jTabs.addChangeListener(cl);

    }

}


