package pl.javastart.config.SpringMVCConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
@ComponentScan("pl.javastart")
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    public void addResourceHandlers(final ResourceHandlerRegistry registry){
        registry.addResourceHandler("/*.css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/*.js/**").addResourceLocations("/js/");

        registry.addResourceHandler("/img/**").addResourceLocations("/img/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());    }
}
