import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    private final List<Item> route = new ArrayList<>();
    private Double totalDistanceTraveled = 0D;

    public Itinerary(Item startPlace) {
        route.add(startPlace);
    }

    public void addToRoute(Item currentVisit) {
        Item previousItem = route.get(route.size() - 1);
        totalDistanceTraveled += Util.calculateDistance(previousItem, currentVisit);
        route.add(currentVisit);
    }

    public List<Item> getRoute() {
        return route;
    }

    public Double getTotalDistanceTraveled() {
        return totalDistanceTraveled;
    }
}
