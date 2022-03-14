import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    List<Item> items = new ArrayList<>();
    Item hotel1;
    Item hotel2;
    Item activity1;
    Item activity2;
    Item activity3;
    Item airport1;

    @BeforeEach
    void setUp(){
        hotel1 = new Item.Builder("Hotel", "H1", new Location(-122.1830, 47.6670)).setRating(4.2).build();
        hotel2 = new Item.Builder("Hotel", "H2", new Location(-120.0000, 47.2000)).setRating(4.7).build();
        airport1 = new Item.Builder("Airport", "SEA", new Location(-120.0000, 48.0000)).setRating(3.8).build();
        activity1 = new Item.Builder("Activity", "A1", new Location(-122.20, 47.80)).setRating(3.7).setPrice(30).build();
        activity2 = new Item.Builder("Activity", "A2", new Location(-122.00, 47.20)).setRating(3.5).setPrice(20).build();
        activity3 = new Item.Builder("Activity", "A3", new Location(-121.90, 47.00)).setRating(4.0).setPrice(15).build();

        items.addAll(Arrays.asList(hotel1,hotel2,activity1,activity2,activity3,airport1));
    }

    @Test
    void filterByType() {
        List<Item> filteredByType = Util.filterByType(items,"hotel");
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(hotel1,hotel2)), filteredByType);
    }

    @Test
    void filterByRating() {
        List<Item> filteredByRating = Util.filterByRating(items,4.0, 5.0);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(hotel1,hotel2,activity3)), filteredByRating);
    }

    @Test
    void filterByPrice() {
        List<Item> filteredByPrice = Util.filterByPrice(items, 0D, 22D);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(activity2,activity3)), filteredByPrice);
    }

    @Test
    void filterByLocation() {
        for (Item item : items) {
            System.out.println(item.getName() + " --> Hotel1 : " + Util.calculateDistance(hotel1, item));
        }
        // H1 --> Hotel1 : 0.0
        // H2 --> Hotel1 : 172.20774065566314
        // A1 --> Hotel1 : 14.843474381523896
        // A2 --> Hotel1 : 53.7213580634048
        // A3 --> Hotel1 : 77.17227339349708
        // SEA --> Hotel1 : 167.09510861283422

        Assertions.assertEquals(new ArrayList<>(Arrays.asList(hotel1,activity1, activity2, activity3, airport1)), Util.filterByLocation(items, hotel1, 170D));
    }

    @Test
    void testMultipleFilters() {
        // Find all activities within 60 km of Hotel1, and have a rating higher than 3.6 star, and the price is under $40
        List<Item> filtered = Util.filterByType(items, "activity");
        filtered = Util.filterByLocation(filtered, hotel1, 60D);
        filtered = Util.filterByRating(filtered, 3.6, 5.0);
        filtered = Util.filterByPrice(filtered, 0D, 40D);

        Assertions.assertEquals(new ArrayList<>(List.of(activity1)), filtered);

        // Find all activities within 100 km of Hotel1, and have a rating higher than 3.0 star, and the price is under $25
        List<Item> filtered2 = Util.filterByType(items, "activity");
        filtered2 = Util.filterByLocation(filtered2, hotel1, 100D);
        filtered2 = Util.filterByRating(filtered2, 3.0, 5.0);
        filtered2 = Util.filterByPrice(filtered2, 0D, 25D);

        Assertions.assertEquals(new ArrayList<>(List.of(activity2,activity3)), filtered2);
    }
}