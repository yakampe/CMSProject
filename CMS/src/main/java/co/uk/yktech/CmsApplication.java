package co.uk.yktech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import co.uk.yktech.auth.SecurityConfig;

@SpringBootApplication
public class CmsApplication extends AbstractAnnotationConfigDispatcherServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}
	

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return null;
	}
	
	

}
