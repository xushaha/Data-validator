package hexlet.code.schemas;

import java.util.function.Predicate;


public class StringSchema extends BaseSchema {

    @Override
    public boolean isValidType(Object object) {
        return object instanceof String;

    }

    @Override
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

}


