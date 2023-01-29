package hexlet.code;

import hexlet.code.Schemas.BaseSchema;
import hexlet.code.Schemas.MapSchema;
import hexlet.code.Schemas.NumberSchema;
import hexlet.code.Schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestValidator {

    @Test
    public void testValidatorString() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(5));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(5));

        final int MINLENGTH = 5;
        schema.minLength(MINLENGTH);
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("wh"));

        schema.contains("wh");
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("waat does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

    }

    @Test
    public void testValidatorNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("abc"));
        assertTrue(schema.isValid(5));

        schema.required(); //любое число включая ноль
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("wh"));
        assertTrue(schema.isValid(10));

        schema.positive(); //положительное число
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-10));

        schema.range(5, 10); //диапазон, в который должны попадать числа включая границы
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(11));

    }

    @Test
    public void testValidatorMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("abc"));
        assertTrue(schema.isValid(5));

        schema.required(); //требуется тип данных Map
        assertTrue(schema.isValid(new HashMap<>()));
        assertFalse(schema.isValid("wh"));
        assertFalse(schema.isValid(10));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));

        schema.sizeof(2); //количество пар ключ-значений в объекте Map должно быть равно заданному
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));

    }

    @Test
    public void testValidatorShape() {

        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().required().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Valya");
        human2.put("age", -5);
        assertFalse(schema.isValid(human2)); // false


        Validator v2 = new Validator();
        MapSchema schema2 = v2.map();

        Map<String, BaseSchema> schemas2 = new HashMap<>();
        schemas2.put("name", v2.string().required());
        schemas2.put("age", v2.number().required().range(1, 5));
        schema2.shape(schemas2);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", 100);
        assertFalse(schema2.isValid(human3)); // False

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Kolya");
        human4.put("age", 3);
        assertTrue(schema2.isValid(human4)); // true


        Validator v3 = new Validator();
        MapSchema schema3 = v3.map();

        Map<String, BaseSchema> schemas3 = new HashMap<>();
        schemas3.put("name", v3.string().required().contains("oly"));
        schemas3.put("age", v3.number().required());
        schema3.shape(schemas3);

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "Anya");
        human5.put("quantity", "100");
        assertFalse(schema3.isValid(human5)); // False

        Map<String, Object> human6 = new HashMap<>();
        human6.put("name", "Kolya");
        human6.put("age", 3);
        assertTrue(schema3.isValid(human6)); // true


        Validator v4 = new Validator();
        MapSchema schema4 = v4.map();

        Map<String, BaseSchema> schemas4 = new HashMap<>();
        schemas4.put("name", v4.string().required().contains("oly"));
        schemas4.put("age", v4.number().required());
        schema4.shape(schemas4);

        Map<String, Object> human7 = new HashMap<>();
        human7.put("name", "Anya");
        human7.put("quantity", "100");
        assertFalse(schema4.isValid(human7)); // False

        Map<String, Object> human8 = new HashMap<>();
        human8.put("name", "Kolya");
        human8.put("age", 3);
        assertTrue(schema4.isValid(human8)); // true
    }
}
