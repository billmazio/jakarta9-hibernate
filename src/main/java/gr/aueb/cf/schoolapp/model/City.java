package gr.aueb.cf.schoolapp.model;





import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    public City() {
    }

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id != null && id.equals(city.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }



    public City addStudent(Student student) {
        this.students.add(student);
        student.setCity(this);
        return this;
    }

    public City removeStudent(Student student) {
        student.setCity(null);  // Remove the association from the student side
        this.students.remove(student);
        return this;
    }
}

