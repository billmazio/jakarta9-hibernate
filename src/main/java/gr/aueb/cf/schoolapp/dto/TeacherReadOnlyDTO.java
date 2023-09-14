package gr.aueb.cf.schoolapp.dto;

public class TeacherReadOnlyDTO extends Base {
    String firstname;
    String lastname;

    public TeacherReadOnlyDTO() {}

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
