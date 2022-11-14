package hello.springmvc.basic;

import lombok.Data;

//롬복 @Data : @Getter , @Setter , @ToString , @EqualsAndHashCode , @RequiredArgsConstructor 를 자동 적용함
@Data
public class HelloData {
    private String username;
    private int age;
}
