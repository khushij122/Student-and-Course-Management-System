public class Student {
	//variables declared
	int id;
	String first;
	String last;
	String level;
	boolean active;
	//constructor
	Student()	{
		this.active = true;
	}	
	//getter methods 
	public String getFirst() {
		return first;
	}
	public String getLast() {
		return last;
	}
	public String getName() {
		return first + " " + last;
	}
	public String getLevel() {
		return level;
	}
	public boolean getActive() {
		return active;
	}
	public int getId() {
		return id;
	}
	public String getStatus() {
		return active? "Active":"Not Active";
	}
	//print methods
	public String printStudentAdded() {
		return "\n" + first+" "+last+" has been added as a "+level+" with ID " + id + "\n";
	}
	public void printStudent() {
		System.out.println("\n" + this.getFirst()+" "+this.getLast()+"\nID: "+this.getId()+"\nLevel: "+this.getLevel()+"\nStatus: "+this.getStatus()+ "\n");
		System.out.println();
	}
	public void deactivated() {
		System.out.println("\n" +getName()+" has been deactivated" + "\n");
		
	}
}
//subclass for students with jobs
class Student_Employee extends Student{
	//variables declared
	String job;
	String jobtype;
	//print methods
	public String printjobConfirmation() {
		return getName()+" has been assigned "+job+" "+jobtype+" job.";
	}
	public String printEmployee() {
		return getName()+"\nID - "+getId()+"\n"+ job+ " " +jobtype+"\n";
	}
}
