package proyectosd.controllers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Gilberto Leon <ripflame@gmail.com>
 */
public class LogWriter {

    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static void saveLogToFile(String logLine) throws IOException {
        Path path = Paths.get("log.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING,
                        StandardOpenOption.APPEND,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.SYNC)) {
            writer.write(logLine);
            writer.newLine();
        }
    }
}
