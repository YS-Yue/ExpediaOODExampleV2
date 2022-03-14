import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Util {
    public static List<Item> filterByType(List<Item> input, String chosenType) {
        return input.stream().filter(item -> Objects.equals(item.getType(), chosenType)).collect(Collectors.toList());
    }

    public static List<Item> filterByRating(List<Item> input, Double minRating, Double maxRating) {
        return input.stream().filter(item -> item.getRating().isPresent() && item.getRating().get() >= minRating && item.getRating().get() <= maxRating).collect(Collectors.toList());
    }

    public static List<Item> filterByPrice(List<Item> input, Double minPrice, Double maxPrice) {
        return input.stream().filter(item -> item.getPrice().isPresent() && item.getPrice().get() >= minPrice && item.getPrice().get() <= maxPrice).collect(Collectors.toList());
    }

    public static List<Item> filterByLocation(List<Item> input, Item dest, Double maxDistance) {
        return input.stream().filter(item -> Util.calculateDistance(item, dest) <= maxDistance).collect(Collectors.toList());
    }

    public static double calculateDistance(Item src, Item dest) {
        double lat1 = src.getLocation().getLatitude();
        double lat2 = dest.getLocation().getLatitude();
        double lon1 = src.getLocation().getLongitude();
        double lon2 = dest.getLocation().getLongitude();

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return (c * r);
    }

}
