package technicalservices.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class PayPalClient {

    private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            "AUuQsZ6W_kSfRMWY_JWNer6Ho-eU3XDcVAgce3CZYj8LhYJO4ZiL9AME6LMbWHPxGkbTVp3zHpoQudsR",
            "EOdF5SBQTEe4pXbZ-4i1nkb9hBuPSQpXazAHGPztte6EAZJTg37ablGbaDg52pTCpe6q6BJhvqQm06UY");

    PayPalHttpClient client = new PayPalHttpClient(environment);

    public PayPalHttpClient client() {
        return this.client;
    }
}