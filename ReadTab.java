import javax.swing.*;

public class ReadTab extends View {

    private JPanel makeReadTab(){
        jPanel = new JPanel();
        jList = new JList<String>(listModel);
        jPanel.add(jList);
        return jPanel;
    }

}
