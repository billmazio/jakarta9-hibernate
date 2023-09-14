package gr.aueb.cf.schoolapp.model;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthdate")
    private Date birthdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Meeting> meetings = new ArrayList<>();

    public Student() {
    }

    public Student(Integer id, String firstname, String lastname, String gender, Date birthdate, City city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthdate +
                ", city=" + city +
                '}';
    }

    public Student addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
        meeting.setStudent(this);
        return this;
    }

    public Student removeMeeting(Meeting meeting) {
        meeting.setStudent(null);  // Remove the association from the meeting side
        this.meetings.remove(meeting);
        return this;
    }
    public Student addToCity(City city) {
        city.addStudent(this);
        return this;
    }

    public Student removeFromCity() {
        if (this.city != null) {
            this.city.removeStudent(this);
        }
        return this;
    }
}

