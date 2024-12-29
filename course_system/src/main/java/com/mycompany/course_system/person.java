/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course_system;

/**
 *
 * @author OWNER
 */
public abstract class  person {
    
    private String name;
    private String email;
    private String password;
    public static String defaultPath = "D:\\java\\projects\\course_system\\course_system\\src\\main\\java\\com\\mycompany\\course_system\\all_files\\";
    
    public person(){
        
    }
    
    
    public person(String name){
       
        this.name = name;
        this.email = "@fci.helwan.edu.eg";
    }
    
     public person(String name,String password){
         
         this.name = name;
         this.email = "@fci.helwan.edu.eg";
         this.password = password;
         
     }
     
     
     public person(int id){//already exist
         
         this.name     = files.readRecord(defaultPath+id+".txt", 1);
         this.email    = files.readRecord(defaultPath+id+".txt", 2);
         this.password = files.readRecord(defaultPath+id+".txt", 3);
         
         
     }

     
     public static int login(int id ,String pass){
         
         
         
        if (id < 10000){
            //check if exist from admin file.
            String id_s = Integer.toString(id);
            boolean exist = files.recordExist(defaultPath+id+".txt",id_s );
            if(exist)//comparing password
                if(pass.equals(files.readRecord(defaultPath+id_s+".txt", 3)))//3 is the index of password in files
                    return 1;
            //return true or false.
        }
        
        else if (id > 20220000){
            //check if exist from students file.
            String id_s = Integer.toString(id);
            boolean exist = files.recordExist(defaultPath+"students.txt",id_s );
            if(exist)//comparing password
                if(pass.equals(files.readRecord(defaultPath+id_s+".txt", 3)))//3 is the index of password in files
                    return 2;
                      
        }
        
        else if (id > 10000 && id < 20220000){
            
            //check if exist from instructors file.
            String id_s = Integer.toString(id);
            boolean exist = files.recordExist(defaultPath+"instructors.txt",id_s );
            if(exist)//comparing password
                if(pass.equals(files.readRecord(defaultPath+id_s+".txt", 3)))//3 is the index of password in files
                    return 3;
            //return true or false.
        } 
        return -1;
    }
     
     public void setName(String name){
         this.name = name;
     }
     
     public void setEmail(String email){
         this.email = email;
     }
     
     public void setPassword(String password){
         this.password = password;
     }
     
     public String getName(){
         return name;
     }
    
     public String getEmail(){
         return email;
     }
     
     public String getPassword(){
         return password;
     }
     
     public abstract int getId();
     
    @Override
     public String toString(){
         return "Name: " + this.name + "\n" + "Email: " + this.email + "\n" + "Password: " + this.password;
     }
     
}
