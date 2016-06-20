package com.demo.ssm.po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/28.
 */
public class User implements Serializable {
    private String id;
    private String uname;
    private String name;
    private String orgno;
    private String pwd;
    private String salt;
    private String sex;
    private Date birthday;
    private String note;
    private Integer status;
    private Timestamp create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgno() {
        return orgno;
    }

    public void setOrgno(String orgno) {
        this.orgno = orgno;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", note='" + note + '\'' +
                ", status=" + status +
                ", create_time=" + create_time +
                '}';
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("abc");
        System.out.print(user);
    }
}
