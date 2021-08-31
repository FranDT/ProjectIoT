package iot.unipi.it.coap;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.net.InetAddress;

public class CollectorCoapServer extends CoapServer {

    public void startServer(){
        this.add(new CoapRegistrationHandler("registration"));
        this.start();
    }

    public void handlePOST(CoapExchange exchange){
        exchange.accept();

        InetAddress sensorAddress = exchange.getSourceAddress();
        CoapClient client = new CoapClient("coap://[" + sensorAddress.getHostAddress() + "]:5683/.well-known/core");
    }
}
