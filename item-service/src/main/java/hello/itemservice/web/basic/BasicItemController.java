package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;


 @Controller
 @RequestMapping("/basic/items")
 @RequiredArgsConstructor // final 이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다.
 public class BasicItemController {
     private final ItemRepository itemRepository;

 /* @RequiredArgsConstructor 로 인해 자동생성 생성자
 @Autowired  // 의존관계 주입 - BasicItemController가 스프링 빈에 등록되면서 ItemRepository도 스프링빈에 등록, 주입된다.
 public BasicItemController(ItemRepository itemRepository) {
 this.itemRepository = itemRepository;
 } */


// item 목록 조회
@GetMapping
public String items(Model model) {
    List<Item> items = itemRepository.findAll();
    model.addAttribute("items", items);
    return "basic/items";
}

// 테스트용 데이터 추가
@PostConstruct
public void init() {
    itemRepository.save(new Item("testA", 10000, 10));
    itemRepository.save(new Item("testB", 20000, 20));
}
}
