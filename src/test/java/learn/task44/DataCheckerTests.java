package learn.task44;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

public class DataCheckerTests {
    @Test
    @DisplayName("Проверка типа приложения")
    public void tstCheckAppType(){
        String s1 = "u1 Бендер Остап Ибрагимович 17.04.2024 ";
        String sweb = "web";
        String snoweb = "noweb";
        String sother = "other:";
        CheckAppType ct = new CheckAppType();
        // Случай указания приложения web или mobile - строка д.б. без изменений
        Assertions.assertEquals(ct.check(s1 + sweb), s1 + sweb);
        // Случай указания другого приложения - перед приложением должно добавитьcя "other:"
        Assertions.assertEquals(ct.check(s1 + snoweb), s1 + sother + snoweb);
    }

    @Test
    @DisplayName("Проверка даты")
    public void tstCheckDate(){
        String s1 = "u1 Бендер Остап Ибрагимович 17.04.2024 web";
        String s2 = "u1 Бендер Остап Ибрагимович w web";
        CheckDate cd = new CheckDate();
        // В случае корректной даты - строка не меняется
        Assertions.assertEquals(cd.check(s1), s1);
        // В случае некорректной даты - возвращается пустая строка
        Assertions.assertEquals(cd.check(s2), "");
    }

    @Test
    @DisplayName("Проверка ФИО")
    public void tstCheckFIO(){
        String s1 = "u1 Бендер Остап Ибрагимович 17.04.2024 web";
        String s2 = "u1 бендер остап ибрагимович 17.04.2024 web";
        CheckFIO cf = new CheckFIO();
        Assertions.assertEquals(s1, cf.check(s2));
    }
    }