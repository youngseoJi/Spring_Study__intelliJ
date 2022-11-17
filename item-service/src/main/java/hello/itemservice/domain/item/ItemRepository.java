package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
// 상품 저장소
public class ItemRepository {

    // key Long타입 (Item id 타입을 Long으로해서 )
    // static 사용 - new 로 생성할 수 없도록
    private static final Map<Long, Item> store = new HashMap<>(); // HashMap 실무에선 X 현재는 싱글통이여서 사용하는 것
    // 수량 +1
    private static long sequence = 0L;// 기본값 0에 L Long 타입

    // save 상풍 저장 기능
    public Item save(Item item) {
        // 상풍 담을떄마다, id +1씩 증가
        item.setId(++sequence);
        // 저장소에 item 담기  -> 키 해당 item id , 값 item
        store.put(item.getId(), item);
        return item;
    }

    // 상품 조회 기능
    public Item findById(Long id) {
        // item의 id 로 조회
        return store.get(id);
    }
    // 상품 목록 조회 기능
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 상품 수정 (이름, 가격, 수량)
    public void update(Long itemId, Item updateParam ) {
        // id로 item 찾기
        Item findItem = findById(itemId);

        // 수정된 updateParam item의 정보로 재설정
        findItem.setItemName(updateParam.getItemName()); // item 이름
        findItem.setPrice(updateParam.getPrice()); // item 가격
        findItem.setQuantity(updateParam.getQuantity()); // item 수량
    }

    // Test를 위한 함수
    // store 상품 저장소 데이터 전체 삭제
    public void clearStore() {
        store.clear();
    }

}
