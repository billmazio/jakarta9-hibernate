package gr.aueb.cf.schoolapp.validator;




import gr.aueb.cf.schoolapp.dto.CityInsertDTO;
import gr.aueb.cf.schoolapp.dto.CityUpdateDTO;

import java.util.HashMap;
import java.util.Map;

public class CityValidator {
    private CityValidator() {}

    public static Map<String, String> validate(CityInsertDTO dto) {
        Map<String, String> errors = new HashMap<>();

        // City Name Validations
        String name = dto.getName();
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "City name must be provided.");
        }

        // Additional validations for CityInsertDTO can be added here if needed

        return errors;
    }

    public static Map<String, String> validate(CityUpdateDTO dto) {
        Map<String, String> errors = new HashMap<>();

        // City ID Validations
        int id = dto.getId();
        if (id <= 0) {
            errors.put("id", "Invalid city ID.");
        }

        // City Name Validations
        String name = dto.getName();
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "City name must be provided.");
        }

        // Additional validations for CityUpdateDTO can be added here if needed

        return errors;
    }
}
