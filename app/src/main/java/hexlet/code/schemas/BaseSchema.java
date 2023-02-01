package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    protected List<Predicate> checksList = new ArrayList<>();
    private boolean isRequired;

    public final void setRequired(boolean required) {
        isRequired = required;
    }

    abstract BaseSchema required();


    public final boolean isValid(Object schema) {

        if (!isRequired && !isValidType(schema)) {
            return true;
        } else if (isRequired && !isValidType(schema)) {
            return false;
        } else {
            return checksList.stream().allMatch(check -> check.test(schema));
        }
    }

    abstract boolean isValidType(Object object);


}
