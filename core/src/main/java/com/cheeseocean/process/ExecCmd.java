package com.cheeseocean.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

public class ExecCmd {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", System.getProperty("user.home") + "/executable.jar", "hello");
        try {
            Process process = pb.start();
            try (Writer writer = new OutputStreamWriter(process.getOutputStream())) {
                writer.write("standard input");
                writer.flush();
            }
            try (InputStream in = process.getInputStream()) {
                System.out.println(StreamUtils.copyToString(in, StandardCharsets.UTF_8));
            }
            try (InputStream err = process.getErrorStream()) {
                System.out.println(StreamUtils.copyToString(err, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
