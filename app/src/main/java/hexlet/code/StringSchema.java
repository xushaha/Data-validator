package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class StringSchema {

    List<Predicate> checksList = new ArrayList<>();
    boolean isRequired;

    public StringSchema required() {
        setRequired(true);
        checksList.add(x -> x instanceof String && !x.equals(""));
        return this;
    }

    public StringSchema minLength(int minLength) {
        Predicate<String> minLeng = x -> x.length() >= minLength;
        checksList.add(minLeng);
        return this;
    }

    public StringSchema contains(String subString) {
        Predicate<String> contains = x -> x.contains(subString);
        checksList.add(contains);
        return this;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public boolean isValid(Object schema) {
        if (!isRequired) {
            return true;
        } else {
            return checksList.stream().allMatch(check -> check.test(schema));
        }
    }
}
