package academy.learnprogramming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @GetMapping("/hello")
    @ResponseBody //oznacza, że to co zwróci ta metoda ma być DOSŁOWNIE zwrócone do przeglądarki, czyli w tym przypadku będzi to słówko "hello", a nie widok (thymeleaf, jsp itp...) o nazwie "hello"
    public String hello() {
        return "hello";
    }
}
