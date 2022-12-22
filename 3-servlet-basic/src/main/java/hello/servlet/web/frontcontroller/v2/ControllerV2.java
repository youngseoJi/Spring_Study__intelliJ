package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // 반환을 MyView 타입으로 만들어서 반환한다.
    MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
