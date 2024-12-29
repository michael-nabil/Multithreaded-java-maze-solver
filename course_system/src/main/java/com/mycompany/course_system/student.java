/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course_system;

//import java.util.ArrayList;

import static com.mycompany.course_system.person.defaultPath;
import java.io.IOException;







/**
 *
 * @author OWNER
 */
public class student extends person {
    
    private static int lastId = files.getLastId(defaultPath+"students.txt");
    private final int id;
   

    
    public student(String name, String password) {
        
        super(name,password);
        if(lastId == -1)
            lastId = 20220000;
        id = ++lastId;
        String fName=super.getName();
        if(fName.contains(" "))
            fName = fName.substring(0, super.getName().indexOf(" "));
        
        fName = super.getName().substring(0, super.getName().indexOf(" "));
        
        super.setEmail(fName + id + super.getEmail());
    }
    
    public student(String name) {
        
        super(name);
        if(lastId == -1)
            lastId = 20220000;
        id = ++lastId;
        String fName=super.getName();
        if(fName.contains(" "))
            fName = fName.substring(0, super.getName().indexOf(" "));
        
        super.setEmail(fName + id + super.getEmail());
        super.setPassword(id + fName);
      
    }
    
    
    public student(int id){//already exist
        
        super(id);
        this.id =  Integer.parseInt( files.readRecord(defaultPath+id+".txt", 4));
    }
    
    @Override
    public int getId(){
        return id;
    }
    
    @Override
    public String toString()
    {
        String studData="";
        studData+="Name: "+getName()+"\n";
        studData+="Email: "+getEmail()+"\n";
        studData+="password: "+getPassword()+"\n";
        studData+="Id: "+getId()+"\n";
        return studData;
    }
    
    
    public double getCourseGrade(String courseId){

        double grade;
        
        String stdId = Integer.toString(getId());
        
        String record = files.readRecord(defaultPath+courseId+"_grades.txt",stdId);
        String[] id_grade = record.split(" ") ;
        //get grade from course_grades file with course id , student id
        //use student id to get his grade
        if(record.length() == 0)
            return -1;
        grade = Double.parseDouble(id_grade[1]);
        return grade;
    }
    
    
    public  String getMyCourses(){//a3mlha ya michael
        String courses     = "";
        String currentLine ="";
        int i = 5; //index of the first cousre in student file
        while(true)
        {
            currentLine=files.readRecord(defaultPath+this.id+".txt", i); //will return empty string if the index of the file to read , is greater than the number of line in the file
            if(currentLine.length()==0) //the base condition when we reach the end of the student's file
                return courses;
            courses += currentLine + "-";
            i++;
        }
        
        //while(!courses.equals("-"));
        //return courses;
    }

    

    
    
    public  boolean makeSurvey(String courseId , String survey){//a3mlha ya michael        
        if(files.recordExist(defaultPath+this.id+".txt", courseId))//this.id
        {
            if(files.addRecord(defaultPath+courseId+"_survey.txt", survey))
                return true;
        }
        return false;
    }
    
    public  String getAllCourses() throws IOException{
        
        String courses = files.readFile2(defaultPath+"courses.txt");
        return courses;
    }
    
    public boolean registerCourses(String courseId){
        if(files.recordExist(defaultPath+"courses.txt", courseId)){
            if(files.addRecord(defaultPath+this.id+".txt",courseId ))   //student_course file
                return true;
        }

        return false;
    }
    
    /*public boolean updateInfo(String name,String password){
        //we have to set the name , password first
        boolean u1 = files.updateRecord(defaultPath+this.id+".txt", name, 1);
        
        boolean u2 = files.updateRecord(defaultPath+this.id+".txt", password, 3);
        
        if(u1 && u2)
            return true;
        
        return false;//implementation needed 
    }*/
    public boolean updateInfo() throws IOException {
        //we have to set the name , password first
        boolean u1 = files.updateRecord(defaultPath+this.id+".txt", this.getName(), 1);
        
        String name;
        
        if(this.getName().contains(" "))
            name = this.getName().substring(0, this.getName().indexOf(" "));
        else
            name = this.getName();

        String email = name+this.getId()+ "@fci.helwan.edu.eg";
        
        boolean u2 = files.updateRecord(defaultPath+this.id+".txt", email, 2);
        
        boolean u3 = files.updateRecord(defaultPath+this.id+".txt", this.getPassword(), 3);
        //implementation needed
        
        return u1 && u2 && u3;
    }
    
    public boolean dropCourse(String courseId) throws IOException{
        if(files.recordExist(defaultPath+this.id+".txt",courseId))
        {
            files.deleteRecordByID(defaultPath+this.id+".txt", courseId);
            return true;
        }
        else
            return false; 
    }
    
    public String coursesNearToStart() throws IOException{
        
        return files.readFile2(defaultPath+"nearToStart.txt");
        
    }
    
    public String coursesNearToEnd() throws IOException{
        
        return files.readFile2(defaultPath+"nearToEnd.txt");
        
    }
    
}
