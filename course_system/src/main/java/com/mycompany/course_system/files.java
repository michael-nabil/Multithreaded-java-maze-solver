/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course_system;

/**
 *
 * @author OWNER
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class files {
    
    
    
    
    
    public static ArrayList<String> readFile(String path) throws IOException{
        
         File file = new File(path);//my file
         
//         if(!file.exists())
//             return {""};
         
         ArrayList<String> content = new ArrayList<>();
         String curLine;
         try {
            Scanner fr = new Scanner(file);
            
            while(fr.hasNextLine())
            {   
                  curLine = fr.nextLine();
                  content.add(curLine);
            }   
            
            fr.close();
            
        } catch (FileNotFoundException ex) {
           
        }
        return content;
    }
    
    
    public static String readFile2(String path) throws IOException{
        
         File file = new File(path);//my file
         
//         if(!file.exists())
//             return {""};
         
         String content = "";
         String curLine;
         try {
            Scanner fr = new Scanner(file);
            
            while(fr.hasNextLine())
            {   
                  curLine = fr.nextLine();
                  content+= curLine + "\n";
            }   
            
            fr.close();
            
        } catch (FileNotFoundException ex) {
           
        }
        return content;
    }
    
    
    
    
    public static String readRecord(String path,int recordNo){
        
        File file = new File(path);//my file
         
         String curLine = "";

         try {
            Scanner fr = new Scanner(file);
            int i = 1;
            while(fr.hasNextLine()){  
                curLine = fr.nextLine();
                
                if(i == recordNo)
                    return curLine;
                i++;
            }
        } catch (FileNotFoundException ex) {
            return "";
        }
        return "";
         
}
    
    
    public static String readRecord(String path,String record){
        
        File file = new File(path);//my file
         
         String curLine = "";

         try {
            Scanner fr = new Scanner(file);
            while(fr.hasNextLine()){
                
                curLine = fr.nextLine();
                if(curLine.contains(record))
                        return curLine;
            
            }
        } catch (FileNotFoundException ex) {
            return "";
        }
        return "";
       
    }
    
    
    
    public static boolean recordExist(String path,String record){
        
        File file = new File(path);//my file
         
         String curLine = "";

         try {
             
            Scanner fr = new Scanner(file);
            
            while(fr.hasNextLine())
            {   
                  curLine = fr.nextLine();
                  if(curLine.equals(record))
                      return true;
            }
            
            fr.close();
            
        } catch (FileNotFoundException ex) {
            return false;
        }
        return false;
    }
    
    
    public static boolean addRecord(String path,String content){
        
        File file = new File(path);//my file
        
        try {
            FileWriter fw = new FileWriter(file, true); // if you want to append choose true else false
            
            if(file.length() != 0)
                fw.append("\n");   
            
            fw.append(content); 

            fw.close();
            
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    
    
    public static boolean deleteAllRecords(String path){
        
         File file = new File(path);//my file
        
        try {
            FileWriter fw = new FileWriter(file, false); //false to overwrite file
            
            fw.close();
            
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    
    public static boolean deleteFile(String path){
        
         File file = new File(path);//my file
         if(file.exists()){
             return file.delete();
         }
           
         return false;
        
    }
    
    
    
    public static boolean updateAllRecords(String path, String content){
        
         File file = new File(path);//my file
        
        try {
            FileWriter fw = new FileWriter(file, false); //false to overwrite file
            
            fw.append(content);
            
            fw.close();
            
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    
    public static boolean createFile(String name){
        
       try {
           
        File myObj = new File(person.defaultPath+name+".txt");

        myObj.createNewFile();
    } catch (IOException e) {
      return false;
    }
       return true;
    }
    
    
    public static int getLastId(String path){
        File file = new File(path);//my file
         
         String Line = "";

         try {
            Scanner fr = new Scanner(file);
            
            while(fr.hasNextLine())
            {   
                  Line = fr.nextLine();
            }    
            
            fr.close();
            
        } catch (FileNotFoundException ex) {
            
        }
         if(Line.length() != 0)
            return Integer.parseInt(Line);
         else
             return -1;
        
        
    }
    
    
    public static boolean isFileEmpty(String path){
        
        File file = new File(path);
        return file.length()== 0;
    }
    
    
    
    public static boolean deleteRecordByID(String path,String id) throws IOException{
        
        File file = new File(path);
        
        
        try{
            FileWriter fw = new FileWriter(file,true);
        
            ArrayList<String> records = readFile(path);

            deleteAllRecords(path);
            
            for(int i = 0;i < records.size();i++){

                if(records.get(i).contains(id))
                    continue;

                fw.append(records.get(i)+System.lineSeparator());

            }
          //fw.flush();
            fw.close();
        }catch(IOException e){
            return false;
        }
        return true;
    }
    
    
    public static boolean updateRecord(String path,String newRecord,int recordNo) throws IOException{
        
        File file = new File(path);
        
        
        try{
            FileWriter fw = new FileWriter(file,true);
        
            ArrayList<String> records = readFile(path);

            deleteAllRecords(path);
            
            for(int i = 0;i < records.size();i++){

                if(i+1 == recordNo)
                    fw.append(newRecord+System.lineSeparator());
                else
                    fw.append(records.get(i)+System.lineSeparator());
                
            }
            //fw.flush();
            fw.close();
        }catch(IOException e){
            return false;
        }
        return true;
    }
    
    
    public static int getRecordNumbers(String path){
        
        int numberOfRecords = 0;
        
        File file = new File(path);
        
        try{
            Scanner fr = new Scanner(file);
        
            while(fr.hasNextLine()){
                fr.nextLine();
                numberOfRecords++;
            }
                
                
        }catch(FileNotFoundException e){
            return -1;
        }
        return numberOfRecords;
    }
    
    
}
