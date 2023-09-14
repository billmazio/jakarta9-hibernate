
package gr.aueb.cf.schoolapp.validator;




import gr.aueb.cf.schoolapp.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapp.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MeetingValidator {

    public static Map<String, String> validate(MeetingInsertDTO dto) {
        Map<String, String> errors = new HashMap<>();

        // Room Validations
        String room = dto.getRoom();
        if (room == null || room.trim().isEmpty()) {
            errors.put("room", "Room name cannot be empty");
        }

        // Meeting Date Validations
        java.sql.Date meetingDateSql = dto.getMeetingDate();
        if (meetingDateSql == null) {
            errors.put("meetingDate", "Meeting date cannot be empty");
        } else {
            LocalDate meetingDate = meetingDateSql.toLocalDate();
            LocalDate currentDate = LocalDate.now();
            if (meetingDate.isBefore(currentDate)) {
                errors.put("meetingDate", "Meeting date cannot be earlier than the current date");
            }
        }

        // Student  Validations
        Student student = dto.getStudent();
        if (student == null) {
            errors.put("student", "Invalid student");
        }

        // Teacher  Validations
        Teacher teacher = dto.getTeacher();
        if (teacher == null) {
            errors.put("teacherId", "Invalid teacher ID");
        }

        return errors;
    }

    public static Map<String, String> validate(MeetingUpdateDTO dto) {
        Map<String, String> errors = new HashMap<>();

        // Room Validations
        String room = dto.getRoom();
        if (room == null || room.trim().isEmpty()) {
            errors.put("room", "Room name cannot be empty");
        }

        // Meeting Date Validations
        java.sql.Date meetingDateSql = dto.getMeetingDate();
        if (meetingDateSql == null) {
            errors.put("meetingDate", "Meeting date cannot be empty");
        } else {
            LocalDate meetingDate = meetingDateSql.toLocalDate();
            LocalDate currentDate = LocalDate.now();
            if (meetingDate.isBefore(currentDate)) {
                errors.put("meetingDate", "Meeting date cannot be earlier than the current date");
            }
        }

        // Student  Validations
        Student student = dto.getStudent();
        if (student == null) {
            errors.put("student", "Invalid student ");
        }

        // Teacher  Validations
        Teacher teacher = dto.getTeacher();
        if (teacher == null) {
            errors.put("teacher", "Invalid teacher ");
        }

        return errors;
    }
}
