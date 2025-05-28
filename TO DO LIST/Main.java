import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class Main{
    public static void loadTasksFromFile(ArrayList<Task> list){
        try(BufferedReader reader=new BufferedReader(new FileReader("tasks.txt"))){
            String line;
            while((line=reader.readLine())!=null){
                String[] parts=line.split(",");
                if(parts.length==3){
                    int id=Integer.parseInt(parts[0]);
                    String description=parts[1];
                    boolean isCompleted=Boolean.parseBoolean(parts[2]);
                    Task task=new Task(id,description);
                    task.isCompleted=isCompleted;
                    list.add(task);
                }
            }
        }catch(IOException e){
            System.out.println("error adding tasks:"+e.getMessage());
        }
    }
    public static void saveTasksToFile(ArrayList<Task> list){
        try(BufferedWriter writer =new BufferedWriter(new FileWriter("tasks.txt"))){
            for(Task task:list){
                writer.write(task.id+","+task.description+","+task.isCompleted);
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println("Error saving tasks:"+e.getMessage());
        }
    }
    public static void main(String[] args){
        ArrayList<Task> list=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        int choice;
        int taskId=1;
        loadTasksFromFile(list);
        if(!list.isEmpty()){
            taskId=list.get(list.size()-1).id+1;
        }
        while(true){
           System.out.println("\n===== TO-DO LIST MENU =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 
            switch(choice){
                case 1:
                    System.out.println("enter task name to be added:");
                    String s=sc.nextLine();
                    Task newtask = new Task(taskId,s);
                    list.add(newtask);
                    taskId++;
                    break;
                case 2:
                    if(list.isEmpty()){
                        System.out.println("No tasks to do");
                    }else{
                        for(Task task:list){
                            task.display();
                        }
                    }
                    break;
                case 3:
                    System.out.println("enter task id to complete");
                    int toComplete=sc.nextInt();
                    boolean found =false;
                    sc.nextLine();
                    for(Task task:list){
                        if(task.id==toComplete){
                            task.isCompleted=true;
                            System.out.println("Task "+toComplete+ " marked as complete");
                            found=true;
                            break;
                            
                        }
                    }
                    if(!found){
                        System.out.println("Task not found");
                    }
                    break;
                case 4:
                    System.out.println("enter the task id to be deleted");
                    int deleteId=sc.nextInt();
                    sc.nextLine();
                     found=false;
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).id==deleteId){
                            list.remove(i);
                            System.out.println("task with id"+deleteId+" removed successfully");
                            found=true;
                            break;
                        }
                    }
                    if(!found){
                        System.out.println("task not found");
                    }
                    break;
                case 5:
                saveTasksToFile(list);
                    System.out.println("terminating the program---");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. please try again");
            }
        }
    }
}
