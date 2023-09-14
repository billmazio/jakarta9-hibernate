
package gr.aueb.cf.schoolapp.dto;



import gr.aueb.cf.schoolapp.model.City;

import java.sql.Date;

public class StudentDeleteDTO extends Base {

    private String firstname;
    private String lastname;
    private String gender;
    private Date birthdate;
    private City city;

    public StudentDeleteDTO() {}


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }


}
