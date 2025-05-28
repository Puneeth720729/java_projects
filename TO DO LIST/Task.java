class Task{
    int id;
    String description;
    boolean isCompleted;
    public Task(int id,String description){
        this.id=id;
        this.description=description;
        this.isCompleted=false;
    }
    public void display(){
        System.out.println("id: "+id);
        System.out.println("description: "+description);
        System.out.println("completed: "+(isCompleted?"yes":"No"));
        System.out.println("-----------------------");
    }
}
