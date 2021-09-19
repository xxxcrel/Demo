package cn.qisee.spring;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Configuration
@ComponentScan(basePackages = "cn.qisee.spring")
class PropertyEditorDemo{
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PropertyEditorDemo.class);
        Article article = context.getBean(Article.class);
        article.print();
    }

    @Bean
    public static CustomEditorConfigurer customEditorConfigurer(){
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> map = new HashMap<>();
        map.put(Text.class, TextEditor.class);
        configurer.setCustomEditors(map);
        return configurer;
    }

    @Bean
    public Content content(){
        return new Content(new Text());
    }

}
