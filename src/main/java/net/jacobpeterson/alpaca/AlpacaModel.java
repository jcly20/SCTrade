
package net.jacobpeterson.alpaca;

import net.jacobpeterson.alpaca.model.util.apitype.MarketDataWebsocketSourceType;
import net.jacobpeterson.alpaca.model.util.apitype.TraderAPIEndpointType;
import net.jacobpeterson.alpaca.openapi.marketdata.model.StockFeed;
import net.jacobpeterson.alpaca.openapi.marketdata.model.StockTrade;
import net.jacobpeterson.alpaca.openapi.trader.ApiException;
import net.jacobpeterson.alpaca.openapi.trader.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlpacaModel {

    private AlpacaAPI alpacaAPI;

    public AlpacaModel(){
        alpacaAPI = null;
    }

    public void createConnection(String keyID, String secretKey) throws ApiException {
//        keyID = "PKBIBI0HHEZKJ9GQXPKI";
//        secretKey = "SqKPm3Q1SbZqjGKuN5FvGR1VrVxtBpAd8EtNGcu7";
        final TraderAPIEndpointType endpointType = TraderAPIEndpointType.PAPER; // or 'LIVE'
        final MarketDataWebsocketSourceType sourceType = MarketDataWebsocketSourceType.IEX; // or 'SIP'
        alpacaAPI = new AlpacaAPI(keyID, secretKey, endpointType, sourceType);
    }

    public UUID getUUID() throws ApiException{
        UUID uuid = alpacaAPI.trader().accounts().getAccount().getId();
            return uuid;
    }

    public void logout(){
        alpacaAPI = null;
    }

    public String buyShare(String ticker, String qty) throws ApiException {

        final Order openingOrder = alpacaAPI.trader().orders()
                .postOrder(new PostOrderRequest()
                        .symbol(ticker)
                        .qty(qty)
                        .side(OrderSide.BUY)
                        .type(OrderType.MARKET)
                        .timeInForce(TimeInForce.GTC));
        System.out.println("Opening order: " + openingOrder);

        return openingOrder.getSymbol();

    }

    public double searchShare(String ticker) throws net.jacobpeterson.alpaca.openapi.marketdata.ApiException {

        final StockTrade latestTrade = alpacaAPI.marketData().stock()
                .stockLatestTradeSingle(ticker, StockFeed.IEX, null).getTrade();
        System.out.printf("Latest trade: price=%s, size=%s\n",
                latestTrade.getP(), latestTrade.getS());

        return latestTrade.getP();

    }

    public String sellShare(String ticker, String qty) throws ApiException {

        final Order closingOrder = alpacaAPI.trader().orders()
                .postOrder(new PostOrderRequest()
                        .symbol(ticker)
                        .qty(qty)
                        .side(OrderSide.SELL)
                        .type(OrderType.MARKET)
                        .timeInForce(TimeInForce.GTC));
        System.out.println("closing order: " + closingOrder);

        return closingOrder.getSymbol();

    }

    public ArrayList<String> getPositions() throws ApiException {

        ArrayList<String> positionList = new ArrayList<>();

        final List<Position> positions = alpacaAPI.trader().positions().getAllOpenPositions();
        for (Position position : positions) {
            String positionData = "";
            positionData = position.getSymbol() + " \t         " + position.getQty() + " \t         " +
                    String.format("%.2f",Double.parseDouble(position.getCurrentPrice())) + " \t         " +
                    String.format("%.2f",Double.parseDouble(position.getUnrealizedPl()));

            positionList.add(positionData);
        }

        return positionList;

    }

    public String getAccountCash() throws ApiException {

        final Account account = alpacaAPI.trader().accounts().getAccount();

        return account.getCash();
    }

}
