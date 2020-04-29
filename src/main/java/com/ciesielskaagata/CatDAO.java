package com.ciesielskaagata;

import java.util.ArrayList;
import java.util.List;

public class CatDAO {
    List<Cat> cats = new ArrayList<Cat>();

    public void saveCat(Cat cat) {
        cats.add(cat);
    }
}