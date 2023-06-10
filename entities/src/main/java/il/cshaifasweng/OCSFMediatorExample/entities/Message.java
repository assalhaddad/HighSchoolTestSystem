//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = -8224097662914849956L;
    private String message;
    private Object object;

    public Message(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public Message(String message) {
        this.message = message;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
