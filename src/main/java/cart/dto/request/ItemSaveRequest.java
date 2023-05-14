package cart.dto.request;

public class ItemSaveRequest {
    private final String name;
    private final String imageUrl;
    private final int price;

    public ItemSaveRequest(String name, String imageUrl, int price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
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
