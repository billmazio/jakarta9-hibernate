package gr.aueb.cf.schoolapp.service.exceptions;


import gr.aueb.cf.schoolapp.model.Specialty;

public class SpecialtyNotFoundException extends Exception{
    public SpecialtyNotFoundException(Specialty specialty) {
        super("Specialty with id " + specialty.getId() + " does not exist");
    }

    public SpecialtyNotFoundException(String s) {
        super(s);
    }
}
