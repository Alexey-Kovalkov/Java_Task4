package learn.task44;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Task44Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Task44Application.class, args);
        Processing pr = ctx.getBean(Processing.class);
        pr.process();
    }

}
