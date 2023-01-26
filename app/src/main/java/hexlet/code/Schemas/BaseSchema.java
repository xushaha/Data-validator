package hexlet.code.Schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    public List<Predicate> checksList = new ArrayList<>();
    public boolean isRequired;

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public BaseSchema required() {
        return this;
    }

    public boolean isValid(Object schema) {
        if (!isRequired) {
            return true;
        } else {
            return checksList.stream().allMatch(check -> check.test(schema));
        }
    }


}
