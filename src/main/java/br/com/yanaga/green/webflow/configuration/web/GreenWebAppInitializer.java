package br.com.yanaga.green.webflow.configuration.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import br.com.yanaga.green.webflow.configuration.root.GreenConfig;
import br.com.yanaga.green.webflow.configuration.web.webflow.WebflowServletConfig;

public class GreenWebAppInitializer implements WebApplicationInitializer {

	private static final EnumSet<DispatcherType> DEFAULT_DISPATCHER_TYPES = EnumSet.of(DispatcherType.REQUEST,
			DispatcherType.FORWARD);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		createRootContext(servletContext);
		configureContextParameters(servletContext);
		registerFilters(servletContext);
		registerServlets(servletContext);
	}

	private void createRootContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
		root.register(GreenConfig.class);
		servletContext.addListener(new ContextLoaderListener(root));
		servletContext.addListener(new RequestContextListener());
	}

	private void configureContextParameters(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.allowTextChildren", "true");
	}

	private void registerFilters(ServletContext servletContext) {
		registerFileUploadFilter(servletContext);
	}

	private void registerServlets(ServletContext servletContext) {
		registerWebflowServlet(servletContext);
	}

	private void registerFileUploadFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic fileUploadFilter = servletContext.addFilter("fileUploadFilter",
				new FileUploadFilter());
		fileUploadFilter.addMappingForServletNames(DEFAULT_DISPATCHER_TYPES, true, "webflow");
	}

	private void registerWebflowServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext webflowContext = new AnnotationConfigWebApplicationContext();
		webflowContext.register(WebflowServletConfig.class);
		ServletRegistration.Dynamic webflow = servletContext.addServlet("webflow",
				new DispatcherServlet(webflowContext));
		webflow.setLoadOnStartup(1);
		webflow.addMapping("/spring/*");
	}

}
