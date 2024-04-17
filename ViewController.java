import javax.swing.*;
import javax.swing.event.ChangeListener;

public class ViewController extends View {

    public JPanel makeTab() {return jPanel;}

    CreateTab createTab = new CreateTab();
    ReadTab readTab = new ReadTab();
    UpdateTab updateTab = new UpdateTab();
    DeleteTab deleteTab = new DeleteTab();

    public ViewController() {

        viewController();

    }

    public void viewController() {

        listModel = new DefaultListModel<String>();
        jList = new JList<String>(listModel);
        jFrame = new JFrame();
        jTabs = new JTabbedPane();
        jTabs.add("CREATE", createTab.makeTab());
        jTabs.add("READ", readTab.makeTab());
        jTabs.add("UPDATE", updateTab.makeTab());
        jTabs.add("DELETE", deleteTab.makeTab());

        jFrame.add(jTabs);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);

    }

    public void setChangeTabChangeLister(ChangeListener cl) {
        jTabs.addChangeListener(cl);
    }

}
