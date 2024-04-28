import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class PortfolioTab extends View {

    JTextArea createTabHeader;
    JTextArea confirmationFooter;
    JPanel portfolioJPanel;
    JButton sellButton;
    private JTextField quantityTextField;
    String welcomeMessage = ("Welcome to your SCTrade Portfolio\n" +
            "Symbol - Quantity - Price - P/L -- Cash: NoAccount");

    public JPanel makeTab() {

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        portfolioJPanel = new JPanel();
        portfolioJPanel.setLayout(new GridLayout(6, 2));

        createTabHeader = new JTextArea(welcomeMessage);
        createTabHeader.setPreferredSize(new Dimension(50, 75));
        createTabHeader.setFont(new Font("Arial", Font.PLAIN, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        jPanel.add(createTabHeader, BorderLayout.NORTH);

        listModel = new DefaultListModel<String>();
        jList = new JList<String>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        portfolioJPanel.add(jList, BorderLayout.CENTER);

        quantityTextField = new JTextField(40);
        portfolioJPanel.add(new JLabel("quantity:"));
        portfolioJPanel.add(quantityTextField);

        sellButton = new JButton("Sell Share");
        portfolioJPanel.add(sellButton, BorderLayout.SOUTH);

        jPanel.add(portfolioJPanel, BorderLayout.CENTER);

        return jPanel;

    }

    public void setListSelectionListener(ListSelectionListener sl) { updateList.addListSelectionListener(sl); }

    public void setSellButtonActionListener(ActionListener al) { sellButton.addActionListener(al); }

    public JTextField getQuantityTextField() {
        return quantityTextField;
    }

    public void printSellError() {
        createTabHeader.setText(welcomeMessage + "\nERROR: Stock not Sold.");
    }

}
