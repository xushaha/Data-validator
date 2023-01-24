package hexlet.code;

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
}
