package hello.servlet.basic.Request;

//JSON 형식 전송

import lombok.Getter;
import lombok.Setter;

// JSON 형식 파싱 - JSON 형식으로 파싱할 수 있게 객체 생성
@Getter @Setter
public class HelloData {
    private String username;
    private int age;
    //==== lombok 사용시 자동 생성 코드 ====//

    /* public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    } */
}
