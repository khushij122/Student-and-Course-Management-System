//imports
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Khushi Jogani
 * ITSS 3312.002
 */
//runner class
public class KhushiJogani_002_Project2 {
	//main method
	public static void main(String[] args) throws IOException {
		//declared variables
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		int studentInd = 0;
		int empInd = 0;
		//ask for number of students that will be in the system
		System.out.println("\n\nThis system will allow you to manage students and courses. Let’s start with the number of tudents this system will have:");
		//make an array of student and student_employee objects with the size inputed by the user
		Student[] students = new Student[scan.nextInt()];
		Student_Employee[] employees = new Student_Employee[students.length];
		//print system menu and take input 
		systemMenu();
		int userChoice = scan.nextInt();
		//run a while loop till user wants to exit
		while(userChoice != 0) {
			//run scenario if user chooses 1
			if(userChoice == 1) {
				//print menu and get user input
				studentMenu();
				int studentChoice = scan.nextInt();
				//stay in sms till user choose 0 and exits
				while(studentChoice != 0) {
					scan.nextLine();
					//run scenario if user chooses 1
					if(studentChoice==1) {
						//make a new student, print and take input and store it in the new student's variables
						Student newStudent = new Student();
						System.out.println("Enter first name:");
						newStudent.first = scan.nextLine();
						System.out.println("Enter last name:");
						newStudent.last = scan.nextLine();
						System.out.println("Enter the level of the student:");
						newStudent.level = scan.nextLine();
						newStudent.id = rand.nextInt(100);
						//store the student object at an index being tracked by studentInd
						students[studentInd] = newStudent;
						//print student added statement
						System.out.println(students[studentInd].printStudentAdded());
						//increment the index variable
						studentInd++;
					}
					//run scenario if user chooses 2
					if(studentChoice == 2) {
						//take input on the id that needs to be deactivated
						System.out.println("Enter the ID for the student you want to deactivate:");
						int deactivate = scan.nextInt();
						//loop through the array of students to match the id inputed with ids of the student
						for(int i = 0; i < students.length; i++) {
							if(students[i]!= null) {
								//if a match is found
								if(students[i].id==deactivate) {
									//change active to false to deactivate 
									students[i].active = false;
									//print
									students[i].deactivated();
								}
							}
						}
					}
					//run scenario if user chooses 3
					if(studentChoice == 3) {
						//make a new copy of students array that gets sorted by sortByFName method
						Student[] sort = sortByFName(students);
						//loop through the new array "sort"
						for(int i = 0; i < sort.length; i++) {
							//if it's not null print each object
							if(sort[i]!= null) {
								sort[i].printStudent();
								}
							}
						}
					//run scenario if user chooses 4
					if(studentChoice == 4) {
						//take input in for the id to be searched
						System.out.println("Enter the ID for the student:");
						int search = scan.nextInt();
						//variable to track if a student is found
						boolean found = false;
						//loop through students
						for(int i = 0; i < students.length; i++) {
							//if the array's value at index i is not null then check if it's a match
							if(students[i]!= null) {
								if(students[i].id==search) {
									//print the student and change the status of found variable to be true
									students[i].printStudent();		
									found = true;
								}
							}
						}
						//if the student is not found then print no results matched
						if(!found)
							System.out.println("\nNo results matched!");
					}
					//run scenario if user chooses 5
					if(studentChoice == 5) {
						//make a new student_employee obj
						Student_Employee e = new Student_Employee();
						//take input and search for that student's id within the array of students 
						System.out.println("Enter student ID: ");
						//store the index of the match into a variable and store all data relating to this employee in the student object at the index
						int index = findStudentIndex(Integer.valueOf(scan.nextLine()),students);
						e.first = students[index].getFirst();
						e.last = students[index].getLast();
						e.id = students[index].getId();
						System.out.println("Enter job");
						e.job = scan.nextLine();
						System.out.println("Enter job type");
						e.jobtype = scan.nextLine();
						//print confirmation
						System.out.println(e.printjobConfirmation());
						//store the new obj at empInd index of employees array
						employees[empInd] = e;
						//increment the empInd by 1
						empInd++;
					}
					//run scenario if user chooses 6
					if(studentChoice == 6) {
						//loop through employees
						for(int i = 0; i < employees.length; i++) {
							//print each if not null
							if(employees[i]!=null)
								System.out.println(employees[i].printEmployee());
						}
					}
					//print student Menu and take input
					studentMenu();
					studentChoice = scan.nextInt();
				}
			}
			//run scenario if user chooses 2
			if(userChoice == 2) {
				//start CMS
				//print and take input of coursemenu
				courseMenu();
				int courseChoice = scan.nextInt();
				//make a file obj for courses data
				File coursefile = new File("courses.txt");
				//make a file obj for courses assignment data
				File courseassignment = new File("course_assignment.txt");
				//run the system till the user enters 0 to exit
				while(courseChoice != 0) {
					//run scenario if the user enters 1
					if(courseChoice == 1) {
						//objects to write to file objects
						FileWriter fw = new FileWriter(coursefile,true);
				    	BufferedWriter bw = new BufferedWriter(fw);
				    	PrintWriter pw = new PrintWriter(bw);
				    	//take input on id and name of the course
						scan.nextLine();
						System.out.println("Enter course ID: ");
						String courseId = scan.nextLine();
						int id = Integer.parseInt(courseId);
						System.out.println("Enter course name: ");
						String courseName = scan.nextLine();
						//if the course doesn't exist 
						if(!courseExists(coursefile,id)) {
							//make a course object and store the inputs taken to the course object
							Course c = new Course();
							c.c_id = id;
							c.c_name = courseName;
							//print the course object data to the file 
							pw.println("ID: "+id);
							pw.println("Course Name: "+courseName);
							pw.println();
							pw.close();
							//print confirmation
							System.out.println("Confirmation: New course "+c.c_id+" has been added");
}
						//if course already exists print:
						else {
							System.out.println("Course already exists!");
						}
					}
					//run scenario if the user enters 2
					if(courseChoice == 2) {
						try {
							//objects to write to file objects
							FileWriter fw = new FileWriter(courseassignment,true);
					    	BufferedWriter bw = new BufferedWriter(fw);
					    	PrintWriter writer2= new PrintWriter(bw);
					    	//take input on student id & id and name of the course
							System.out.println("Enter student ID: ");
							int student_id = scan.nextInt();
							System.out.println("Enter course ID: ");
							int course_id = scan.nextInt();
							//print the details to the file
							writer2.println(findStudentName(student_id, students)+"\n ID - "+ student_id+"\n Courses: "+course_id);
							writer2.close();
							//print confirmation
							System.out.println("Confirmation: "+findStudentName(student_id, students)+" has been assigned course "+course_id);
							}
						catch (FileNotFoundException e) {
							System.out.println("An error occurred.");
							e.printStackTrace();
						}
					}
					//run scenario if the user enters 3
					if(courseChoice == 3) {
						//Display students with assigned courses. You will have to read from the file
						Scanner sc = new Scanner(courseassignment);
						while (sc.hasNextLine()) {
						      System.out.println(sc.nextLine());
						  }
					}
					//print course menu and take input
				courseMenu();
				courseChoice = scan.nextInt();
			}
				//run scenario if the user enters 0
				if(courseChoice == 0) {
					//print bye
					System.out.println("Good Bye!!!");
				}
		}
			//print menu and take input
			systemMenu();
			userChoice = scan.nextInt();
		}
		//close scanner
		scan.close();
	}
	//method to check if course exists in file
	public static boolean courseExists(File f, int id) {
		try {
			 Scanner sc = new Scanner(f);
	    while (sc.hasNextLine()) {
	      if(sc.nextLine().contains(Integer.toString(id))) {
	    	  return true;
	      }
	     }
	  } catch (FileNotFoundException e) {
			e.printStackTrace();
		}	 
	    return false;
	}
	//method to sort the students by their first name
	public static Student[] sortByFName(Student[] students) {
		Student temp = new Student();
		for(int i = 0; i < students.length-1; i++) {
	         for (int j = i+1; j<students.length; j++) {
	        	if(students[i]!= null && students[j]!=null) {
	            if(students[i].first.compareTo(students[j].first)>0) {
	               temp = students[i];
	               students[i] = students[j];
	               students[j] = temp;
	            	}
	        	}
	         }
	      }
		return students;
	}//method to print student menu
	public static void studentMenu() {
		System.out.println("\n***Welcome to Student Management System***\n"
				+ "Press '1' to add a student\n"
				+ "Press '2' to deactivate a student\n"
				+ "Press '3' to display all students\n"
				+ "Press '4' to search for a student by ID\n"
				+ "Press '5' to assign on-campus job\n"
				+ "Press '6' to display all students with on-campus jobs\n"
				+ "Press '0' to exit the SMS\n");
	}//method to print course menu	
	public static void courseMenu() {
		System.out.println("Press ‘1’ to add a new course\nPress ‘2’ to assign student a new course\nPress ‘3’ to display student with assigned courses\nPress ‘0’ to exit CMS");
	}//method to print system menu	
	public static void systemMenu() {
		System.out.println("\n\nWelcome to Student and Course Management System!");
		System.out.println("Press '1' for Student Management System (SMS)\n"
				+ "Press '2' for Course Management System (CMS)\n"
				+ "Press '0' to exit\n");
		
	}//method to find student's name by their id
	public static String findStudentName(int id, Student[] students) {
		for(int i = 0; i < students.length; i++) {
			if(students[i].id == id) {
				return students[i].getName();
			}
		}
		return null;
	}
	//method to find student's index in the array by their id
	public static int findStudentIndex(int id, Student[] students) {
		for(int i = 0; i < students.length; i++) {
			if(students[i].getId() == id) {
				return i;
			}
		}
		return -1;
	}
}