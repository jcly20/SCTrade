
package net.jacobpeterson.alpaca;

import net.jacobpeterson.alpaca.model.util.apitype.MarketDataWebsocketSourceType;
import net.jacobpeterson.alpaca.model.util.apitype.TraderAPIEndpointType;
import net.jacobpeterson.alpaca.openapi.trader.ApiException;

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

}
