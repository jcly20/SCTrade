import javax.swing.*;
import javax.swing.event.ChangeListener;

public class ViewController extends View {

    DefaultListModel<String> listModel = new DefaultListModel<>();
    AuthenticateTab authenticateTab = new AuthenticateTab();
    MarketTab marketTab = new MarketTab();
    PortfolioTab portfolioTab = new PortfolioTab();


    public ViewController() {

        jFrame = new JFrame();
        jTabs = new JTabbedPane();

        jTabs.add("AUTHENTICATE", authenticateTab.makeTab());
        jTabs.add("MARKET", marketTab.makeTab());
        jTabs.add("PORTFOLIO", portfolioTab.makeTab());

        jFrame.add(jTabs);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);

    }

    public void setChangeTabChangeLister(ChangeListener cl) {
        jTabs.addChangeListener(cl);
    }

    public JPanel makeTab() {return jPanel;}

}
