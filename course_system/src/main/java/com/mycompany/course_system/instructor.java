/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course_system;

/**
 *
 * @author OWNER
 */
public class instructor extends person {
    
    private static int lastId = files.getLastId(defaultPath+"instructors.txt");
    private final int id;
    
    
    public instructor(String name,String password) {
        super(name, password);
        if(lastId == -1)
            lastId = 10000;
        id = ++lastId;
        
        String fName=super.getName();
        if(fName.contains(" "))
            fName = fName.substring(0, super.getName().indexOf(" "));
        
    
        super.setEmail( fName + id + super.getEmail());
        
    }
    
    public instructor(String name) {
        super(name);
        if(lastId == -1)
            lastId = 10000;
        id = ++lastId;
        
        String fName=super.getName();
        if(fName.contains(" "))
            fName = fName.substring(0, super.getName().indexOf(" "));
        
        super.setEmail(fName + id + super.getEmail());
        super.setPassword(id + fName);
    }
    
    public instructor(int id){//already exist
        
        super(id);
        this.id =  Integer.parseInt( files.readRecord(defaultPath+id+".txt", 4));
    }
    
    @Override
    public int getId(){
        return id;
    }
    
    
    public boolean addGrade(String courseId,int studentId,int grade){
        
        String insId = Integer.toString(this.getId());
        
        if(files.recordExist(defaultPath+studentId+".txt", courseId) && files.recordExist(defaultPath+courseId+".txt",insId) && (grade >= 0 && grade <= 100) )
            return files.addRecord(defaultPath + courseId + "_grades.txt" , studentId + " " + grade);
        
        else
            return false;

    }
    
    
    @Override
    public String toString(){
        
        return "Name: " + this.getName() + "\n" + "Email: " + this.getEmail()
                
                + "\n" + "Password: "  + this.getPassword() + "\n" + "ID: " + this.getId() + "\n";
        
    }
    
    
    
    
}
