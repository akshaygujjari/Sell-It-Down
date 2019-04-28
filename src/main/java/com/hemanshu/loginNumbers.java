package com.hemanshu;

import javax.persistence.*;

@Entity
@Table(name="loginlog")
public class loginNumbers {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int ID;

    private String user_id;
    private String date;
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}