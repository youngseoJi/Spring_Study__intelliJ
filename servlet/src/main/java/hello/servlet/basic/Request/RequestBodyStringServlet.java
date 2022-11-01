package hello.servlet.basic.Request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        // InputStream : HTTP 메시지 바디의 데이터 직접 읽을 수 있다.
        String massageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // > inputStream은 byte 코드를 반환한다. byte 코드를 우리가 읽을 수 있는 문자(String)로 보려면 문자표
        //(Charset)를 지정해주어야 한다. 여기서는 UTF_8 Charset을 지정
        System.out.println("massageBody = " + massageBody);

        response.getWriter().write("ok");
    }
}
