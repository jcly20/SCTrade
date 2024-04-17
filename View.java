
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

public abstract class View {

//    CreateTab createTab = new CreateTab();
//    ReadTab readTab = new ReadTab();
//    UpdateTab updateTab = new UpdateTab();
//    DeleteTab deleteTab = new DeleteTab();

    protected DefaultListModel<String> listModel = new DefaultListModel<String>();
    protected JFrame jFrame;
    protected JPanel jPanel;
    protected JTabbedPane jTabs;
    protected JList<String> jList;
    protected JList<String> updateList;
    protected JButton updateButton;
    protected final int[] id= new int[1];
    protected JButton deleteButton;
    protected JButton createButton;

//    public void view() {
//
//        listModel = new DefaultListModel<String>();
//        jFrame = new JFrame();
//        jTabs = new JTabbedPane();
//        jTabs.add("CREATE", createTab.makeTab());
//        jTabs.add("READ", readTab.makeTab());
//        jTabs.add("UPDATE", updateTab.makeTab());
//        jTabs.add("DELETE", deleteTab.makeTab());
//
//        jFrame.add(jTabs);
//        jFrame.setSize(500,500);
//        jFrame.setVisible(true);
//
//    }
//
//    public void setChangeTabChangeLister(ChangeListener cl) {
//        jTabs.addChangeListener(cl);
//    }

    public abstract JPanel makeTab() ;

}


