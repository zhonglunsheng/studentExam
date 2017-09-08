package com.lipop.model;


import javax.persistence.*;

@Entity
@Table(name = "t_student")
public class Student {
    private String id;
    private String name;
    private String password;
    private String sex;
    private String prefession;
    private String cardNo;

    private String flag="2";

    @Id
    @Column(name="id",unique=true,nullable=false,length=40)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name",length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password",length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "sex",length = 5)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "prefession",length = 5)
    public String getPrefession() {
        return prefession;
    }

    public void setPrefession(String prefession) {
        this.prefession = prefession;
    }

    public String getCardNo() {
        return cardNo;
    }

    @Column(name = "cardNo",length = 50)
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Transient
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
