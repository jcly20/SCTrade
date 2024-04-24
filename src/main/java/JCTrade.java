
import net.jacobpeterson.alpaca.AlpacaModel;
import net.jacobpeterson.alpaca.openapi.trader.ApiException;

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

            ArrayList<String> symbols = new ArrayList<>();
            ArrayList<String> quantity = new ArrayList<>();
            ArrayList<String> price = new ArrayList<>();
            ArrayList<String> profit = new ArrayList<>();

            try {
                symbols = alpacaModel.getPositions('s');
            } catch (ApiException a) {
                a.printStackTrace();
            }

            viewController.listModel.clear();
            for (String s : symbols) {
                viewController.listModel.addElement(s);
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


//    class DeleteButtonActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            System.out.println("delete button");
//
//            int i = viewController.jList.getSelectedIndex();
//            String s = viewController.jList.getSelectedValue();
//            String[] words = s.trim().split(" ");
//            int id = Integer.parseInt(words[0]);
//            viewController.listModel.remove(i);
//            model.deleteRecord(id);
//        }
//    }

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

//            String name = viewController.createTab.getKeyTextField().getText();
//            int age = -1;
//            try {
//                age = Integer.parseInt(viewController.createTab.getSecretKeyTextField().getText());
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//            if (!name.isEmpty() && !(age == -1)) {
//                model.createRecord(name, age);
//            }

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
//        viewController.updateTab.updateList.addListSelectionListener(new UpdateListSelectionListener());
//        viewController.updateTab.updateButton.addActionListener(new UpdateButtonActionListener());
//        viewController.deleteTab.deleteButton.addActionListener(new DeleteButtonActionListener());
        viewController.authenticateTab.setLoginButtonActionListener(new LoginButtonActionListener());
        viewController.authenticateTab.setLogoutButtonActionListener(new LogoutButtonActionListener());
        viewController.marketTab.setSearchButtonActionListener(new SearchButtonActionListener());
        viewController.marketTab.setBuyButtonActionListener(new BuyButtonActionListener());

    }

}

