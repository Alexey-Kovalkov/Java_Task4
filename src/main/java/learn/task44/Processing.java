package learn.task44;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Processing {
    @Setter
    @Getter
    @Autowired
    DataReader dr;
    @Setter
    @Getter
    @Autowired
    DbWriter dw;

    @Autowired
    public List<DataChecker> checks;

    public void process() {
        List<String> arg = dr.dtread();
        for (String s : arg) {
            // бежим по строкам, обрабатываем, сохраняем
            for (DataChecker chk : checks) {
                s = chk.check(s);
            }
            dw.write(s);
        };
    }
}
