package learn.task44;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "logins")
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    public Login(Timestamp access_date, Integer user_id, String app){
        this.access_date = access_date;
        this.user_id = user_id;
        this.application = app;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Getter
    @Setter
    @Column(name = "access_date")
    Timestamp access_date;

    @Getter
    @Setter
    @Column(name = "user_id")
    Integer user_id;

    @Getter
    @Setter
    @Column(name = "application")
    String application;

    @Override
    public String toString()
    {
        return access_date + " " + user_id + " " + application;
    }
}
