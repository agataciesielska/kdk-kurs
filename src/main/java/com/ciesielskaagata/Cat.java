package com.ciesielskaagata;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Cat {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    String name;
    Date birthday;
    Float weight;
    String keeperName;

    public String introduce() {
        System.out.println("Hello. Cat named " + name + " was born on " + this.getFormatedBirthday() + " and right now his/her weight is " +
                weight + "kg. The keeper is " + keeperName + ".");

        return "Hello. Cat named " + name + " was born on " + this.getFormatedBirthday() + " and right now his/her weight is " +
                weight + "kg. The keeper is " + keeperName + ".";
    }

    public String getFormatedBirthday() {
        return df.format(this.birthday);
    }
}
