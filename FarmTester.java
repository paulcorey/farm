import java.util.*;   
public class FarmTester {
   public static void main(String[] args) {
      int menuOpt=0;   
      Scanner kIn= new Scanner(System.in);
      Sheep mySheep = new Sheep();
       
      do  { 
         System.out.println("\t\tFarm Menu");
         System.out.println("\t1. Display all sheep");
         System.out.println("\t2. Search and display one sheep");
         System.out.println("\t3. Add a sheep");
         System.out.println("\t4. Delete a sheep");
         System.out.println("\t5. Change sheep weight");
         System.out.println("\t6. Fry some lamb chops");
         
         System.out.println("\t0. Exit the system");
         menuOpt=kIn.nextInt();
         
         switch(menuOpt)  {
         
            case 1://display all sheep
             
               ArrayList<Sheep> allSheep = mySheep.getAllSheep();
               System.out.println("View All Sheep");
               System.out.println("Number \tSheep name \tWeight \t\tKill Date");
               Iterator<Sheep> sheepIterator = allSheep.iterator();
               while(sheepIterator.hasNext())    {
                  Sheep DisplaySheep = sheepIterator.next();
                  System.out.println(DisplaySheep.getSNumber() + " \t" +  
                                          DisplaySheep.getSName() + " \t\t" +  
                                          DisplaySheep.getSWeight() + " \t\t" + 
                                          DisplaySheep.getSKillDate() );
               }
            
               break;
               
            case 2:// serch and display one sheep
               int searchSheepNo=0;
               System.out.println("\n\nEnter the sheep number you wish to view");
               searchSheepNo=kIn.nextInt();      
               Sheep SearchSheep = mySheep.getSheep(searchSheepNo);
               System.out.println(SearchSheep.getSNumber() + " \t" +  
                                          SearchSheep.getSName() + " \t\t" +  
                                          SearchSheep.getSWeight() + " \t\t" + 
                                          SearchSheep.getSKillDate() );
               break;
                
            case 3://add a new sheep
               System.out.println("\n\nEnter the new sheep values");
               System.out.print("Enter Sheep Number:\t");
               int nSheepNo=kIn.nextInt();
               kIn.nextLine(); // clear keyboard buffer
               System.out.print("Enter Sheep Name:\t");
               String nSheepName=kIn.nextLine();
               System.out.print("Enter Sheep Weight:\t");
               int nSheepWeight=kIn.nextInt();
               kIn.nextLine();//clear keyboard buffer
               System.out.print("Enter the date to be killed (YYYY-MM-DD)");
               String newDate = kIn.nextLine();
               Sheep newSheep = new Sheep(nSheepNo,nSheepName,nSheepWeight,newDate);
               int addStatus = mySheep.add(newSheep);
               if (addStatus==1)
                  System.out.println("record successfully added to database");
               break;   
               
            case 4://delete a sheep
               int delSheepNo=0;
               System.out.print("\n\nEnter the sheep number you wish to delete:");
               delSheepNo=kIn.nextInt(); 
               int delStatus = mySheep.delete(delSheepNo);
               if (delStatus==1)
                  System.out.println("Record successfully deleted from database");          
               break;
               
            case 5://change a sheep weight
               int changeSheepNo=0;
               int newWeight=0;
               int changeStatus=0;
               System.out.print("\n\nEnter the sheep number you wish to change weight: ");
               changeSheepNo=kIn.nextInt(); 
               System.out.print("\n\nEnter the new weight for that sheep: ");
               newWeight=kIn.nextInt(); 
               changeStatus=mySheep.changeWeight(changeSheepNo, newWeight);
               if (changeStatus==1)
                  System.out.println("Record successfully deleted from database");
               break;
         
         }
      } while (menuOpt!=0);          
   }
}