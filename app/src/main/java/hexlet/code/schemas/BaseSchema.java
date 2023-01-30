package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    protected List<Predicate> checksList = new ArrayList<>();
    private boolean isRequired;

    public final void setRequired(boolean required) {
        isRequired = required;
    }

    public BaseSchema required() {
        return this;
    }

    public final boolean isValid(Object schema) {

        if (!isRequired && !(isValidType(schema))) {
            return true;
        } else if (isRequired && (schema == null || !(isValidType(schema)))) {
            return false;
        } else {
            return checksList.stream().allMatch(check -> check.test(schema));
        }
    }

    boolean isValidType(Object object) {
        return true;
    }

}
