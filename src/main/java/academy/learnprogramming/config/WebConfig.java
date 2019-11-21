package academy.learnprogramming.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc //ta adnotacja odpowiada za uruchomienie całego mechanizmu MVC (view resolverów, request mappingów itp...)
// dzieje się to za pomocą rejestrowania odpowiednich beanów  (specyficznych dla MVC) w kontekście całej aplikacji
@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
public class WebConfig {

    //klasa - konfiguracja webowej części naszej aplikacji

}
