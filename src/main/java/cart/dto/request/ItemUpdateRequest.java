package cart.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItemUpdateRequest {

    @NotNull(message = "상품의 아이디는 필수 입니다.")
    private long id;
    @NotBlank(message = "상품의 이름은 필수입니다.")
    private String name;
    @NotBlank(message = "상품의 이미지는 필수 입니다.")
    private String imageUrl;
    @Positive(message = "상품의 가격은 0 보다 커야합니다.")
    private int price;

    public ItemUpdateRequest(long id, String name, String imageUrl, int price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ItemUpdateRequest() {
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
