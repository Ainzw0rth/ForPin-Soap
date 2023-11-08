import jakarta.xml.ws.Endpoint;
import model.TestModel;
import service.User;

public class App {
    private static final String URL = "http://0.0.0.0:1233/user";

    public static void main(String[] args) {
        try {
            System.out.println("Service is published at: http://localhost:1233/user");
            TestModel test = new TestModel();
            int id = test.fetchId();
            System.out.println(id);
            Endpoint.publish(URL, new User());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
