package school;

import school.config.DemoWebContextConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class DemoApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("======================================================");
		System.out.println("============= MyWebApplicationInitializer ============");
		System.out.println("======================================================");
		
		AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
		webApplicationContext.register(DemoWebContextConfig.class);//klasa u kojoj se definisu binovi koji se nalaze u web kontekstu
		webApplicationContext.setServletContext(servletContext);
		
		ServletRegistration.Dynamic dispatcherServlet = 
				servletContext.addServlet("mySpringDispatcherServlet", 
										 new DispatcherServlet(webApplicationContext));
		dispatcherServlet.addMapping("/");
		dispatcherServlet.setLoadOnStartup(1);
	}

}
