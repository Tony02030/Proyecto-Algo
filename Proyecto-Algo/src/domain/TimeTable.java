/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class TimeTable implements java.io.Serializable{
   private Course courseID;
   private String period;
   private String schedule1;
   private String schedule2;
   private int idEnrollment;
   

    public TimeTable(Course courseID, String period, String schedule1, String schedule2,int idEnrollment) {
        this.courseID = courseID;
        this.period = period;
        this.schedule1 = schedule1;
        this.schedule2 = schedule2;
        this.idEnrollment=idEnrollment;
        
    }

    public int getIdEnrollment() {
        return idEnrollment;
    }

    public void setIdEnrollment(int idEnrollment) {
        this.idEnrollment = idEnrollment;
    }
    

    public Course getCourseID() {
        return courseID;
    }

    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSchedule1() {
        return schedule1;
    }

    public void setSchedule1(String schedule1) {
        this.schedule1 = schedule1;
    }

    public String getSchedule2() {
        return schedule2;
    }

    public void setSchedule2(String schedule2) {
        this.schedule2 = schedule2;
    }
   
   
}
