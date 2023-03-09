package personal.project;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import personal.project.domain.formatter.NumberFormatter;
import personal.project.web.filter.LoginCheckFilter;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<Filter>();
        filter.setFilter(new LoginCheckFilter());
        filter.setOrder(1);
        filter.addUrlPatterns("/*");
        return filter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new NumberFormatter());
    }
}
