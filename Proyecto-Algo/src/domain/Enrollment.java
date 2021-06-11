/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author User
 */
public class Enrollment implements Serializable{
   private int id;
   private Date date;
   private Student studentID;
   private Course courseID;
   private String schedule;
   private int idEnroll;
   private int identifier;

    public Enrollment(int id, Date date, Student studentID, Course courseID, String schedule,int idEnroll, int identifier) {
        this.id = id;
        this.date = date;
        this.studentID = studentID;
        this.courseID = courseID;
        this.schedule = schedule;
        this.idEnroll=idEnroll;
        this.identifier=identifier;
    }

    public int getIdEnroll() {
        return idEnroll;
    }

    public void setIdEnroll(int idEnroll) {
        this.idEnroll = idEnroll;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudentID() {
        return studentID;
    }

    public void setStudentID(Student studentID) {
        this.studentID = studentID;
    }

    public Course getCourseID() {
        return courseID;
    }

    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }

    

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
   
   
}
