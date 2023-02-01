
#### Hexlet tests and linter status:
[![Actions Status](https://github.com/xushaha/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/xushaha/java-project-78/actions) [![Java CI](https://github.com/xushaha/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/xushaha/java-project-78/actions/workflows/main.yml) [![Maintainability](https://api.codeclimate.com/v1/badges/c6a9d7f42c9c6019b8dd/maintainability)](https://codeclimate.com/github/xushaha/java-project-78/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/c6a9d7f42c9c6019b8dd/test_coverage)](https://codeclimate.com/github/xushaha/java-project-78/test_coverage)

___

### Валидатор данных
*библиотека, которая проверяет, корректны ли введенные данные*



##### Пример использования:

`Validator v = new Validator();`




*Проверка строк*  
```
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false
```

*Проверка чисел*
```
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true
```

*Проверка объекта Map с поддержкой проверки структуры*
```
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false
```
