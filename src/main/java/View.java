
import javax.swing.*;
import java.util.ArrayList;

public abstract class View {

    protected DefaultListModel<String> listModel;
    protected JFrame jFrame;
    protected JPanel jPanel;
    protected JTabbedPane jTabs;
    protected JList<String> jList;
    protected JList<String> updateList;
    protected final int[] id= new int[1];

    public abstract JPanel makeTab() ;

}


