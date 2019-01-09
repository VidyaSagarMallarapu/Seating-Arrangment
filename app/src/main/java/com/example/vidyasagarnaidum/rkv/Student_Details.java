package com.example.vidyasagarnaidum.rkv;
import java.io.Serializable;

public class Student_Details implements Serializable{
    private String name;
    private String id;
    private String password;
    private String branch;
    private String seating;
    private String exams;
    private String date,time;
    private String mode;

    public Student_Details()
    {

    }
    public Student_Details(String nam)
    {
    id=nam;
    }
   public Student_Details(String id, String password, String branch, String seating,String exams, String date, String time,String mode,String name) {
        this.id=id;
        this.name = name;
        this.password = password;
        this.branch = branch;
        this.seating = seating;
        this.mode=mode;
        this.date=date;
        this.time=time;
        this.exams=exams;


    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setExams(String exams) {
        this.exams = exams;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getBranch() {
        return branch;
    }

    public String getSeating() {
        return seating;
    }
    public String getId() {
        return id;
    }
    public String getExams() {
        return exams;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getMode() {
        return mode;
    }
}
