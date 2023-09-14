package gr.aueb.cf.schoolapp.dto;


import gr.aueb.cf.schoolapp.model.Specialty;

public class TeacherDeleteDTO extends Base {
    private String firstname;
    private String lastname;

    private Specialty specialty;
    public TeacherDeleteDTO() {}

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
