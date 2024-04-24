import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.UUID;

public class PortfolioTab extends View {

    private JTextArea createTabHeader;
    JPanel portfolioJPanel;
    JButton sellButton;
    private final String welcomeMessage = ("Welcome to your SCTrade Portfolio");

    public JPanel makeTab() {

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        portfolioJPanel = new JPanel();
        portfolioJPanel.setBackground(Color.BLACK);
        portfolioJPanel.setLayout(new GridLayout(3, 2));

        createTabHeader = new JTextArea(welcomeMessage);
        createTabHeader.setPreferredSize(new Dimension(50, 50));
        createTabHeader.setFont(new Font("Arial", 20, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        jPanel.add(createTabHeader, BorderLayout.NORTH);

        listModel = new DefaultListModel<String>();
        jList = new JList<String>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPanel.add(jList, BorderLayout.CENTER);

        sellButton = new JButton("Sell Share");
        jPanel.add(sellButton, BorderLayout.SOUTH);

        return jPanel;

    }

    public void setListSelectionListener(ListSelectionListener sl) { updateList.addListSelectionListener(sl); }

    public void setSellButtonActionListener(ActionListener al) { sellButton.addActionListener(al); }

    public void printSellError() {
        createTabHeader.setText(welcomeMessage + "\nERROR: Stock not Sold.");
    }

    public void printSuccessfulSell(String confirmedTicker) {
        createTabHeader.setText(welcomeMessage + "\nSold 1 share of " + confirmedTicker);
    }

    public void viewController() {}

}
