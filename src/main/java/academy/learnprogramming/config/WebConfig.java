package academy.learnprogramming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc //ta adnotacja odpowiada za uruchomienie całego mechanizmu MVC (view resolverów, request mappingów itp...)
// dzieje się to za pomocą rejestrowania odpowiednich beanów  (specyficznych dla MVC) w kontekście całej aplikacji
@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
public class WebConfig {

    //klasa - konfiguracja webowej części naszej aplikacji

    // constants
    public static final String RESOLVER_PREFIX = "/WEB-INF/view/";
    public static final String RESOLVER_SUFFIX = ".jsp";

    //utworzenie i konfiguracje view resolvera
    //robimy to poprzez stworzenei odpowiedniego beana
    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new InternalResourceViewResolver(); //wybieramy sobie taki type view resolvera
        viewResolver.setPrefix(RESOLVER_PREFIX);                                //ponadto obsługuje on JSTL
        viewResolver.setSuffix(RESOLVER_SUFFIX);
        return viewResolver;
    }
}
