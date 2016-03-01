//Author: Paul Corey
//Date: February 2014
//Decsription: Sample UML Style implementation of relational database access (mySql)  
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Sheep {
   //class attributes
   private int sNumber;
   private String sName;
   private int sWeight;
   private String sKillDate;
   
   //sql and database variables
   private String url = "jdbc:mysql://localhost:3306/";
   private String dbName = "farm";
   private String driver = "com.mysql.jdbc.Driver";
   private String userName = "root";
   private String password = "password";
   private Statement statement = null;   
   private ResultSet resultSet = null;

   //Constructors
   public Sheep(int sNumIn, String sNamIn,int sWeigIn, String sKillDateIn)   { 
      sNumber=sNumIn;
      sName=sNamIn;
      sWeight=sWeigIn;
      sKillDate=sKillDateIn;
   }
   public Sheep()   {
   }
 
   //Accessor methods   
   public int getSNumber() {
      return sNumber;
   }

   public String getSName() {
      return sName;
   }

   public int getSWeight() {
      return sWeight;
   }

   public String getSKillDate() {
      return sKillDate;
   }
  
// database access and update methods  
       
/// add /////////////////////////////////////////////////////////////////////////////////////// 
   public int add(Sheep sIn)   { 
      int status=0;
      String sqlString="insert into farm.sheep(snumber,sname,sweight,skilldate) values("+
                                          sIn.getSNumber() + ",\"" + sIn.getSName() + "\"," +
                                          sIn.getSWeight() + ",\'"+ sIn.getSKillDate()+ "\'"+ ")";
    status = databaseUpdate(sqlString);
    return status;
   }

 
 
/// changeWeight/////////////////////////////////////////////////////////////////////////////// 
   public int changeWeight(int sheepNoIn, int weightIn)   {
      int status = 0;
      String sqlString="update farm.sheep set sweight="+weightIn
                                         + " where snumber="+sheepNoIn;
      status = databaseUpdate(sqlString);                                           
               
      return status;
   }

 
 
 /// delete/////////////////////////////////////////////////////////////////////////////// 
   public int delete(int sheepNoIn)   {
      int status=0;  
      String sqlString= "delete from farm.sheep where snumber=" + sheepNoIn;     
      status = databaseUpdate(sqlString);                                        
      return status;
   }

 
 /// getAllSheep//////////////////////////////////////////////////////////////////////////////////////
   public ArrayList<Sheep> getAllSheep()      { 
      ArrayList<Sheep> AllSheep = new ArrayList<Sheep>();
      try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn = DriverManager.getConnection(url+dbName,userName,password);
         statement=conn.createStatement();
         resultSet=statement.executeQuery("select * from farm.sheep");
         
         while ( resultSet.next() )    {
            Sheep nextSheep = new Sheep(resultSet.getInt("snumber"), resultSet.getString("sname"),
                                         resultSet.getInt("sweight"), resultSet.getDate("skilldate").toString() );                
            AllSheep.add(nextSheep);
         }
         conn.close();
      } 
      catch (Exception e) {
         e.printStackTrace();
      }
      return AllSheep;     
   }

 
 /// getSheep////////////////////////////////////////////////////////////////////////////////////// 
   public Sheep getSheep(int searchNo)
   {
      Sheep foundSheep= new Sheep();
      try{
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn = DriverManager.getConnection(url+dbName,userName,password);
         statement=conn.createStatement();
         resultSet=statement.executeQuery("select * from farm.sheep where farm.sheep.sNumber=" + searchNo);
                 
         while ( resultSet.next() )      {
            foundSheep = new Sheep(resultSet.getInt("snumber"), resultSet.getString("sname"),
                                   resultSet.getInt("sweight"), resultSet.getDate("skilldate").toString() );                
         }   
         conn.close();
      } 
      catch (Exception e) {
         e.printStackTrace();
      }  
      return foundSheep;
   }

 
 
   private int databaseUpdate(String sqlUpdate)
   {
      int status=0;
   
      try{
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn = DriverManager.getConnection(url+dbName,userName,password);
         statement=conn.createStatement();
         status=statement.executeUpdate(sqlUpdate);
         conn.close(); 
      }       
       
      catch (Exception e) {
         e.printStackTrace();
      }   
      return status;
   }
 
 
 
 
}