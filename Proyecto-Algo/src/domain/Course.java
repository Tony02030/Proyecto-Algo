/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author User
 */
public class Course {
    private String id;
    private String name;
    private int credits;
    private Career careerID;

    public Course(String id, String name, int credits, Career careerID) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.careerID = careerID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Career getCareerID() {
        return careerID;
    }

    public void setCareerID(Career careerID) {
        this.careerID = careerID;
    }

    
    
    
}
