import java.util.Optional;

public class Item {
    // Required fields
    private final String type;
    private final String name;
    private final Location location;

    // Optional fields
    private Double rating;
    private Double price;

    private Item(Builder builder) {
        this.type = builder.type;
        this.name = builder.name;
        this.location = builder.location;
        this.rating = builder.rating;
        this.price = builder.price;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Optional<Double> getRating() {
        return Optional.ofNullable(rating);
    }

    public Optional<Double> getPrice() {
        return Optional.ofNullable(price);
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static class Builder {

        // Required parameters
        private final String type;
        private final String name;
        private final Location location;

        // Optional parameters
        private Double rating;
        private Double price;

        public Builder(String type, String name, Location location) {
            this.type = type.toLowerCase();
            this.name = name;
            this.location = location;
        }

        public Builder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
