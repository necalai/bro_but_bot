import java.util.LinkedList;
import java.util.List;

public class Requests {
    private List<String> requests = new LinkedList<>();

    public void addRequest(String request) {
        requests.add(request);
    }

    public void deleteRequest(String request) {
        requests.remove(request);
    }
}
