package learn.task44;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@DisplayName("Интеграционный тест")
class Task44ApplicationTests {

    @Test
    void contextLoads() throws Exception {
        String[] args = new String[0];
        ApplicationContext ctx = SpringApplication.run(Task44Application.class, args);
        Processing pr = ctx.getBean(Processing.class);
        pr.process();
        // Проверяем по кол-ву:
        // - уникальных логинов - 2
        // - записей без ошибок в Logins - 3
        DBUserRepo repU = ctx.getBean(DBUserRepo.class);
        Assertions.assertEquals(repU.count(), 2);
        DBLoginRepo repL = ctx.getBean(DBLoginRepo.class);
        Assertions.assertEquals(repL.count(), 3);
    }
}


