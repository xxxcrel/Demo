package cn.qisee.javafx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class WebViewDemo {
    public static void main(String[] args) throws IOException {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("template/");
        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        resolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        Context context = new Context(Locale.CHINA);

        context.setVariable("name", "wuxc");
        context.setVariable("week", new String[]{"Mon", "Tue", "Web", "Thu", "Fri"});
        context.setVariable("data", new int[]{10, 20, 18, 30, 28});
        context.setVariable("rect", new Rect(3, 5));
        List<Rect> list = Arrays.asList(new Rect(1, 2), new Rect(2, 3));
        List<List<Object>> resolvedList = list.stream().map(rect -> {
            List<Object> innerList = new ArrayList<>();
            Field[] fields = rect.getClass().getDeclaredFields();
            for (Field f : fields){
                try {
                    innerList.add(f.get(rect));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return innerList;
        }).collect(Collectors.toList());
        context.setVariable("table", resolvedList);
        String resolvedHtml = templateEngine.process("index", context);
        System.out.println(resolvedHtml);
        ClassPathResource echartsFile = new ClassPathResource("/template/echarts.min.js");
        ClassPathResource gridFile = new ClassPathResource("/template/gridjs.production.min.js");
        String echarts = new String(Files.readAllBytes(echartsFile.getFile().toPath()), StandardCharsets.UTF_8);
        String grid = new String(Files.readAllBytes(gridFile.getFile().toPath()), StandardCharsets.UTF_8);
//        System.out.println(resolvedHtml);
        String finalHtml = resolvedHtml.replace("###echartsjs###", echarts);
        finalHtml = finalHtml.replace("###gridjs###", grid);
        try {
            StreamUtils.copy(finalHtml, StandardCharsets.UTF_8, new FileOutputStream(new File("/Users/wuxc/grid.html")));
        }catch (IOException e){
            e.printStackTrace();
        }
//        WebView wv = new WebView();
//        WebEngine engine = wv.getEngine();
//        engine.setJavaScriptEnabled(true);
//        engine.loadContent(finalHtml);
//        engine.getLoadWorker().stateProperty().addListener(((observable, oldValue, newValue) -> {
//            if (newValue == Worker.State.SUCCEEDED) {
////                System.out.println(engine.getDocument());
//                WritableImage image = wv.snapshot(new SnapshotParameters(), null);
//
//                try {
//                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File("/Users/wuxc/hello.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                Document doc = engine.getDocument();
//                try {
//                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
//                    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
//
//                    transformer.transform(new DOMSource(doc),
//                            new StreamResult(new OutputStreamWriter(System.out, "UTF-8")));
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//                try (OutputStream os = new FileOutputStream("/Users/wuxc/index.pdf")) {
//                    PdfRendererBuilder builder = new PdfRendererBuilder();
//                    builder.useFastMode();
//                    builder.withW3cDocument(engine.getDocument(), null);
//                    builder.toStream(os);
//                    builder.run();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }));
//        primaryStage.setScene(new Scene(wv));
//        primaryStage.show();

    }
}

class Rect {
    int x;
    int y;
    public Rect(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
