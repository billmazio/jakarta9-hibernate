package gr.aueb.cf.schoolapp.model;





import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Meeting> meetings = new ArrayList<>();


    public Teacher() {
    }

    public Teacher(Integer id, String firstname, String lastname, Specialty specialty) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialty = specialty;
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", specialty=" + specialty +
                '}';
    }
    public Teacher addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
        meeting.setTeacher(this);
        return this;
    }

    public Teacher removeMeeting(Meeting meeting) {
        meeting.setTeacher(null);  // Remove the association from the meeting side
        this.meetings.remove(meeting);
        return this;
    }

    public Teacher addSpecialty(Specialty specialty) {
        this.specialty = specialty;
        specialty.getTeachers().add(this);
        return this;
    }

    public Teacher removeSpecialty() {
        if (this.specialty != null) {
            this.specialty.getTeachers().remove(this);
            this.specialty = null;
        }
        return this;
    }

}