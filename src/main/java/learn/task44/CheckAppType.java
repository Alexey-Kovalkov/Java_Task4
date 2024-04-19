package learn.task44;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Order(2)
@LogTransformation()
public class CheckAppType implements DataChecker{
    @Override
    public String check(String str) {
        String res = "";
        String[] parts = str.split(" ");
        if (parts.length < 6) {return res;}
        ArrayList<String> appWords = new ArrayList<>(Arrays.asList("web", "mobile"));
        int i = 0;
        for (String f : parts){
            if (res.length() > 0) {res += " ";}
            if (i == 5){
                if (!appWords.contains(f)) {
                    res += "other:" + f.trim();
                } else {
                    res += f.trim();
                }
            } else {
                res += f.trim();
            }
            i++;
        }
        return res;
    }
}
