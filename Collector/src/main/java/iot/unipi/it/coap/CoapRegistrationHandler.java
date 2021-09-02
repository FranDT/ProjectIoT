package iot.unipi.it.coap;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.net.InetAddress;

public class CoapRegistrationHandler extends CoapResource {

    public CoapRegistrationHandler(String name){
        super(name);
    }

    public void handlePOST(CoapExchange exchange){
        exchange.accept();

        /*

            Resource discovery process, used to get the name of the resource associated to the corresponding address. In
            order to obtain the information needed, we need to issue a get request to the /.well-know/core resource: the
            information we need will be contained in the body of the resource as showed at the following link.
            https://www.researchgate.net/figure/An-example-of-Constrained-RESTful-Environments-CoRE-direct-resource-discovery-and_fig2_262819123
            We therefore need to get the value between angular brackets (since we have just one resource per sensor, we
            will have one occurrence of that term); we will also need to check if

         */
        InetAddress sensorAddress = exchange.getSourceAddress();
        CoapClient client = new CoapClient("coap://[" + sensorAddress.getHostAddress() + "]:5683/.well-known/core");
        CoapResponse response = client.get();

        String responseCode = response.getCode().toString();
        if(!responseCode.startsWith("2")){
            System.out.println("Error: " + responseCode);
            return;
        }



    }

}
