package gr.aueb.cf.schoolapp.validator;



import gr.aueb.cf.schoolapp.dto.SpecialtyInsertDTO;
import gr.aueb.cf.schoolapp.dto.SpecialtyUpdateDTO;

import java.util.HashMap;
import java.util.Map;

public class SpecialtyValidator {

    private SpecialtyValidator() {}

    public static Map<String ,String> validate(SpecialtyInsertDTO dto) {
        Map<String ,String> errors = new HashMap<>();

        String name = dto.getName();
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Specialty  must be provided.");
        }



        return errors;
    }

    public static Map<String ,String> validate(SpecialtyUpdateDTO dto) {
        Map<String ,String> errors = new HashMap<>();

        int id = dto.getId();
        if (id <= 0) {
            errors.put("id", "Invalid specialty ID.");
        }

        String name = dto.getName();
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Specialty must be provided.");
        }


        return errors;
    }
}
