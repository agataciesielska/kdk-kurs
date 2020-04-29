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

    public String getFormatedBirthday() {
        return df.format(this.birthday);
    }
}