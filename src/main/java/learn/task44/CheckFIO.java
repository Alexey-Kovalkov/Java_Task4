package learn.task44;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@LogTransformation()
public class CheckFIO implements DataChecker {
    @Override
    public String check(String str) {
        String res = "";
        String[] parts = str.split(" ");
        if (parts.length < 6) {return res;}
        int i = 0;
        for (String f : parts){
            if (res.length() > 0) {res += " ";}
            if (i >0 && i < 4){
                res += f.trim().substring(0, 1).toUpperCase() + f.trim().substring(1).toLowerCase();
            } else {
                res += f.trim();
            }
            i++;
        }
            return res;
    }
}
