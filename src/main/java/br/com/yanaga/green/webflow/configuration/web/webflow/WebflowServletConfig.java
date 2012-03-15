package br.com.yanaga.green.webflow.configuration.web.webflow;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.yanaga.green.webflow.app.Pessoa;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { Pessoa.class, WebflowServletConfig.class }, scopedProxy = ScopedProxyMode.TARGET_CLASS, useDefaultFilters = false, includeFilters = {
		@Filter(Controller.class), @Filter(Configuration.class) })
@ImportResource("classpath*:META-INF/spring/servlet/webflow-servlet.xml")
public class WebflowServletConfig {

}