package gr.aueb.cf.schoolapp.model;





import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "room")
    private String room;

    @Column(name = "meeting_date")
    private Date meetingDate;

    public Meeting() {
    }

    public Meeting(Integer id, Teacher teacher, Student student, String room, Date meetingDate) {
        this.id = id;
        this.teacher = teacher;
        this.student = student;
        this.room = room;
        this.meetingDate = meetingDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", student=" + student +
                ", room='" + room + '\'' +
                ", meetingDate=" + meetingDate +
                '}';
    }
}
