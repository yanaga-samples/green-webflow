package br.com.yanaga.green.webflow.configuration.root;

import org.springframework.beans.factory.aspectj.EnableSpringConfigured;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

@Configuration
@EnableSpringConfigured
@EnableAsync(mode = AdviceMode.ASPECTJ)
@ComponentScan(basePackageClasses = { GreenConfig.class }, scopedProxy = ScopedProxyMode.TARGET_CLASS, excludeFilters = @Filter(Controller.class))
public class GreenConfig {

}
