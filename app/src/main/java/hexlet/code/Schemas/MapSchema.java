package hexlet.code.Schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    @Override
    public MapSchema required() {
        setRequired(true);
        checksList.add(x -> x instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map> range = x -> x.size() == size;
        checksList.add(range);
        return this;
    }
}
