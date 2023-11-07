//package com.cheeseocean.javafx;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.Locale;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicReference;
//import java.util.logging.Logger;
//
//import javax.imageio.ImageIO;
//
//import org.springframework.core.io.ClassPathResource;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.concurrent.Worker;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.Scene;
//import javafx.scene.SnapshotParameters;
//import javafx.scene.SnapshotResult;
//import javafx.scene.image.WritableImage;
//import javafx.scene.paint.Color;
//import javafx.scene.web.WebView;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//
//public class SnapshotRaceCondition extends Application {
//    private static final Logger log = Logger.getLogger(SnapshotRaceCondition.class.getName());
//    // frequency for checking fx is started
//    private static final int STARTUP_TIMEOUT = 10; // seconds
//    private static final int STARTUP_SLEEP_INTERVAL = 250; // millis
//    // frequency for checking capture has occured
//    private static final int CAPTURE_SLEEP_INTERVAL = 10; // millis
//    // self reference
//    private static SnapshotRaceCondition instance = null;
//    // concurrent-safe containers for flags/exceptions/image data
//    private static AtomicBoolean started = new AtomicBoolean(false);
//    private static AtomicBoolean finished = new AtomicBoolean(true);
//    private static AtomicReference<Throwable> thrown = new AtomicReference<>(null);
//    private static AtomicReference<BufferedImage> capture = new AtomicReference<>(null);
//    // main javafx objects
//    private static WebView webView = null;
//    private static Stage stage = null;
//    /** Listens for a SUCCEEDED state to activate image capture **/
//    private static ChangeListener<Worker.State> stateListener = (ov, oldState, newState) -> {
//        if (newState == Worker.State.SUCCEEDED) {
//            new AnimationTimer() {
//                int frames = 0;
//
//                @Override
//                public void handle(long l) {
//                    // capture at exactly two frames
//                    if (++frames == 2) {
//                        System.out.println("Attempting image capture");
//                        webView.snapshot(new Callback<SnapshotResult,Void>() {
//                            @Override
//                            public Void call(SnapshotResult snapshotResult) {
//                                capture.set(SwingFXUtils.fromFXImage(snapshotResult.getImage(), null));
////                                unlatch();
////                                stage.show();
//                                stage.toBack();
//                                return null;
//                            }
//                        }, null, null);
//
//                        //stop timer after snapshot
//                        stop();
//                    }
//                }
//            }.start();
////            stage.hide();
//        }
//    };
//    /** Listen for failures **/
//    private static ChangeListener<Throwable> exceptListener = new ChangeListener<Throwable>() {
//        @Override
//        public void changed(ObservableValue<? extends Throwable> obs, Throwable oldExc, Throwable newExc) {
//            if (newExc != null) {thrown.set(newExc);}
//        }
//    };
//
//
//    /** Called by JavaFX thread */
//    public SnapshotRaceCondition() {
//        instance = this;
//    }
//
//    /** Starts JavaFX thread if not already running */
//    public static synchronized void initialize() throws IOException {
//        if (instance == null) {
//            new Thread(() -> Application.launch(SnapshotRaceCondition.class)).start();
//        }
//
//        for (int i = 0; i < (STARTUP_TIMEOUT * 1000); i += STARTUP_SLEEP_INTERVAL) {
//            if (started.get()) {break;}
//
//            log.fine("Waiting for JavaFX...");
//            try {Thread.sleep(STARTUP_SLEEP_INTERVAL);} catch (Exception ignore) {}
//        }
//
//        if (!started.get()) {
//            throw new IOException("JavaFX did not start");
//        }
//    }
//
//    /** Loads the specified HTML, triggering stateListener above **/
//    public static synchronized BufferedImage capture(final String html) throws Throwable {
//        capture.set(null);
//        thrown.set(null);
//        finished.set(false);
//
//        // run these actions on the JavaFX thread
//        Platform.runLater(new Thread(() -> {
//            try {
//                webView.getEngine().loadContent(html, "text/html");
//                webView.getEngine().reload();
//                stage.show(); // JDK-8087569: will not capture without showing stage
//                stage.toBack();
//            } catch (Throwable t) {
//                thrown.set(t);
//            }
//
//        }));
//
//        System.out.println("finished");
//        // wait for capture to complete by monitoring our own finished flag
//        while (!finished.get() && thrown.get() == null) {
//            System.out.println("Waiting on capture...");
//            try {
//                Thread.sleep(CAPTURE_SLEEP_INTERVAL);
//            } catch (InterruptedException e) {
//                log.warning(e.getLocalizedMessage());
//            }
//        }
//
//        if (thrown.get() != null) {
//            throw thrown.get();
//        }
//
//        System.out.println("get it");
//        return capture.get();
//    }
//
//    public static void main(String[] args) throws Throwable {
//        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
//        resolver.setPrefix("template/");
//        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
//        resolver.setSuffix(".html");
//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(resolver);
//        Context context = new Context(Locale.CHINA);
//
//        context.setVariable("name", "wuxc");
//        context.setVariable("week", new String[]{"Mon", "Tue", "Web", "Thu", "Fri"});
//        context.setVariable("data", new int[]{10, 20, 18, 30, 28});
//        String resolvedHtml = templateEngine.process("index", context);
//        ClassPathResource echartsFile = new ClassPathResource("/template/echarts.min.js");
//        String echarts = new String(Files.readAllBytes(echartsFile.getFile().toPath()), StandardCharsets.UTF_8);
//        String finalHtml = resolvedHtml.replace("###echartsjs###", echarts);
//        Files.write(Paths.get("/Users/wuxc", "chart.html"), finalHtml.getBytes(), StandardOpenOption.WRITE);
//        SnapshotRaceCondition.initialize();
//        BufferedImage image = SnapshotRaceCondition.capture(finalHtml);
//        ImageIO.write(image, "png", new File("/Users/wuxc/test.png"));
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        started.set(true);
//        log.fine("Started JavaFX, creating WebView...");
//        stage = primaryStage;
//        primaryStage.setScene(new Scene(webView = new WebView(), 900, 600, Color.web("lightgray")));
//
//        webView.getEngine().setJavaScriptEnabled(true);
//        // Add listener for SUCCEEDED
//        Worker<Void> worker = webView.getEngine().getLoadWorker();
//        worker.stateProperty().addListener(stateListener);
//
//        // Prevents JavaFX from shutting down when hiding window, useful for calling capture(...) in succession
//        Platform.setImplicitExit(false);
//    }
//}