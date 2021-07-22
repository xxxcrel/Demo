package beer.cheese.compress;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.springframework.util.StreamUtils;


public class CompressDemo {
    public static void main(String[] args) {
//        extractFile();
        compressFile();
    }

    public static void extractFile() {
        String tarPath = System.getProperty("user.home") + "/license.tar.gz";
        try (InputStream fi = Files.newInputStream(Paths.get(tarPath))) {
            InputStream bi = new BufferedInputStream(fi);
            InputStream gzi = new GzipCompressorInputStream(bi);
            ArchiveInputStream ai = new TarArchiveInputStream(gzi);
            ArchiveEntry entry = ai.getNextEntry();
            System.out.println(entry.getName());
            Files.copy(ai, Paths.get(System.getProperty("user.home") + "/node-1"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void compressFile()  {

        try (FileOutputStream file = new FileOutputStream("/tmp/license.tar.gz");
             GZIPOutputStream gzipOut = new GZIPOutputStream(file);
             ArchiveOutputStream to = new TarArchiveOutputStream(gzipOut)) {
            try {
                String path = System.getProperty("user.home") + "/node-1";
                File licenseFile = new File(path);
                ArchiveEntry entry = to.createArchiveEntry(licenseFile, "node-1");
                to.putArchiveEntry(entry);
                try {
                    StreamUtils.copy(new FileInputStream(licenseFile), to);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                to.closeArchiveEntry();
            } catch (IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
