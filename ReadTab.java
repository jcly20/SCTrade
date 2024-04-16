import javax.swing.*;

public class ReadTab extends View {

    public JPanel makeTab(){
        jPanel = new JPanel();
        jList = new JList<String>(listModel);
        jPanel.add(jList);
        return jPanel;
    }

    public void viewController() {}

}
