package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "principal")
public class Principal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String password;
    public Principal(){}
    public Principal(String name, String username, String password){
        super();
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public void copy(Principal p){
        if(p!=null) {
            this.id = p.getId();
            this.name = p.getName();
            this.username = p.getUsername();
            this.password = p.getPassword();
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}