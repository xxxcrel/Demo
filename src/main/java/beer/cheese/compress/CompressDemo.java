package beer.cheese.compress;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;


public class CompressDemo {
    public static void main(String[] args) {
        extractFile();
    }

    public static void extractFile(){
        String tarPath = System.getProperty("user.home") + "/license.tar.gz";
        try(InputStream fi = Files.newInputStream(Paths.get(tarPath))){
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
}
