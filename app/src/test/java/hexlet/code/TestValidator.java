package hexlet.code;

import hexlet.code.Schemas.NumberSchema;
import hexlet.code.Schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestValidator {

    @Test
    public void testValidatorString() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        int minLenth = 5;

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(5));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(5));

        schema.minLength(minLenth);
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
}


