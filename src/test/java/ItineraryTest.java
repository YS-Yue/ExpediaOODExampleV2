import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItineraryTest {

    @Test
    void getTotalDistanceTraveled() {
        // Travel start from Airport SEA, and end at Hotel H1
        Item airport1 = new Item.Builder("Airport", "SEA", new Location(-120.0000, 48.0000)).setRating(3.8).build();
        Item activity1 = new Item.Builder("Activity", "A1", new Location(-122.20, 47.80)).setRating(3.7).setPrice(30).build();
        Item activity2 = new Item.Builder("Activity", "A2", new Location(-122.00, 47.20)).setRating(3.5).setPrice(20).build();
        Item activity3 = new Item.Builder("Activity", "A3", new Location(-121.90, 47.00)).setRating(4.0).setPrice(15).build();
        Item hotel1 = new Item.Builder("Hotel", "H1", new Location(-122.1830, 47.6670)).setRating(4.2).build();

        Itinerary itinerary = new Itinerary(airport1);
        System.out.println(itinerary.getTotalDistanceTraveled()); // 0.0

        itinerary.addToRoute(activity1);
        System.out.println(itinerary.getTotalDistanceTraveled()); // 165.50072552817986

        itinerary.addToRoute(activity2);
        System.out.println(itinerary.getTotalDistanceTraveled()); // 233.8884179665875

        Double expectedTotal = Util.calculateDistance(airport1,activity1) + Util.calculateDistance(activity1,activity2);

        Assertions.assertEquals(expectedTotal, itinerary.getTotalDistanceTraveled());

        itinerary.addToRoute(activity3);
        System.out.println(itinerary.getTotalDistanceTraveled()); // 257.38024759803125

        itinerary.addToRoute(hotel1);
        System.out.println(itinerary.getTotalDistanceTraveled()); // 334.55252099152835

        Assertions.assertEquals(335, Math.round(itinerary.getTotalDistanceTraveled()));

    }
}