package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 상품 저장소 테스트
public class ItemRepositoryTest {
    // 상품저장소
    ItemRepository itemRepository = new ItemRepository();

    // 각 테스트가 종료할떄마다 실행
    @AfterEach
    void afterEach() {
        // 테스트 할때 마다 저장소 데이터 삭제
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("item1", 1000, 10); // 상품생성 (이름, 가격, 수량)

        //when 저장소에 item 저장
        Item saveditem = itemRepository.save(item);

        //then : test - 저장소에 조회한 item id == 저장헀던 item id 가 같으면 성공
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveditem);
    }


    @Test
    void findAll() {
        //given :  list 여서 item 2개 이상 생성
        Item item1 = new Item("item1", 1000, 10);
        Item item2 = new Item("item2", 2000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when : 저장소의 모든 아이템 담아주기
        List<Item> result = itemRepository.findAll();

        //then : test1 - 저장소의 .size() 개수가 2개면 성공
        assertThat(result.size()).isEqualTo(2);
        // test2 - 저장소에 item1, item2 가 있으면 성공
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item1 = new Item("item1", 1000, 10);
        // 저장소에 저장한 item
        Item savedItem = itemRepository.save(item1);
        // 저장한 item id
        Long itemId = savedItem.getId();

        //when : 상품 정보 업데이트 할 때 
        Item updateParam = new Item("item2", 20000, 30);
        // 저장소에 저장한 item id를 조회해서 해당 item 의 값을 update해줌
        itemRepository.update(itemId, updateParam);

        //then : test - 저장소에 저장된 item의 정보 == 업데이트한 item 정보 같으면 성공 
        
        Item findItem = itemRepository.findById(itemId);
        
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}
