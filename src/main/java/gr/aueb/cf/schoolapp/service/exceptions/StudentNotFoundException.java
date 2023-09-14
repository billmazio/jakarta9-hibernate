package gr.aueb.cf.schoolapp.service.exceptions;


import gr.aueb.cf.schoolapp.model.Student;

public class StudentNotFoundException extends Exception {
       public StudentNotFoundException(Student student) {
        super("Student with id " +student.getId() + " does not exist");
    }



    public StudentNotFoundException(String s) {
        super(s);
    }
}
