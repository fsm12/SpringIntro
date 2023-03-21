package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 웹브라우저에서 요청이 오면 먼저 스프링 컨트롤러가 있는지 찾고 없으면 static파일을 찾음
    // 홈화면에서 맵핑된 것이 있으므로(아래코드) 해당 컨트롤러를 호출함
    @GetMapping("/")
    public String home(){
        return "home";
    }

}
