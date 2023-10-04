package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "loginInfo")
public class LoginInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String type;

    public LoginInfo(){}
    public LoginInfo(String username, String password, String type){
        super();
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public void copy(LoginInfo l){
        this.id = l.getId();
        this.username = l.getUsername();
        this.password = l.getPassword();
        this.type = l.getType();
    }

    private int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getType() {
        return this.type;
    }
}