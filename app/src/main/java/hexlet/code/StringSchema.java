package main.java.hexlet.code;

import java.util.function.Predicate;


public class StringSchema {

    // required – любая непустая строка
    // minLength – строка равна или длиннее указанного числа
    // contains – строка содержит определённую подстроку

    public Predicate<String> required() {
        return x -> x instanceof String && !x.equals("");
    }

    public Predicate<String> minLength(int minLength) {
        return x -> x.length() >= minLength;
    }

    public Predicate<String> contains(String subString) {
        return x -> x.contains(subString);
    }
}
