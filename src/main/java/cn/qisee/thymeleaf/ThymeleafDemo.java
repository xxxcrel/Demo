package cn.qisee.thymeleaf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

import javax.swing.*;

import org.springframework.core.io.ClassPathResource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


public class ThymeleafDemo extends JFrame {
    public static void main(String[] args) throws IOException {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("template/");
        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        resolver.setSuffix(".html");
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        Context context = new Context(Locale.CHINA);

        context.setVariable("name", "wuxc");
        context.setVariable("week", new String[]{"Mon", "Tue", "Web", "Thu", "Fri"});
        context.setVariable("data", new int[]{10, 20, 18, 30, 28});
        String resolvedHtml = engine.process("index", context);
        ClassPathResource echartsFile = new ClassPathResource("/template/echarts.min.js");
        String echarts = new String(Files.readAllBytes(echartsFile.getFile().toPath()), StandardCharsets.UTF_8);
        String finalHtml = resolvedHtml.replace("###echartsjs###", echarts);
        System.out.println(resolvedHtml);
        Files.write(Paths.get(System.getProperty("user.home") + "/index.html"), finalHtml.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
//        ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName("JavaScript");
//        try {
//            jsEngine.eval(finalHtml);
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        }

    }


}
