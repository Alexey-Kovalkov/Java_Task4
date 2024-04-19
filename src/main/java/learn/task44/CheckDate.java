package learn.task44;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Order(3)
@LogTransformation()
public class CheckDate implements DataChecker {
    @Value("${spring.application.patherr}")
    private String logPath;
    @Value("${spring.application.pathinput}")
    private String sourcePath;
    private void logBadDatRecord(String s){
        try {
            if (logPath != null) {
                FileWriter fw = new FileWriter(logPath, true);
                fw.write("Файл: " + sourcePath + " - " + s + "\n");
                fw.close();}
        } catch (IOException eio) {
            throw new RuntimeException(eio);
        }
    }
    public String check(String str) {
        String[] parts = str.split(" ");
        if (parts.length < 6) {return "";}
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date parsedDate = dateFormat.parse(parts[4]);
            Timestamp t = new java.sql.Timestamp(parsedDate.getTime());
            return str;
        } catch(ParseException e) {
            logBadDatRecord(parts[1] + " " + parts[2] + " " + parts[3]);
            return "";
        }
    }
}
