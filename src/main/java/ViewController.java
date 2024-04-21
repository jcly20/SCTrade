import javax.swing.*;
import javax.swing.event.ChangeListener;

public class ViewController extends View {

    DefaultListModel<String> listModel = new DefaultListModel<>();
    AuthenticateTab authenticateTab = new AuthenticateTab();
    ReadTab readTab = new ReadTab();
    UpdateTab updateTab = new UpdateTab();
    DeleteTab deleteTab = new DeleteTab();


    public ViewController() {

        jFrame = new JFrame();
        jTabs = new JTabbedPane();

        jTabs.add("AUTHENTICATE", authenticateTab.makeTab());
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

    public JPanel makeTab() {return jPanel;}

}
