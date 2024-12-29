/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.course_system;


import static com.mycompany.course_system.person.defaultPath;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class course {

    private String courseName;
    private static int idCounter = files.getLastId(defaultPath+"courses.txt");
    private String id = "";
    private int instructorId;
    private double price;
    private String startDate;
    private String endDate;
    private long courseDays;
    
    
    
     public course(String name, double price, int instructorId, String startDate, String endDate) {
        
        if(idCounter == -1)
            idCounter = 111;
        
        courseName = name;
        id = name.substring(0, 2) + idCounter++;
        this.price = price;
        this.instructorId = instructorId;
        this.startDate = startDate;
        this.endDate = endDate;
        LocalDate startDatee = null;
        LocalDate endDatee = null;
        try{
            startDatee = LocalDate.parse(startDate);
            endDatee = LocalDate.parse(endDate);            
        }catch(java.time.format.DateTimeParseException e){
            
        }

        this.courseDays = ChronoUnit.DAYS.between(startDatee, endDatee);

        
    }
    
        public course(String id) {//indexes missing

        this.id = files.readRecord(defaultPath+id+".txt",1 );
        courseName =files.readRecord(defaultPath+id+".txt",2 );
        this.price = Double.parseDouble(files.readRecord(defaultPath+id+".txt",3 ));
        this.instructorId = Integer.parseInt(files.readRecord(defaultPath+id+".txt",4 ));
        this.startDate = files.readRecord(defaultPath+id+".txt",5 );
        this.endDate = files.readRecord(defaultPath+id+".txt", 6);
        this.courseDays = Long.parseLong(files.readRecord(defaultPath+id+".txt",7 )); 
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getId() {
        return id;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public double getPrice() {
        return price;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public long getCourseDays() {
        return courseDays;
    }
    
    public boolean nearToStart() {
        
        LocalDate startDatee = LocalDate.parse(startDate);
        LocalDate today = LocalDate.now();

        long daysToStart = ChronoUnit.DAYS.between(today,startDatee );

        return daysToStart < 30;
    }

    public boolean nearToEnd() {
       
        LocalDate endDatee = LocalDate.parse(endDate);
        LocalDate today = LocalDate.now();

        long daysToend = ChronoUnit.DAYS.between(endDatee,today );

        return daysToend < 30;
    }

    
    @Override
    public String toString(){
        
        return  "ID: " + this.getId() + "\n" + "Name: " + this.getCourseName() + "\n" + "Price: " + this.getPrice()+
                 "\n" + "Instructor ID: "  + this.getInstructorId() + "\n" + "Start date: " + this.getStartDate() + "\n" +
                "End date: " + this.getEndDate() + "\n" + "Days: " + this.getCourseDays();
        
    }
    
}
