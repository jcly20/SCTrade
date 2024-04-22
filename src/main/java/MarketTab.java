import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.UUID;

public class MarketTab extends View {

    private JTextField tickerTextField;
    private JTextArea createTabHeader;
    private JButton searchButton;
    private JButton buyButton;
    String welcomeMessage = ("Welcome to SCTrade Market Watch");

    public JPanel makeTab() {

        jPanel = new JPanel();
        JPanel marketJPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        marketJPanel.setLayout(new GridLayout(3, 2));
        
        createTabHeader = new JTextArea(welcomeMessage);

        createTabHeader.setPreferredSize(new Dimension(50, 50));
        createTabHeader.setFont(new Font("Arial", 20, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        jPanel.add(createTabHeader, BorderLayout.NORTH);

        tickerTextField = new JTextField(40);
        marketJPanel.add(new JLabel("symbol:"));
        marketJPanel.add(tickerTextField);

        searchButton = new JButton("Search");
        marketJPanel.add(searchButton);
        buyButton = new JButton("Purchase Share");
        marketJPanel.add(this.buyButton);

        jPanel.add(marketJPanel, BorderLayout.CENTER);

        return jPanel;

    }

    public JTextField getTickerTextField() {
        return tickerTextField;
    }

    public void setSearchButtonActionListener(ActionListener al) {
        searchButton.addActionListener(al);
    }

    public void setBuyButtonActionListener(ActionListener al){
        buyButton.addActionListener(al);
    }

    public void printPurchaseError() {
        createTabHeader.setText(welcomeMessage + "\nERROR: Stock not purchased.");
    }

    public void printSuccessfulPurchase(String confirmedTicker) {
        createTabHeader.setText(welcomeMessage + "\nPurchased 1 share of " + confirmedTicker);
    }

    public void printStockData(double price) {
        createTabHeader.setText(welcomeMessage + "\nPrice of stock:  " + price);
    }

    public void viewController() {}


}
