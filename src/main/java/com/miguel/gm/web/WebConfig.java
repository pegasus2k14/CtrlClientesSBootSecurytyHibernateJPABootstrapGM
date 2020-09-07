 package com.miguel.gm.web;
 
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    //Creamos un nuevo Bean
    @Bean
    public LocaleResolver localeResolver(){
        //var slr = new SessionLocaleResolver();
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }
    
    //configurando interceptor para cambiar de idioma de manera dinamica
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        //indicamos cual sera el nombre del parametro para cambiar
        //de lenguage
        lci.setParamName("lang");
        return lci;
    } 
    
    //Registramos el interceptor 
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    //Estableciendo el Path de inicio por defecto de la aplicacion
    //Sobre escribimos el metodo addViewControllers
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
       //indicamos el Path de inicio y le damos un nombre 
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }
}
