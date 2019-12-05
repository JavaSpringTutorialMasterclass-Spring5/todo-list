package academy.learnprogramming.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {

    @GetMapping("/hello") //http://localhost:8080/todo-list/hello
    @ResponseBody //oznacza, że to co zwróci ta metoda ma być DOSŁOWNIE zwrócone do przeglądarki, czyli w tym przypadku będzi to słówko "hello", a nie widok (thymeleaf, jsp itp...) o nazwie "hello"
    public String hello() {
        return "hello";
    }

    @GetMapping("welcome") //http://localhost:8080/todo-list/welcome
    public String welcome(Model model) { //ten parametr będzie utworzony przez dispatcher servlet
        model.addAttribute("user", "Bartek");
        log.info("Oto model: {}", model);
        // prefix + name + suffix
        // /WEB-INF/view/welcome.jsp
        return "welcome";
    }

    @ModelAttribute("welcomeMessage") //ta metoda będzie odpalona przed jakimikolwiek innymi endpointami z tego kontrolera
    public String welcomeMessage() { //jej efekt jest taki, że do każdego modelu w każdym endpoincie doda atrybut o nazwie "welcomeMessage" o wartości takiej jak w returnie
        return "welcome to this Demo application."; //a więc we wcześniejszym endpoincie, w jego pliku-widoku będzie można z tego atrybutu skorzystać
    }


}
