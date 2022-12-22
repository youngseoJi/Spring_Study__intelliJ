package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {
// InitializingBean 초기화 빈
    private String url;

    public  NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }
    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }
    
    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }


    /** @PostConstruct, @PreDestroy 애노테이션 지원
     * 사용해야할 생명주기 콜백 지원방법
     */

    @PostConstruct //생성이 된 이후에
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy // 소멸되기 전에
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect(); // close: http://hello-spring.dev
    }
}
