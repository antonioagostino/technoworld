package technicalservices;

import java.io.IOException;
import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import org.json.JSONException;
import org.json.JSONObject;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersGetRequest;

import technicalservices.paypal.PayPalClient;

public class GetOrder extends PayPalClient {

    public String getOrder(String orderId) throws IOException {

        OrdersGetRequest request = new OrdersGetRequest(orderId);
        HttpResponse<Order> response = client().execute(request);
        try {
            JSONObject jsonObject = new JSONObject(new Json().serialize(response.result()));
            if(jsonObject.getString("status").equals("COMPLETED")){
                return jsonObject.getString("id");
            } else {
                return "-1";
            }
        } catch (JSONException e) {
            return "-1";
        }
    }
}