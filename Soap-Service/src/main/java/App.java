import service.PremiumService;
import service.SubscriptionService;

import javax.xml.ws.Endpoint;

public class App {
    private static final String URL_subscription = "http://0.0.0.0:1233/subscription";
    private static final String URL_premium = "http://0.0.0.0:1233/premium";

    public static void main(String[] args) {
        try {
            System.out.println("Service is published at: http://localhost:1233/subscription");
            System.out.println("Service is published at: http://localhost:1233/");
            Endpoint.publish(URL_subscription, new SubscriptionService());
            Endpoint.publish(URL_premium, new PremiumService());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
