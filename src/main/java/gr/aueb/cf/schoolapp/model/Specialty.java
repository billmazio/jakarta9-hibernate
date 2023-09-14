package gr.aueb.cf.schoolapp.model;





import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specialties")
    public class Specialty {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "name")
        private String name;


        @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL)
        private List<Teacher> teachers = new ArrayList<>();

        public Specialty() {
        }

        public Specialty(Integer id, String name) {
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

        @Override
        public String toString() {
            return "Specialty{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

    public Specialty addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.setSpecialty(this);
        return this;
    }

    public Specialty removeTeacher(Teacher teacher) {
        teacher.setSpecialty(null);
        this.teachers.remove(teacher);
        return this;
    }

}