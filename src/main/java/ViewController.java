import net.jacobpeterson.alpaca.AlpacaModel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ViewController extends View {

    AuthenticateTab authenticateTab = new AuthenticateTab();
    MarketTab marketTab = new MarketTab();
    PortfolioTab portfolioTab = new PortfolioTab();


    public ViewController() {

        jFrame = new JFrame();
        jTabs = new JTabbedPane();

        listModel = new DefaultListModel<String>();

        jTabs.add("AUTHENTICATE", authenticateTab.makeTab());
        jTabs.add("MARKET", marketTab.makeTab());
        jTabs.add("PORTFOLIO", portfolioTab.makeTab());

        jTabs.setEnabledAt(0, true);
        jTabs.setEnabledAt(1, false);
        jTabs.setEnabledAt(2, false);

        jFrame.add(jTabs);
        jFrame.getContentPane().setBackground(Color.BLACK);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(600,700);
        jFrame.setVisible(true);

    }

    public void setChangeTabChangeLister(ChangeListener cl) {
            jTabs.addChangeListener(cl);
    }

    public JPanel makeTab() {return jPanel;}

}
