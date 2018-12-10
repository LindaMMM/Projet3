package fr.bicomat.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
      
		registry.addViewController("/admin/users").setViewName("admin/users");
        registry.addViewController("/admin/userform").setViewName("admin/userform");
        registry.addViewController("/admin/usershow").setViewName("admin/usershow");
        
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/accessDenied").setViewName("accessDenied");
        registry.addViewController("/changedLogin").setViewName("changedLogin");
        registry.addViewController("/resetPassword").setViewName("resetPassword");
        
        registry.addViewController("/admin/index").setViewName("admin/index");
        
        registry.addViewController("/agent/index").setViewName("agent/index");
        registry.addViewController("/agent/newtiers").setViewName("agent/newtiers");
        registry.addViewController("/agent/newinterne").setViewName("agent/newinterne");
        registry.addViewController("/agent/newconseiller").setViewName("agent/newconseiller");
        
        registry.addViewController("/client/index").setViewName("client/index");
    }

}