package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String viewName; // view 논리이름
    private Map<String, Object> model = new HashMap<>(); // model 객체
    // model은 map이므로 컨트롤러에서 뷰에 필요한 데이터를 key, value로 넣어주면 된다.

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return viewName;
    }
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    public Map<String, Object> getModel() {
        return model;
    }
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}