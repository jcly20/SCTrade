
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

public abstract class View {

    protected DefaultListModel<String> listModel;
    protected JFrame jFrame;
    protected JPanel jPanel;
    protected JTabbedPane jTabs;
    protected JList<String> jList;
    protected JList<String> updateList;
    protected JButton updateButton;
    protected final int[] id= new int[1];
    protected JButton deleteButton;
    protected JButton createButton;

    public abstract JPanel makeTab() ;

}


