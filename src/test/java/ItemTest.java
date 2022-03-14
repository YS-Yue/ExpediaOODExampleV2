import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {
    private Item hotelItem;
    private Item activityItem;


    @BeforeEach
    void setUp() {
        hotelItem = new Item.Builder("Hotel", "Hilton", new Location(-122.1830, 47.6670)).setRating(4.2).build();
        activityItem = new Item.Builder("Activity", "A1", new Location(-122.20, 47.80)).setRating(4.5).setPrice(30).build();
    }

    @Test
    void getType() {
        Assertions.assertEquals("hotel", hotelItem.getType());
        Assertions.assertEquals("activity", activityItem.getType());
    }

    @Test
    void getName() {
        Assertions.assertEquals("Hilton", hotelItem.getName());
        Assertions.assertEquals("A1", activityItem.getName());
    }

    @Test
    void getLocation() {
        Assertions.assertEquals(new Location(-122.1830, 47.6670), hotelItem.getLocation());
        Assertions.assertEquals(new Location(-122.20, 47.80), activityItem.getLocation());
    }

    @Test
    void getRating() {
        Assertions.assertTrue(hotelItem.getRating().isPresent());
        Double rating = hotelItem.getRating().get();
        Assertions.assertEquals(4.2, rating);

        Assertions.assertTrue(activityItem.getRating().isPresent());
        Double rating2 = activityItem.getRating().get();
        Assertions.assertEquals(4.5, rating2);
    }

    @Test
    void getPrice() {
        Assertions.assertFalse(hotelItem.getPrice().isPresent());

        Assertions.assertTrue(activityItem.getPrice().isPresent());
        Double price = activityItem.getPrice().get();
        Assertions.assertEquals(30, price);
    }
}