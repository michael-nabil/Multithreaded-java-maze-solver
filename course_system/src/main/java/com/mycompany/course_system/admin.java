/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course_system;


import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author OWNER
 */
public class admin extends person{
    
    
    private static final int id = 111;
     
    
    
    public admin(String name, String password) {
        super(name,password);
    }
    
    /**
     *
     * @param id
     */
    public admin(int id){
        super(id);
    }
    
    @Override
    public int getId(){
        
        return id;   
    }
       
    
    
    public boolean addUser(Object obj){
        
        
        boolean recordAdded = false;
        boolean fileCreated = false;
        boolean contentAdded = false;
        String id = "";
        String content = "";
        
        if(obj instanceof student){
            
            id = Integer.toString(((student) obj).getId());
            
            content = ((student) obj).getName() + "\n" + ((student) obj).getEmail() + "\n" + ((student) obj).getPassword() + "\n" + ((student) obj).getId();
            //obj = (student) obj;
            //adding his id to the students file
            files.addRecord(defaultPath+"students.txt",id);

            //create his own file.
            files.createFile(id);

            //adding content in his file(info)
            files.addRecord(defaultPath+id+".txt", content);            
        }
            
        else if(obj instanceof instructor){
            
            //obj = (instructor) obj;
            id = Integer.toString(((instructor) obj).getId());
            
            content = ((instructor) obj).getName() + "\n" + ((instructor) obj).getEmail() + "\n" + ((instructor) obj).getPassword() + "\n" + ((instructor) obj).getId();
            //obj = (student) obj;
            //adding his id to the students file
            files.addRecord(defaultPath+"instructors.txt",id);

            //create his own file.
            files.createFile(id);

            //adding content in his file(info)
            files.addRecord(defaultPath+id+".txt", content);  
            
        }
        
        return recordAdded && fileCreated && contentAdded;

    }
    
    
        public boolean addCourse(course c){
        
        //uncomment the lines below after implementing course class.
        
        String content = c.getId() + "\n" + c.getCourseName() + "\n" + c.getPrice() + "\n"
                +  c.getInstructorId()+ "\n" + c.getStartDate() + "\n" + c.getEndDate() + "\n" + c.getCourseDays();//based on the order in files
        
        
        boolean file = files.createFile(c.getId());//create his own file.
        
        boolean gradeFile = files.createFile(c.getId()+"_grades");//create his grades file.
        
        boolean surveyFile = files.createFile(c.getId()+"_survey");//create his survey file.
        
        
        boolean idAdded = files.addRecord(defaultPath+"courses.txt",c.getId());//adding the id in the courses file
        
        
        boolean detailsAdded = files.addRecord(defaultPath+c.getId()+".txt", content);//insert the course details in his own file
        
        
        if(c.nearToEnd())
            files.addRecord(defaultPath+"nearToEnd.txt", c.getId());
        
        if(c.nearToStart())
            files.addRecord(defaultPath+"nearToStart.txt", c.getId());
        
        return file && gradeFile && surveyFile && idAdded && detailsAdded;
        
    }
    
    
    
    public boolean updateCourse(course c) throws IOException{
        
        //update attribute 1
        
        String price = Double.toString(c.getPrice());
        
        boolean priceUpdate = files.updateRecord(defaultPath+c.getId()+".txt",price,3);
        //update attribute 2
        
        String id = Integer.toString(c.getInstructorId());
        
        boolean insID = files.updateRecord(defaultPath+c.getId()+".txt", id, 4);
        
        return priceUpdate && insID;
    }
    
    
    public boolean updateUserInfo(Object obj) throws IOException{
        
        boolean nameUpdate = false;
        boolean emailUpdate = false;
        
        if(obj instanceof student){
            
            //obj = (student) obj;
            //update name
            nameUpdate  = files.updateRecord(defaultPath+((student) obj).getId()+".txt", ((student) obj).getName(), 1);//index of name
            //update email
            emailUpdate = files.updateRecord(defaultPath+((student) obj).getId()+".txt", ((student) obj).getEmail()+ "@fci.helwan.edu.eg", 2);//index of email
            
        }
            
        else if(obj instanceof instructor){
            
            //obj = (instructor) obj;
            //update name
            nameUpdate  = files.updateRecord(defaultPath+((instructor) obj).getId()+".txt", ((instructor) obj).getName(), 1);//index of name
            //update email
            emailUpdate = files.updateRecord(defaultPath+((instructor) obj).getId()+".txt", ((instructor) obj).getEmail()+ "@fci.helwan.edu.eg", 2);//index of email

            
        }
        
        return nameUpdate && emailUpdate;
    }
    
    
    
    public boolean deleteStudent(int studentId) throws IOException{
        
        String id = Integer.toString(studentId);
        
        //delete id from students file
        boolean idDeleted = files.deleteRecordByID(defaultPath+"students.txt",id);
        
        //deleting his own file.
        boolean fileDeleted = files.deleteFile(defaultPath+id+".txt");
        //file exists and the
        

        return idDeleted && fileDeleted;
    }
    
    
    
    public boolean deleteInstructor(int instructorId) throws IOException{
        
        String id = Integer.toString(instructorId);
        
        //deleting id from instructors file
        boolean idDeleted = files.deleteRecordByID(defaultPath+"instructors.txt",id);
        
        //deleting his own file.
        boolean fileDeleted = files.deleteFile(defaultPath+id+".txt");
        
        
        return idDeleted && fileDeleted;
    }
    
   
   public boolean deleteCourse(String courseId) throws IOException{
        
        
        boolean recordDeleted = files.deleteRecordByID(defaultPath+"courses.txt",courseId);//deleting the course from courses file.
        
        boolean fileDeleted = files.deleteFile(defaultPath+courseId+".txt");//deleting it's own file.     
        
        boolean gradeDeleted = files.deleteFile(defaultPath+courseId+"_grades.txt");//deleting it's grade file.
        
        boolean surveyDeleted = files.deleteFile(defaultPath+courseId+"_survey.txt");//deleting it's survey file.
        
        
        if(files.recordExist(defaultPath+"nearToEnd.txt", courseId))
            files.deleteRecordByID(defaultPath+"nearToEnd.txt", courseId);
        
        if(files.recordExist(defaultPath+"nearToStart.txt", courseId))
            files.deleteRecordByID(defaultPath+"nearToStart.txt", courseId);
        
        return recordDeleted && fileDeleted && gradeDeleted && surveyDeleted;
    } 
   
  
    
    public String showInstructors() throws IOException{
 
        String instructors="";
        
        ArrayList<String> instructorsIds = files.readFile(defaultPath+"instructors.txt");//returns "" if doesn't exist
        
        if(instructorsIds.size() < 1)
            return "";
        
        ArrayList<String> instructorsNames = new ArrayList<>();
        
        for(int i = 0;i < instructorsIds.size();i++){
            
            instructorsNames.add( files.readRecord(defaultPath+instructorsIds.get(i)+".txt", 1) );
            instructors+= instructorsIds.get(i) + "\t" + instructorsNames.get(i) + "\n";
            
        }

        return instructors;
        
    }
    
    
    public String showStudents() throws IOException{
        
        String students="";
        
        ArrayList<String> studentsIds = files.readFile(defaultPath+"students.txt");//returns "" if doesn't exist
        
        if(studentsIds.size() < 1)
            return "";
            
        ArrayList<String> studentsNames = new ArrayList<>();
        
        for(int i = 0;i < studentsIds.size();i++){
            
            studentsNames.add( files.readRecord(defaultPath+studentsIds.get(i)+".txt", 1) );
            students+= studentsIds.get(i) + "\t\t" + studentsNames.get(i) + "\n";
            
        }
       
        
        
        return students;
    }
    
    
    @Override
    public String toString(){
        
        return "Name: " + this.getName() + "\n" + "Email: " + this.getEmail()
                
                + "\n" + "Password: "  + this.getPassword() + "\n" + "ID: " + this.getId() + "\n";
        
    }
    
    
}
