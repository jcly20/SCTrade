import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.UUID;

public class PortfolioTab extends View {


    private JTextField keyTextField;
    private JTextField secretKeyTextField;
    private JTextArea createTabHeader;
    private JPanel enterKeysJPanel;
    private JButton loginButton;
    private JButton logoutButton;
    JPanel portfolioJPanel;
    private String welcomeMessage = ("Welcome to your SCTrade Portfolio");


    public JPanel makeTab() {

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        portfolioJPanel = new JPanel();
        portfolioJPanel.setBackground(Color.BLACK);
        portfolioJPanel.setLayout(new GridLayout(3, 2));

        keyTextField = new JTextField(40);
        createTabHeader = new JTextArea(welcomeMessage);
        createTabHeader.setPreferredSize(new Dimension(50, 50));
        createTabHeader.setFont(new Font("Arial", 20, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        secretKeyTextField = new JTextField(40);
        jPanel.add(createTabHeader, BorderLayout.NORTH);

        listModel = new DefaultListModel<String>();
        jList = new JList<String>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPanel.add(jList, BorderLayout.CENTER);

        //deleteButton = new JButton("DELETE");
        //jPanel.add(deleteButton, BorderLayout.SOUTH);

        return jPanel;

    }


    //public void setListSelectionListener(ListSelectionListener sl) { updateList.addListSelectionListener(sl); }

    //public void setDeleteButtonActionListener(ActionListener al) { deleteButton.addActionListener(al); }

    public void viewController() {}

}
