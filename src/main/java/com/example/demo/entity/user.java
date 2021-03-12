package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="user")
@JsonIgnoreProperties(value ={"hibernatelazyInitializer" })
public class user implements Serializable {
    private static final long sersionUID=1L;
    @Id
    private String logname;
    @Column(length = 100)
    private String password;
    @Column(length = 100)
    private String phone;
    @Column(length = 200)
    private String email;
    @Column(length = 200)
    private String message;
    @Column(length = 200)
    private String pic;
    @Column(length = 200)
    private String Idcardforward;
    @Column(length = 200)
    private String Idcardbackward;

    @OneToMany(mappedBy="u",cascade= CascadeType.ALL)
    private List<userfollower> userfollowerlist1;

    public List<userfollower> getUserfollowerlist1() {
        return userfollowerlist1;
    }

    @OneToMany(mappedBy="bu",cascade= CascadeType.ALL)
    private List<userfollower> userfollowerlist2;

    public List<userfollower> getUserfollowerlist2() {
        return userfollowerlist2;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public static long getSersionUID() {
        return sersionUID;
    }


    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public String getPic() {
        return pic;
    }

    public String getLogname() {
        return logname;
    }

    public String getIdcardforward() {
        return Idcardforward;
    }

    public String getIdcardbackward() {
        return Idcardbackward;
    }

    public void setIdcardforward(String idcardforward) {
        Idcardforward = idcardforward;
    }

    public void setIdcardbackward(String idcardbackward) {
        Idcardbackward = idcardbackward;
    }

    public void setUserfollowerlist1(List<userfollower> userfollowerlist1) {
        this.userfollowerlist1 = userfollowerlist1;
    }

    public void setUserfollowerlist2(List<userfollower> userfollowerlist2) {
        this.userfollowerlist2 = userfollowerlist2;
    }
}
