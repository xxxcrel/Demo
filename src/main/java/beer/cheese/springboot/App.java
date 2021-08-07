package beer.cheese.springboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@ComponentScan(basePackages = {"beer.cheese.service", "beer.cheese.springboot"})
@RestController
@RequestMapping("/api")
@CommonsLog
@EnableScheduling
@CrossOrigin
public class App extends WebSecurityConfigurerAdapter {

    private static String staticValue;
    @Value("${beer.cheese.key}")
    private String value;
    @Value("${server.regex}")
    private String address;

    public static void main(String[] args) {
//        System.setProperty("spring.profiles.include", "service");
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(App.class)
//                .initializers(new ServiceInitializer())
//                .listeners(new ServiceListener())
                .run(args);
        log.info("value is: " + staticValue);
        App app = (App)ctx.getBean(App.class);
        log.info("address: " + app.address);
        log.info(Pattern.matches(app.address, "/bin/bash | awk '{print $1, $2}'"));
//        FlyService flyService = ctx.getBean(FlyService.class);
//        System.out.println("beer config :" + flyService.getModuleName());
//
//        System.out.println("getProp: " + System.getProperty("spring.profiles.include"));
//        CheeseProperties properties = ctx.getBean(CheeseProperties.class);
//        System.out.println(properties.appName);
//        properties.map.entrySet().forEach((entry) -> {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        });
//        new ArrayList<String>(){
//            {
//               add("nothing");
//            };
//        };
    }

    @Value("${beer.cheese.key}")
    private void setValue(String value) {
        App.staticValue = value;
    }

    @Scheduled(cron = "${beer.cheese.cron}")
    public void schedule() {
        System.out.println("hello");
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=request.json");
        File something = new File(System.getProperty("user.home").concat("/tmp.json"));
        try {
            StreamUtils.copy(new FileInputStream(something), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/license")
    public void downloadLicense(HttpServletResponse response) {
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(response.getOutputStream());
             ArchiveOutputStream out = new TarArchiveOutputStream(gzipOut)) {
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=license.tar.gz");
            File licenseFile = new File(System.getProperty("user.home") + "/node-1");
            ArchiveEntry entry = out.createArchiveEntry(licenseFile, "node-1");
            out.putArchiveEntry(entry);
            StreamUtils.copy(new FileInputStream(licenseFile), out);
            out.closeArchiveEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletRequest response) {
        HttpSession session = request.getSession(true);
        session.setAttribute("username", "anonymous");
    }

    @GetMapping("/hello")
    public void hello(HttpServletRequest request) {
        log.info("Session.username:" + request.getSession().getAttribute("username"));
        for (Cookie cookie : request.getCookies()) {
            log.info("Comment: " + cookie.getComment());
            log.info("Domain: " + cookie.getDomain());
            log.info("Name: " + cookie.getName());
            log.info("MaxAge: " + cookie.getMaxAge());
            log.info("Path: " + cookie.getPath());
            log.info("Value: " + cookie.getValue());
            log.info("Version: " + cookie.getVersion());
        }
    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //TODO: just override default security config
//    }

    @GetMapping("/remoteIP")
    public String getRemoteIP(HttpServletRequest request) {
        log.info(request.getRemoteAddr());
        return request.getRemoteAddr();
    }
}
