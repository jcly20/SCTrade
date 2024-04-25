
import net.jacobpeterson.alpaca.AlpacaModel;
import net.jacobpeterson.alpaca.openapi.trader.ApiException;
import net.jacobpeterson.alpaca.openapi.trader.model.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;



public class JCTrade {

    ViewController viewController;
    AlpacaModel alpacaModel;

    class ChangeTabChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {

            System.out.println("change tab");

//            ArrayList<String> symbols = new ArrayList<>();
//            ArrayList<String> quantity = new ArrayList<>();
//            ArrayList<String> price = new ArrayList<>();
//            ArrayList<String> profit = new ArrayList<>();

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


//    class UpdateListSelectionListener implements ListSelectionListener {
//        @Override
//        public void valueChanged(ListSelectionEvent e) {
//
//            System.out.println("update selection");
//
//            if (!e.getValueIsAdjusting()) {
//                int index = viewController.updateTab.updateList.getSelectedIndex();
//                if (index != -1) {
//                    String s = viewController.listModel.elementAt(index);
//                    String[] words = s.trim().split("\\s+");
//                    viewController.id[0] = Integer.parseInt(words[0]);
//                    String name = words[1];
//                    int age = Integer.parseInt(words[2]);
//                    viewController.updateTab.updateTextFieldName.setText(name);
//                    viewController.updateTab.updateTextFieldAge.setText(String.valueOf(age));
//                    viewController.updateButton.setEnabled(true);
//                }
//            }
//        }
//    }


//    class UpdateButtonActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            System.out.println("update button");
//
//            if (viewController.id[0] != -1) {
//                String name = viewController.updateTab.updateTextFieldName.getText();
//                int age = Integer.parseInt(viewController.updateTab.updateTextFieldAge.getText());
//                model.updateRecord(viewController.id[0], name, age);
//            }
//        }
//    }


    class SellButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("sell button");

            //int i = viewController.portfolioTab.jList.getSelectedIndex();
            String ticker = viewController.portfolioTab.jList.getSelectedValue();

            //parse first few characters of position to get symbol
            //search for first whitespace

            System.out.println("selling: " + ticker);

            try {
                String confirmedTicker = alpacaModel.sellShare(ticker);
                viewController.portfolioTab.printSuccessfulSell(confirmedTicker);
                System.out.println("stock sold!");
            } catch(ApiException a){
                viewController.portfolioTab.printSellError();
                System.out.println("ERROR: stock not sold!");
            }

            //reload positions with updated data
            //alpacaModel.getPositions()
            //clear and set listModel


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
                viewController.marketTab.printStockData(price);
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

        //model = new Model();
        //model.model();
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

