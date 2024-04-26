import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.UUID;

public class MarketTab extends View {

    private JTextField tickerTextField;
    private JTextArea createTabHeader;
    private JTextArea dataFooter;
    private JButton searchButton;
    private JButton buyButton;
    JPanel marketJPanel;
    String welcomeMessage = ("Welcome to SCTrade Market Watch");


    public JPanel makeTab() {

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        marketJPanel = new JPanel();
        marketJPanel.setBackground(Color.BLACK);
        marketJPanel.setLayout(new GridLayout(3, 2));
        
        createTabHeader = new JTextArea(welcomeMessage);
        createTabHeader.setPreferredSize(new Dimension(50, 50));
        createTabHeader.setFont(new Font("Arial", Font.PLAIN, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        jPanel.add(createTabHeader, BorderLayout.NORTH);

        dataFooter = new JTextArea("");
        createTabHeader.setPreferredSize(new Dimension(50, 50));
        createTabHeader.setFont(new Font("Arial", Font.PLAIN, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        jPanel.add(createTabHeader, BorderLayout.SOUTH);

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

    public void printStockData(String ticker, double price) {
        dataFooter.setText(ticker + " current price:  " + price);
    }

    public void viewController() {}


}
