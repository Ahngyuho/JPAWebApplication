package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){ //Model은 model에 매개변수로 받아서 view로 넘길 수 있음
        model.addAttribute("data","fdsfa");
        return "hello";
    }
}
