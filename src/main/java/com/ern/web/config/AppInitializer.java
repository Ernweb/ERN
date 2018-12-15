package com.ern.web.config;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.sun.faces.config.ConfigureListener;

public class AppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[] { AppConfig.class,AppSecConfig.class };
   }
 
   @Override
   protected Class<?>[] getServletConfigClasses() {
      //return new Class[] { AppMvcConfig.class };
	   return null;
   }
 
   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }
  
   
   @Override public void onStartup(ServletContext servletContext) throws ServletException {
	   //Set init params // Use JSF view templates saved as *.xhtml, for use with Facelets 
	   servletContext.setInitParameter("spring.profiles.active", "dev"); 
	   servletContext.setInitParameter("spring.profiles.default", "dev"); 
	   servletContext.setInitParameter("spring.liveBeansView.mbeanDomain", "dev"); 
	   servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml"); 
	   servletContext.setInitParameter("javax.faces.FACELETS_VIEW_MAPPINGS", "*.xhtml"); 
	   /*Project Stage Level*/
	   servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development"); 
	   /*Primefaces Themes*/
	   servletContext.setInitParameter("primefaces.THEME", "omega");
	   /*Added for Menu Resolver*/
	   servletContext.setInitParameter("facelets.SKIP_COMMENTS","true");  
	   servletContext.setInitParameter("javax.faces.WEBAPP_RESOURCES_DIRECTORY", "/webapp/resources");
	   ServletRegistration.Dynamic facesServlet = servletContext.addServlet("Faces Servlet", javax.faces.webapp.FacesServlet.class); 
	   facesServlet.setLoadOnStartup(1); 
	   facesServlet.addMapping("*.xhtml"); 
	   facesServlet.addMapping("*.png"); 
	   facesServlet.addMapping("*.jpg"); 
	   /*Add Filter configuration*/
	   servletContext.addFilter("securityFilter",new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");
	   /*ServletRegistration.Dynamic registration = servletContext.addServlet("dsp", new DispatcherServlet()); 
	   registration.setInitParameter("contextConfigLocation", ""); 
	   registration.setLoadOnStartup(1); 
	   registration.addMapping("/");*/
	   servletContext.addListener(ConfigureListener.class);
	   servletContext.addListener(org.springframework.web.context.request.RequestContextListener.class); 
	   
	   //Add OpenEntityManagerInViewFilter Filter 
	   /*servletContext.addFilter("openEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class).addMappingForUrlPatterns(null, true, "/*");*/ 
	   super.onStartup(servletContext);
   }
}