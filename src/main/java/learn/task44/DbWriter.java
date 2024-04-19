package learn.task44;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class DbWriter implements DataWriter {
    @Autowired
    ApplicationContext ctx;
    private Optional<User> getUserByLogin(String userlogin){
        User uExample = new User();
        uExample.setUsername(userlogin);
        Example<User> example = Example.of(uExample);
        return ctx.getBean(DBUserRepo.class).findOne(example);
    }

    private Timestamp convStrToTimestamp (String s) {
        try {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date parsedDate = dateFormat.parse(s);
            return new java.sql.Timestamp(parsedDate.getTime());
        } catch(ParseException e) {
            return  null;
        }
    }

    @Override
    public void write(String str) {
        String[] parts = str.split(" ");
        if (parts.length < 6) {return;}
        DBUserRepo repU = ctx.getBean(DBUserRepo.class);

        String sfio = parts[1] + " " + parts[2] + " " + parts[3];
        Optional<User> u = getUserByLogin(parts[0]);
        u.ifPresent(user -> {repU.updateUserSetNameForIdNative(sfio, user.id);});
        if (u.isEmpty()){
            repU.save(new User(parts[0], sfio));
            u = getUserByLogin(parts[0]);
        }
        DBLoginRepo repL = ctx.getBean(DBLoginRepo.class);
        u.ifPresent(user -> {repL.save(new Login(convStrToTimestamp(parts[4]), user.id, parts[5]));});
    }
}
