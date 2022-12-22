package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;


public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form"; 
        // 논리이름만 반환하면 되도록


        // V3 버전 - ModelView 생성자를 직접 만들어야했음
        /* @Override
            public ModelView process(Map<String, String> paramMap) {
                return new ModelView("new-form");
                }
         */
    }
}

