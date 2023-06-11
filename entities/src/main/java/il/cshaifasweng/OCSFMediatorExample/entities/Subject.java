package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "subjects")
public class Subject implements Serializable {

    @Id
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public Subject() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
