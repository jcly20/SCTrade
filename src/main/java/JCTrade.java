
import net.jacobpeterson.alpaca.AlpacaModel;
import net.jacobpeterson.alpaca.openapi.trader.ApiException;
import net.jacobpeterson.alpaca.openapi.trader.model.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.PackedColorModel;
import java.util.ArrayList;
import java.util.UUID;
import java.lang.Thread;



public class JCTrade {

    ViewController viewController;
    AlpacaModel alpacaModel;

    class ChangeTabChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {

            System.out.println("change tab");

            ArrayList<String> positions = new ArrayList<>();
            String cash = null;

            try {
                positions = alpacaModel.getPositions();
                cash = alpacaModel.getAccountCash();
            } catch (ApiException a) {
                System.out.println("Cannot Access Page: Not Logged In");
                a.printStackTrace();
            }

            System.out.println("positions: " + positions);

            viewController.portfolioTab.listModel.clear();
            for (String p : positions) {
                viewController.portfolioTab.listModel.addElement(p);
            }

            String header = viewController.portfolioTab.welcomeMessage.substring(0, viewController.portfolioTab.welcomeMessage.lastIndexOf(" "));
            viewController.portfolioTab.createTabHeader.setText(header + " " + cash);

        }
    }


    class SellButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("sell button");

            String data = viewController.portfolioTab.jList.getSelectedValue();
            String qty = viewController.portfolioTab.getQuantityTextField().getText();

            String[] stockData = data.split(" ", 2);
            String ticker = stockData[0];

            System.out.println("selling: " + ticker);

            try {
                String confirmedTicker = alpacaModel.sellShare(ticker, qty);
                //viewController.portfolioTab.printSuccessfulSell(confirmedTicker, qty);
                System.out.println(confirmedTicker + " sold!");
            } catch(ApiException a){
                viewController.portfolioTab.printSellError();
                System.out.println("ERROR: stock not sold!");
            }

            ArrayList<String> positions = new ArrayList<>();
            String cash = new String();

            try {
                Thread.sleep(500);
                positions = alpacaModel.getPositions();
                cash = alpacaModel.getAccountCash();
            } catch (ApiException a) {
                a.printStackTrace();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            System.out.println("positions(after sell): " + positions);

            viewController.portfolioTab.listModel.clear();
            for (String p : positions) {
                viewController.portfolioTab.listModel.addElement(p);
            }

            String header = viewController.portfolioTab.welcomeMessage.substring(0, viewController.portfolioTab.welcomeMessage.lastIndexOf(" "));
            viewController.portfolioTab.createTabHeader.setText(header + " " + cash);

        }
    }

    class LogoutButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            alpacaModel.logout();
            viewController.authenticateTab.logout();
        }
    }


    class LoginButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String keyID = viewController.authenticateTab.getKeyTextField().getText();
            String secretKey = viewController.authenticateTab.getSecretKeyTextField().getText();
            try{
                alpacaModel.createConnection(keyID, secretKey);
                UUID uuid = alpacaModel.getUUID();
                viewController.authenticateTab.printSuccessfulLogin(uuid);
                viewController.jTabs.setEnabledAt(1, true);
                viewController.jTabs.setEnabledAt(2, true);
            } catch(ApiException e){
                viewController.authenticateTab.printInvalidKeysError();
                System.out.println("ERROR: Invalid keys!");
            }
        }
    }


    class SearchButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("search for stock");

            String ticker = viewController.marketTab.getTickerTextField().getText();
            ticker = ticker.toUpperCase();
            try {
                double price = alpacaModel.searchShare(ticker);
                viewController.marketTab.printStockData(ticker, price);

                ArrayList<ArrayList<Double>> priceData = new ArrayList<>();
                priceData = alpacaModel.getChart(ticker);

                viewController.marketTab.displayChart(ticker, priceData);

                System.out.println("stock searched!");
            } catch(net.jacobpeterson.alpaca.openapi.marketdata.ApiException e){
                viewController.marketTab.printPurchaseError();
                System.out.println("ERROR: stock not search!");
            }

        }
    }

    class BuyButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("buying stock");

            String qty = viewController.marketTab.getQuantityTextField().getText();
            String ticker = viewController.marketTab.getTickerTextField().getText();
            ticker = ticker.toUpperCase();

            try {
                String confirmedTicker = alpacaModel.buyShare(ticker, qty);
                viewController.marketTab.printSuccessfulPurchase(confirmedTicker, qty);
                System.out.println("stock purchased!");
            } catch(ApiException e){
                viewController.marketTab.printPurchaseError();
                e.printStackTrace();
                System.out.println("ERROR: stock not purchased!");
            }

        }
    }


    public void controller() {

        viewController = new ViewController();
        alpacaModel = new AlpacaModel();

        viewController.setChangeTabChangeLister(new ChangeTabChangeListener());
        viewController.portfolioTab.sellButton.addActionListener(new SellButtonActionListener());
        viewController.authenticateTab.setLoginButtonActionListener(new LoginButtonActionListener());
        viewController.authenticateTab.setLogoutButtonActionListener(new LogoutButtonActionListener());
        viewController.marketTab.setSearchButtonActionListener(new SearchButtonActionListener());
        viewController.marketTab.setBuyButtonActionListener(new BuyButtonActionListener());

    }

}

