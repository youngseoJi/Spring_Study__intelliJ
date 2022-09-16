package hello.core.singleton;

public class StatefulService {

    private int price; // 상태 유지하는 핑드

    public void order(String name, int price){
        System.out.println("name = " + name + "price" + price);
        this.price = price; // 이 부분이 문제
    }

    public int getPrice() {
        return price;
    }

}
