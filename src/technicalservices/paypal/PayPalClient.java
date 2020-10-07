package technicalservices.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class PayPalClient {

    private PayPalEnvironment environment;
    PayPalHttpClient client;


    public PayPalClient() {

        var clientData = Files.readAllLines(PayPalClient.class.getResource("paypal-client-data.txt").toURI());
        var clientId = clientData.get(0);
        var clientSecret = clientData.get(1);

        environment = new PayPalEnvironment.Sandbox(clientId, clientSecret);
        client = new PayPalHttpClient(environment);
        
    }

    public PayPalHttpClient client() {
        return this.client;
    }
}