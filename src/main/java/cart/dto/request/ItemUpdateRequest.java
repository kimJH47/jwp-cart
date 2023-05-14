package cart.dto.request;

public class ItemUpdateRequest {

    private final long id;
    private final String name;
    private final String imageUrl;
    private final int price;

    public ItemUpdateRequest(long id, String name, String imageUrl, int price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }
}
