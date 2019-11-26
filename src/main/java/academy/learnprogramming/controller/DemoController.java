package academy.learnprogramming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @GetMapping("/hello") //http://localhost:8080/todo-list/hello
    @ResponseBody //oznacza, że to co zwróci ta metoda ma być DOSŁOWNIE zwrócone do przeglądarki, czyli w tym przypadku będzi to słówko "hello", a nie widok (thymeleaf, jsp itp...) o nazwie "hello"
    public String hello() {
        return "hello";
    }

    @GetMapping("welcome") //http://localhost:8080/todo-list/welcome
    public String welcome() {
        return "welcome";
    }
}
