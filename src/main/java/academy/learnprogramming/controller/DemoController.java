package academy.learnprogramming.controller;

import academy.learnprogramming.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {

    // == fields ==
    private final DemoService service;

    // == constructors ==
    @Autowired
    public DemoController(DemoService service) {
        this.service = service;
    }

    // == request methods ==

    @GetMapping("/hello") //http://localhost:8080/todo-list/hello?user=Bartek
    @ResponseBody //oznacza, że to co zwróci ta metoda ma być DOSŁOWNIE zwrócone do przeglądarki, czyli w tym przypadku będzi to słówko "hello", a nie widok (thymeleaf, jsp itp...) o nazwie "hello"
    public String hello() {
        return "hello";
    }

    @GetMapping("welcome") //http://localhost:8080/todo-list/welcome
    public String welcome(@RequestParam String user, @RequestParam int age, Model model) { //ten parametr będzie utworzony przez dispatcher servlet

        String helloMessage = service.getHelloMessage(user);
        model.addAttribute("helloMessage", helloMessage);
        model.addAttribute("age", age);

        log.info("Oto model: {}", model);
        // prefix + name + suffix
        // /WEB-INF/view/welcome.jsp
        return "welcome";
    }

    // == model attributes ==
    @ModelAttribute("welcomeMessage") //ta metoda będzie odpalona przed jakimikolwiek innymi endpointami z tego kontrolera
    public String welcomeMessage() { //jej efekt jest taki, że do każdego modelu w każdym endpoincie doda atrybut o nazwie "welcomeMessage" o wartości takiej jak w returnie
        return service.getWelcomeMessage(); //a więc we wcześniejszym endpoincie, w jego pliku-widoku będzie można z tego atrybutu skorzystać
    }


}
