
import net.jacobpeterson.alpaca.AlpacaModel;
import net.jacobpeterson.alpaca.openapi.trader.ApiException;
import net.jacobpeterson.alpaca.openapi.trader.model.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

            try {
                positions = alpacaModel.getPositions();
            } catch (ApiException a) {
                a.printStackTrace();
            }

            System.out.println("positions: " + positions);

            viewController.portfolioTab.listModel.clear();
            for (String p : positions) {
                viewController.portfolioTab.listModel.addElement(p);
            }



        }
    }


    class SellButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("sell button");

            String data = viewController.portfolioTab.jList.getSelectedValue();

            String[] stockData = data.split(" ", 2);
            String ticker = stockData[0];

            System.out.println("selling: " + ticker);

            try {
                String confirmedTicker = alpacaModel.sellShare(ticker);
                viewController.portfolioTab.printSuccessfulSell(confirmedTicker);
                System.out.println("stock sold!");
            } catch(ApiException a){
                viewController.portfolioTab.printSellError();
                System.out.println("ERROR: stock not sold!");
            }

            ArrayList<String> positions = new ArrayList<>();

            try {
                Thread.sleep(500);
                positions = alpacaModel.getPositions();
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
            try {
                double price = alpacaModel.searchShare(ticker);
                viewController.marketTab.printStockData(ticker, price);
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

            String ticker = viewController.marketTab.getTickerTextField().getText();
            ticker = ticker.toUpperCase();
            try {
                String confirmedTicker = alpacaModel.buyShare(ticker);
                viewController.marketTab.printSuccessfulPurchase(confirmedTicker);
                System.out.println("stock purchased!");
            } catch(ApiException e){
                viewController.marketTab.printPurchaseError();
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

