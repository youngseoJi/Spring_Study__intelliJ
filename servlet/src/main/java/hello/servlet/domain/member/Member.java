package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;


// 회원 클래스 생성 - get set 함수 생성 (lombok 룸북 이용하여 자동생성)
@Getter @Setter
public class Member {

    private Long id;
    private String username;
    private int age;

    // 디폴트 , 기본 생성자
    public Member() { }

    // 생성자 - 이름, 나이를 갖는
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
