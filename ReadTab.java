import javax.swing.*;

public class ReadTab extends View {

    public JPanel makeTab(){
        jPanel = new JPanel();
        //check if null skip
        jList = new JList<String>(listModel);
        jPanel.add(jList);
        return jPanel;
    }

    public void viewController() {}

}
