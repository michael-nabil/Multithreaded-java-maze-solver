/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course_system;

import java.util.Scanner;

/**
 *
 * @author OWNER
 */
public class menus {
    
    
    public static int choiceInput(String message)
    {
        
        System.out.print(message);
       
        Scanner input = new Scanner(System.in);
        
        String s="";
        
        s = input.nextLine();
        
        if(s.length()>1 || s.length()<1)    //if the input is a string not character
            return -1;
        else if((int)s.charAt(0)<49 || (int)s.charAt(0)>58)     //it causes an error if we use an alphabitic character in parsing
            return -1;
        else 
            return Integer.parseInt(s);
           
    }
    
    public static int choiceInput()
    {
       
        Scanner input = new Scanner(System.in);
        
        String s="";
        
        s = input.nextLine();
        
        if(s.length()>1 || s.length()<1)    //if the input is a string not character
            return -1;
        else if((int)s.charAt(0)<49 || (int)s.charAt(0)>58)     //it causes an error if we use an alphabitic character in parsing
            return -1;
        else 
            return Integer.parseInt(s);
           
    }
    
    
    public static int IntInput(String message){
        
        System.out.print(message);
        
        int result=-1;
        
        Scanner input = new Scanner(System.in);
        
        try{
            
            String s = input.nextLine();
            result = Integer.parseInt(s);
            
        }catch(NumberFormatException e)
        {
            return result;
        }
        
        return result;
    }
    
    
    public static String StringInput(String message){
        
        System.out.print(message);
        
        Scanner input = new Scanner(System.in);
        
        String s = input.nextLine();
        
        return s;
    }
    
    public static double DoubleInput(String message){
     
        
        System.out.print(message);
        
        double result=-1;
        
        Scanner input = new Scanner(System.in);
        
        try{
            
            String s = input.nextLine();
            result = Double.parseDouble(s);
            
        }catch(NumberFormatException e)
        {
            return result;
        }
        
        return result;
    }
    
    
    public static int IntInput(){
     
        int result=-1;
        
        Scanner input = new Scanner(System.in);
        
        try{
            
            String s = input.nextLine();
            result = Integer.parseInt(s);
            
        }catch(NumberFormatException e)
        {
            return result;
        }
        
        return result;
    }
    
    
    public static String StringInput(){
        Scanner input = new Scanner(System.in);
        
        String s = input.nextLine();
        
        return s;
    }
    
    
    public static double DoubleInput(){
     
        double result=-1;
        
        Scanner input = new Scanner(System.in);
        
        try{
            
            String s = input.nextLine();
            result = Double.parseDouble(s);
            
        }catch(NumberFormatException e)
        {
            return result;
        }
        
        return result;
    }
    
    
    
    public static void main_menu(){
       
        System.out.println("1- Sign in.");
        System.out.println("2- Exit program.");
        System.out.print("Your choice: ");

    }
    
    
    
    public static void student_menu(){
        System.out.println("-------------------Welcome-------------------");
        System.out.print("""
                         1. view all the available courses
                         2. view your registered courses
                         3. register a course
                         4. drop a course
                         5. get a course grade
                         6. make survey of a specific course
                         7. update your info
                         8. Courses near to start
                         9. Courses near to end
                        10. Exit
                         Enter your choice:\u00a0""");

    }
    
    public static void instructor_menu(){
        System.out.println("-------------------Welcome-------------------");
        System.out.println("1- Add Grade.");
        System.out.println("2- View personal information.");
        System.out.println("3- Exit.");
        System.out.print("Your choice: ");
    }
    
    public static void adminMenu(){
        System.out.println("-------------------Welcome-------------------");
        
        //main admin menu
        System.out.println("1- Add    (student,instructor,course)");
        System.out.println("2- Update (student,instructor,course)");
        System.out.println("3- Delete (student,instructor,course)");
        System.out.println("4- View students");
        System.out.println("5- View instructor");
        System.out.println("6- View personal information");
        System.out.println("7- Exit");
        System.out.print("Your choice: ");

    }
        public static void adminAddMenu(){
        
        System.out.println("1- Add (student)");
        System.out.println("2- Add (instructor)");
        System.out.println("3- Add (course)");
        System.out.println("4- Exit page");
        System.out.print("Your choice: ");


    }
    
       public static void adminUpdateMenu(){
        
        System.out.println("1- Update (student)");
        System.out.println("2- Update (instructor)");
        System.out.println("3- Update (course)");
        System.out.println("4- Exit page");
        System.out.print("Your choice: ");


    }
       
       public static void adminDeleteMenu(){
        
        System.out.println("1- Delete (student)");
        System.out.println("2- Delete (instructor)");
        System.out.println("3- Delete (course)");
        System.out.println("4- Exit page");
        System.out.print("Your choice: ");


    }
       
       
       public static void adminShowStudents(String students){
           
        if(students.length() == 0)
            System.out.println("No students to show");
        
        else{
            System.out.println("Students: ");
            System.out.println("Id:\t\tName:");
            System.out.println(students);
        }
        
       }
       
       public static void adminShowInstructors(String instructors){
           
        if(instructors.length() == 0)
            System.out.println("No instructors to show");

        else{
            System.out.println("instructors: ");
            System.out.println("Id:\tName:");
            System.out.println(instructors);   
        }
           
       }
}
