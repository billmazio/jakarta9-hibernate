package gr.aueb.cf.schoolapp.service.exceptions;


import gr.aueb.cf.schoolapp.model.Teacher;

;

public class TeacherNotFoundException extends Exception {

    public TeacherNotFoundException(Teacher teacher) {
        super("Teacher with id " + teacher.getId() + " does not exist");
    }

    public TeacherNotFoundException(String s) {
        super(s);
    }
}
