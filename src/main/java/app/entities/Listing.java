package app.entities;

public class Listing {

    private int id;
    private String headline;
    private String description;
    private int price;

    public Listing(int id, String headline, String description, int price) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.price = price;
    }

    public Listing (String headline){
        this.headline = headline;
    }

    public int getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

}
