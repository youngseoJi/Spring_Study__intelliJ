package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    // items 목록 조회
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    // item 상세 조회
    @GetMapping("/{itemId}")

    // 메소드 정의 변수명 itemId 그대로 -> @PathVariable 변수명 itemId
    public String item(@PathVariable Long itemId, Model model) {

        // PathVariable 로 넘어온 itemid로 item 조회
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);   // 조회한 item을 모델에 담기

        return "basic/item";  // 뷰 템플릿 호출
    }

    // item 등록 폼 조회
    @GetMapping("/add")
    public String addForm() {
        return "basic/addform";
    }

    // item 등록 - 폼조회와 동일한 url 이지만, 메소드로 구분

     // @PostMapping("/add")

     // 저장할 변수 3개의 값을 받기
    public String addItemV1(@RequestParam String itemName,
                          @RequestParam int price,
                          @RequestParam Integer quantity,
                           Model model) {
        //item 생성
        Item item = new Item();

        // 전달받은 변수 값으로 item 값 셋팅
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        // item 저장소에 셋팅한 item 저장
        itemRepository.save(item);
        // 저장된 결과를 model에 추가
        model.addAttribute("item", item);
        
        return "basic/item"; // item 상세정보 화면으로 view
     }


     /** ModelAttribute
      * 1. 요청 파라미터 처리
      * 지정한 객체 생성 및 프로퍼티 접근법으로 set() 파라미터 값을 입력해준다.
      * 2. model 추가
      * model에 지정한 객체를 자동 추가
      * @ModelAttribute("이름") 지정한 이름으로 model에 담아준다.
     */
//     @PostMapping("/add")
     public String addItemV2(@ModelAttribute("item") Item item) {

         itemRepository.save(item); 
        // model.addAttribute("item", item); - 자동추가, 생략 가능
         return "basic/item";
     }


     /**
      * @ModelAttribute("이름)" 생략시 모델에 저장될 때 클래스명을 사용
      * 클래스의 첫글자만 소문자로 변경해서 등록한다.
      * ex) Item item */
//     @PostMapping("/add")
     public String addItemV3(@ModelAttribute Item item, Model model) {

         itemRepository.save(item);
         // model.addAttribute("item", item); - 자동추가, 생략 가능
         return "basic/item";
     }

     // @ModelAttribute 생략 : 대상 객체는 모델에 자동 등록됨.
     @PostMapping("/add")
     public String addItemV4( Item item, Model model) {

         itemRepository.save(item);
         return "basic/item";
     }


     // 상품수정 폼 조회

     @GetMapping("/{itemId}/edit")
     // 수정할 item의 id와 model 받기
    public String editForm(@PathVariable Long itemId, Model model) {
            // 저장소에서 수정할 itemID 조회
         Item item = itemRepository.findById(itemId);
         model.addAttribute("item", item); // item이름으로 수정할 item model에 담기
         return "basic/editForm"; // 수정폼
     }

     // 상품 수정 차리 기능 - 리다이렉트
     @PostMapping("/{itemId}/edit")
     public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
         // 저장소 수정 함수 사용 : 저장할 itemid와 item를 수정완료
         itemRepository.update(itemId, item);
         return "redirect:/basic/items/{itemId}"; // 수정완료 후 상세화면 돌아가도록 리다이렉트 설정
     }

     // 테스트용 데이터 추가
    @PostConstruct
    public void init() {
      itemRepository.save(new Item("testA", 10000, 10));
      itemRepository.save(new Item("testB", 20000, 20));
    }
}
