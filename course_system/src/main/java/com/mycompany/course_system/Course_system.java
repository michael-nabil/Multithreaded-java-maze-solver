/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.course_system;

import java.io.IOException;

/**
 *
 * @author OWNER
 */
public class Course_system {

    public static void main(String[] args) throws IOException {
        
    int id = 0;
    String password;
    int login = 0;
    

    System.out.println("\t\tWelcome!!\t\t");

    //menus.main_menu();

    //int choice = Integer.parseInt(input.nextLine());
    int choice;

 
    do{
        menus.main_menu();

        choice = menus.choiceInput();

        if(choice == 1){

            id = menus.IntInput("Enter your id: ");

            password = menus.StringInput("Enter your password: ");

            login = person.login(id,password);

            if(login == -1)
                System.out.println("Invalid id or password!\nPlease try agian.");
        }        
        
    
 

        if(login == 1){
            do{
                login = -1;
                
                admin m = new admin(id);

                menus.adminMenu();

                choice = menus.choiceInput();

                switch(choice){

                    case 1://main
                        do{
                            
                            menus.adminAddMenu();

                            choice  = menus.choiceInput();

                    switch (choice) {
                        case 1:
                            {
                                String name =  menus.StringInput("Enter the new student full name: ");
                                
                                student std = new student(name);
                                m.addUser(std);
                                
                                System.out.println("Student added!");
                                break;
                            }
                        case 2:
                            {
                                String name = menus.StringInput("Enter the new instructor full name: ");
                                
                                instructor ins = new instructor(name);
                                m.addUser(ins);
                                
                                System.out.println("instructor added!");
                                break;
                            }
                    
                        case 3:{
                            String name = menus.StringInput("Enter the course name: ");
                            
                            double price = menus.DoubleInput("Enter the course price: ");
                            
                            int insId = menus.IntInput("Enter the course instructor id: ");
                            
                            String startDate = menus.StringInput("Enter the course start date (yyyy-mm-dd): ");
                            
                            String endDate = menus.StringInput(  "Enter the course end date   (yyyy-mm-dd): ");
                            
                            course c = new course(name,price,insId,startDate,endDate);
                            
                            if(m.addCourse(c))
                                System.out.println("Course added successfully.!");
                            else
                                System.out.println("Something went wrong!");
                            break;
                        }
                        case 4:
                            break;
                            
                        default:{
                            System.out.println("Invalid choice please try again");
                            break;
                        }
                    }
                            
                        }while(choice !=4);
                        

                        break;

                    case 2://main
                        
                        do{
                            menus.adminUpdateMenu();

                        choice  = menus.choiceInput();

                    switch (choice) {
                        case 1:
                            {
                                int stdId = menus.IntInput("Enter the student id: ");

                                String stdName = menus.StringInput("Enter the student new full name: ");

                                String stdEmail = menus.StringInput("Enter the student new full email: ");
                                
                                student std = new student(stdId);
                                std.setName(stdName);
                                std.setEmail(stdEmail);
                                
                                boolean updated = m.updateUserInfo(std);
                                
                                if(updated)
                                    System.out.println("Student updated!");
                                else
                                    System.out.println("Something went wrong please try again!");
                                break;
                            }
                        case 2:
                            {
                                int insId = menus.IntInput("Enter the instructor id: ");

                                String insName = menus.StringInput("Enter the instructor new full name: ");

                                String insEmail = menus.StringInput("Enter the instructor new full email: ");
                                
                                instructor ins = new instructor(insId);
                                ins.setName(insName);
                                ins.setEmail(insEmail);
                                
                                boolean updated = m.updateUserInfo(ins);
                                
                                if(updated)
                                    System.out.println("instructor updated!");
                                else
                                    System.out.println("Something went wrong please try again!");
                                break;
                            }
                    
                        case 3:
                            String cId = menus.StringInput("Enter the course ID: ");
                            
                            course c = new course(cId);
                            
                            double price = menus.DoubleInput("Enter the course new price: ");
                            
                            int insId = menus.IntInput("Enter the new instructor for this course: ");
                            
                            c.setPrice(price);
                            c.setInstructorId(insId);
                            
                            m.updateCourse(c);
                            
                            break;
                            
                        case 4:
                            break;
                            
                        default:
                            System.out.println("Invalid choice please try again");
                            break;
                    }
                        }while(choice !=4);
                        
                        break;

                    case 3://main
                        
                        do{
                            menus.adminDeleteMenu();

                        choice  = menus.choiceInput();

                    switch (choice) {
                        case 1:
                            {
                                int stdId = menus.IntInput("Enter the student id: ");
                                boolean deleted = m.deleteStudent(stdId);
                                
                                if(deleted)
                                    System.out.println("Student deleted!");
                                else
                                    System.out.println("Student not found!");
                                
                                break;
                            }
                        case 2:
                            {
                                int insId = menus.IntInput("Enter the instructor id: ");
                                
                                boolean deleted = m.deleteInstructor(insId);
                                
                                if(deleted)
                                    System.out.println("Instructor deleted!");
                                else
                                    System.out.println("Instructor not found!");
                                
                                break;
                            }
                    
                        case 3:
                            
                            String cId = menus.StringInput("Enter the course ID: ");
                            
                            if(m.deleteCourse(cId))
                                System.out.println("Course deleted!");
                            else
                                System.out.println("Something went wrong!");
                            
                            break;
                            
                        case 4:
                            break;    
                            
                        default:
                            System.out.println("Invalid choice please try again");
                            break;
                    }
                        }while(choice!=4);
                       
                        break;


                    case 4://main
                        String students =  m.showStudents();

                        menus.adminShowStudents(students);
                        
                        break;

                    case 5://main
                        String instructors =  m.showInstructors();

                        menus.adminShowInstructors(instructors);

                        break;

                    case 6://main
                        System.out.println(m.toString());
                        break;
             
                    case 7://main
                        break;    
                    default:
                        System.out.println("Invalid choice please try again");
            }
          }while(choice != 7);

              }//end of if(login)
        else if(login == 2){
            
                login = -1;
                int student_choice;
                String courseID ="";
                String survey   ="";
                String name     ="";
                String email    ="";
                String pass     ="";
                
              do{
                   student stu=new student(id);

                    menus.student_menu();
                    student_choice=menus.IntInput();
                    switch (student_choice) {
                        case 1:
                            System.out.println(stu.getAllCourses());
                            break;
                        case 2:
                            String id_string = Integer.toString(id);

                            System.out.println(stu.getMyCourses());
                            break;
                        case 3:
                            courseID=menus.StringInput("enter course id:");

                            if(stu.registerCourses(courseID))
                                System.out.println("Course registered successfully.");

                            else
                                System.out.println("Couldn't register.!");


                            break;
                        case 4:
                            courseID=menus.StringInput("enter course id:");
                            if(stu.dropCourse(courseID))
                                System.out.println("Course dropped successfully.");
                            else
                                System.out.println("Something went wrong!");

                            break;
                        case 5:
                            courseID=menus.StringInput("enter course id:");
                            System.out.println("Garde: " + stu.getCourseGrade(courseID));
                            break;
                        case 6:
                            courseID=menus.StringInput("enter course id:");
                            survey=menus.StringInput("enter survey about course: ");
                            if( stu.makeSurvey(courseID, survey) )
                                System.out.println("Survey made successfully.");
                            else
                                System.out.println("Something went wrong!");
                            break;
                        case 7:
                            
                            System.out.println("Information:\n"+ stu.toString());
                            
                            name=menus.StringInput("enter new name:");
                            pass=menus.StringInput("enter new password:");

                            stu.setName(name);
                            stu.setPassword(pass);

                            if(stu.updateInfo())
                                System.out.println("Information updated successfully.");
                            else
                                System.out.println("Something went wrong!");

                            break;
                        case 8:
                            System.out.println(stu.coursesNearToStart());
                            break;
                        case 9:
                            System.out.println(stu.coursesNearToEnd());
                            break;
                        case 10:
                            break;
                        default:
                            break;
                           }//end of switch

              }while(student_choice != 10);
        
                    
            }
            else if(login == 3){
                login = -1;
                String courseId = "";
                int studentId = 0;
                int grade = 0;
                int insChoice;
                instructor ins = new instructor(id);
                do{
                    menus.instructor_menu();
                    insChoice = menus.IntInput();
                    
                    switch (insChoice) {
                        case 1 :
                            
                            courseId  = menus.StringInput("Enter the course ID: ");
                            
                            studentId = menus.IntInput("Enter the student ID: ");
                            
                            grade     = menus.IntInput("Enter the grade: ");
                   
                            if(ins.addGrade(courseId, studentId, grade) && grade != -1)
                                System.out.println("Grade added successfully!.");
                            else
                                System.out.println("Student or instructor informations' don't include this course.");
                                
                            break;
                            
                        case 2 :
                            System.out.println(ins.toString());
                            break;
                        
                        case 3 :
                            break;    
                    }
                    
                }while(insChoice != 3);
            }
        }while(choice != 2);
       
    
    }
}
    

        

