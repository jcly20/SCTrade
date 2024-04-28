import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import org.knowm.xchart.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class MarketTab extends View {

    private JTextField tickerTextField;
    private JTextField quantityTextField;
    private JTextArea createTabHeader;
    private JButton searchButton;
    private JButton buyButton;
    JPanel marketJPanel;
    String welcomeMessage = ("Welcome to SCTrade Market Watch");


    public JPanel makeTab() {

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        marketJPanel = new JPanel();
        marketJPanel.setLayout(new GridLayout(6, 2));

        createTabHeader = new JTextArea(welcomeMessage);
        createTabHeader.setPreferredSize(new Dimension(50, 50));
        createTabHeader.setFont(new Font("Arial", Font.PLAIN, 18));
        createTabHeader.setAlignmentX(SwingConstants.CENTER);
        jPanel.add(createTabHeader, BorderLayout.NORTH);

        tickerTextField = new JTextField(40);
        marketJPanel.add(new JLabel("symbol:"));
        marketJPanel.add(tickerTextField);

        quantityTextField = new JTextField(40);
        marketJPanel.add(new JLabel("quantity:"));
        marketJPanel.add(quantityTextField);

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

    public JTextField getQuantityTextField() {
        return quantityTextField;
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

    public void printSuccessfulPurchase(String confirmedTicker, String qty) {
        createTabHeader.setText(welcomeMessage + "\nPurchased " + qty + " share/s of " + confirmedTicker);
    }

    public void printStockData(String ticker, double price) {
        createTabHeader.setText(welcomeMessage + "\n" + ticker + " current price:  " + price);
    }

    public void displayChart(String ticker, ArrayList<ArrayList<Double>> priceData) {

        ArrayList<Double> openPrice = priceData.get(0);
        ArrayList<Double> highPrice = priceData.get(1);
        ArrayList<Double> lowPrice = priceData.get(2);
        ArrayList<Double> closePrice = priceData.get(3);
        ArrayList<Double> dates = priceData.get(4);

        ArrayList<Double> openPriceMO = new ArrayList<>();
        ArrayList<Double> highPriceMO = new ArrayList<>();
        ArrayList<Double> lowPriceMO = new ArrayList<>();
        ArrayList<Double> closePriceMO = new ArrayList<>();
        ArrayList<Double> datesMO = new ArrayList<>();

        double[] closeArr = new double[closePrice.size()];
        double[] barIndex = new double[closePrice.size()];
        for(int i=0; i<closePrice.size(); i++) {
            barIndex[i] = i;
            closeArr[i] = closePrice.get(i);
        }

        for (int i=0; i<30; i++) {
            openPriceMO.add(openPrice.get(i));
            highPriceMO.add(highPrice.get(i));
            lowPriceMO.add(lowPrice.get(i));
            closePriceMO.add(closePrice.get(i));
            datesMO.add(dates.get(i)); //new Date(String.valueOf(
        }

        XYChart chart = QuickChart.getChart(ticker, null, null, null, barIndex, closeArr);
        chart.getStyler().setCursorEnabled(true);
        chart.getStyler().setZoomEnabled(true);

        OHLCChart candleChart = new OHLCChartBuilder().width(800).height(600).title(ticker).build();
        candleChart.addSeries(ticker, datesMO, openPriceMO, highPriceMO, lowPriceMO, closePriceMO);

        JPanel lineChartPanel = new XChartPanel<XYChart>(chart);
        JPanel candleChartPanel = new XChartPanel<OHLCChart>(candleChart);
        JLabel label = new JLabel("3yr Line Above -- 1mo Candle Below", SwingConstants.CENTER);

        JFrame chartFrame = new JFrame(ticker + " Chart");
        chartFrame.setLayout(new BorderLayout());
        chartFrame.add(lineChartPanel, BorderLayout.NORTH);
        chartFrame.add(candleChartPanel, BorderLayout.CENTER);
        chartFrame.add(label, BorderLayout.SOUTH);
        chartFrame.setSize(1000,600);
        chartFrame.pack();
        chartFrame.setVisible(true);

    }

    public void viewController() {}

}
