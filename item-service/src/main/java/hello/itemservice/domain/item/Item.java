package hello.itemservice.domain.item;

import lombok.Data;

@Data // 위험한 방법 - 분리해서 사용해라

// item  객체 생성
public class Item {
    private Long id; // 식별자 
    private String itemName; // 이름
    private Integer price; // 가격
    private Integer quantity; // 수량
    // int(기본형) 를 쓰면 =0 이라도 들어가야한다. null 이 들어갈 수 있도록 Integer(객체타입) 사용한것

    // 디폴트 기본 생성자
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
