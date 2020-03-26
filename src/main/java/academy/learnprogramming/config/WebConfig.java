package academy.learnprogramming.config;

import academy.learnprogramming.util.ViewNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc //ta adnotacja odpowiada za uruchomienie całego mechanizmu MVC (view resolverów, request mappingów itp...)
// dzieje się to za pomocą rejestrowania odpowiednich beanów  (specyficznych dla MVC) w kontekście całej aplikacji
@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
public class WebConfig implements WebMvcConfigurer {

    //klasa - konfiguracja webowej części naszej aplikacji

    //interfejs WebMvcConfigurer - defines callbacks methods to customize the java based configuration for spring mvc (which was enabled by the @EnableWebMvc annotation)
    //zawiera domyślne metody, które można zaimplementować do dostosowania naszej konfiguracji, jedną z tych metod jest metoda addViewControllers

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

    //jest używana do prostego, automatycznego dodawania kontrolerów, które będą pre-konfigurowane (razem z response statusem lub określonym widokiem jsp do zrenderowania)
    //przydatne jeśli endpoint i tak by nic nie robił ponad zwrócenie stringa z nazwą odpowiedniego widoku jsp
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName(ViewNames.HOME); //nie trzeba dodawać endpointu do żadnego kontrolera (lub nowego kontrolera)!
    }
}
