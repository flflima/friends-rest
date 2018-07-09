package br.com.dev.friends.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class FriendsAppInitializer implements WebApplicationInitializer {

    private String TMP_FOLDER = "/tmp"; 
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024; 
    
	@Override
	public void onStartup(ServletContext container) {
		final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		container.addListener(new ContextLoaderListener(rootContext));

		// create servlet
		final AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(ApplicationConfiguration.class);
		
		// register servlet
		final ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
				new DispatcherServlet(dispatcherServlet));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");

		// add multipart capability
		final MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, 
		          MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
		dispatcher.setMultipartConfig(multipartConfigElement);
	}
}