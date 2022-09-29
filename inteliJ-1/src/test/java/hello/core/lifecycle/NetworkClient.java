package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
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

    @Override //InitializingBean
    // 의존관계주입이 끝나면 호출
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @Override // DisposableBean
    // 빈이 종료되면 호풀
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");  // NetworkClient.destroy
        disconnect(); // close: http://hello-spring.dev
    }
}
