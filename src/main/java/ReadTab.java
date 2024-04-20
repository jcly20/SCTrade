import javax.swing.*;

public class ReadTab extends View {

    public JPanel makeTab(){
        jPanel = new JPanel();
        listModel = new DefaultListModel<String>();
        jList = new JList<String>(listModel);
        jPanel.add(jList);
        return jPanel;
    }

    public void viewController() {}

}
