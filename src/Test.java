import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        LinkedList<Student> studentLinkedList = getStudentToList(); // it takes the information from student.txt and adding to the linked list.
        Scanner scanner = new Scanner(System.in);

        // There is a menu to do following instructions. it takes input from user what it want to access. Also it continues until user enter No(N).
        String YorN = "Y";
        while (YorN.toUpperCase().equals("Y")) {  // it checks if user enter lower letter.

            System.out.println("1) Display list.\n" +
                    "2) Display the students sorted by their overall average. " +
                    "\n3) Display the student names sorted by their data structure grades.\n" +
                    "4) Display the math average.\n" +
                    "5) Display the student(s) who has the lowest Data Structures grade. \n" +
                    "6) Delete the student whose id is entered by the user.\n" +
                    "7) Insert a new student. \n" +
                    "8) Display the student count.\n" +
                    "9) Display the successful students for each course.");
            System.out.println();

            System.out.print("What do you want to access: ");
            int input = scanner.nextInt();

            // Instructions are here with if conditions.
            if (input < 1 || input > 10)
                System.out.println("You can only enter numbers 1-10.");
            System.out.println();

            if (input == 1) {
                studentLinkedList.display();

            } else if (input == 2) {
                studentLinkedList.selectionSorted();
                studentLinkedList.display();

            } else if (input == 3) {
                studentLinkedList = sortedDataGrade(studentLinkedList);
                studentLinkedList.display();

            } else if (input == 4) {
                System.out.println("Mathematic Average: " + mathAverage(studentLinkedList));

            } else if (input == 5) {
                studentLinkedList = sortedDataGrade(studentLinkedList);  //After students sorted by their data grades.
                Node<Student> iter = studentLinkedList.getHead();        //With a loop it finds minimum grade and find who has minimum grade it lists them.
                while (iter.next != null) {
                    iter = iter.next;
                }
                int min = iter.val.getDataGrade();
                iter = studentLinkedList.getHead();
                while (iter != null) {
                    if (iter.val.getDataGrade() == min)
                        System.out.println(iter);
                    iter = iter.next;
                }

            } else if (input == 6) {  // it takes an id from user and deletes student from list.
                System.out.print("ID: ");
                int id = scanner.nextInt();
                deleteStudent(id, studentLinkedList);
                //studentLinkedList.display();

            } else if (input == 7) {
                addStudent(studentLinkedList);


            } else if (input == 8) {
                System.out.println("Total student: " + studentLinkedList.numberOfNodes());

            } else if (input == 9) {
                successfulStudent(studentLinkedList);

            }
            System.out.println();
            System.out.println();

            System.out.print("Do you want to continue yes(Y) or no(N): ");
            YorN = scanner.next();
        }
        System.out.println("Thanks for using the program.");

    }


    // It reads the "student.txt" as line by line and splits to the empty string.
    // Also it adds the student to the linked list and return a linked list who has students.
    public static LinkedList getStudentToList() {
        LinkedList<Student> studentLinkedList = new LinkedList<>();

        File student = new File("students.txt");

        try {
            Scanner reader = new Scanner(student);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                //System.out.println(line);
                String[] parts = line.split(" ");
                Student newStudent = new Student(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                studentLinkedList.addEnd(newStudent);
                //userArrayList.add(newUser);
                //System.out.println(userArrayList);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return studentLinkedList;
    }
    // it takes a parameter to add new student to the linked list.
    // Also it checks the inputs with while loops until user enter valid input.
    // After all, it adds the linked list new student with taken inputs.
    public static void addStudent(LinkedList studentLinkedList) {
        Scanner input = new Scanner(System.in);
        try {
            int id;
            do {
                System.out.print("Enter ID: ");
                id = input.nextInt();
                if (isIncluded(studentLinkedList, id))
                    System.out.println("Please enter a valid id");

            } while (isIncluded(studentLinkedList, id));
            System.out.print("Enter name: ");
            String name = input.next();
            int mathGrade;
            do {
                System.out.print("Enter math grade: ");
                mathGrade = input.nextInt();
                if (mathGrade < 0 || mathGrade > 100)
                    System.out.println("Please enter valid grade");
            } while (mathGrade < 0 || mathGrade > 100);
            int dataGrade;
            do {
                System.out.print("Enter data grade: ");
                dataGrade = input.nextInt();
                if (dataGrade < 0 || dataGrade > 100)
                    System.out.println("Please enter valid grade");
            } while (dataGrade < 0 || dataGrade > 100);
            studentLinkedList.addEnd(new Student(id, name, mathGrade, dataGrade));
            System.out.println("Student has been added!");
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid student informations!");
        }

        //studentLinkedList.display();
        //System.out.println("id: " + id + " name: " + name + " mathGrade: " + mathGrade + " dataGrade: " + dataGrade);


    }
    // It takes linked list as a parameter.
    // It travels in linked list sum up student's math grade and also increasing i in every student to find average math grade.
    // returning average.
    public static float mathAverage(LinkedList studentLinkedList) {
        Node<Student> head = studentLinkedList.getHead();
        Node<Student> iter = head;
        float sum = 0;
        float i = 0;
        while (iter != null) {

            sum += iter.val.getMathGrade();
            i++;
            iter = iter.next;
        }
        if (i == 0) {
            System.out.println("There is no math grade in list");
            return 0;
        } else
            return sum / i;
    }
    // It takes linked list as a parameter.
    // It travels in linked list sum up student's data grade and also increasing i in every student to find average data grade.
    // returning average.
    public static float dataAverage(LinkedList studentLinkedList) {
        Node<Student> head = studentLinkedList.getHead();
        Node<Student> iter = head;
        float sum = 0;
        float i = 0;
        while (iter != null) {
            sum += iter.val.getDataGrade();
            i++;
            iter = iter.next;
        }
        return sum / i;

    }

    // It takes linked list who has students.
    // It travels the student and if they grades greater than average grade it adds students to new list
    // After that printing who is successful for each course.
    public static void successfulStudent(LinkedList studentLinkedList) {

        LinkedList<Student> successfulStudentListOfMath = new LinkedList<>();
        LinkedList<Student> successfulStudentListOfData = new LinkedList<>();
        Node<Student> head = studentLinkedList.getHead();
        Node<Student> iter = head;
        while (iter != null) {
            if ((iter.val.getMathGrade() > mathAverage(studentLinkedList))) {
                successfulStudentListOfMath.addEnd(iter.val);
            }
            if (iter.val.getDataGrade() > dataAverage(studentLinkedList)) {
                successfulStudentListOfData.addEnd(iter.val);
            }
            iter = iter.next;
        }
        System.out.println("Student who passed math: ");
        successfulStudentListOfMath.display();
        System.out.println();
        System.out.println("Student who passed data: ");
        successfulStudentListOfData.display();


    }
    // It takes student list and check the grades and sorting the linked list.
    public static LinkedList<Student> sortedDataGrade(LinkedList studentLinkedList) {
        Node<Student> head = studentLinkedList.getHead();
        LinkedList<Student> newll = new LinkedList<>();
        while (head != null) {
            Node<Student> iter = head;
            Node<Student> max = head;
            while (iter != null) {
                if (max.val.getDataGrade() < iter.val.getDataGrade()) {
                    max = iter;
                }
                iter = iter.next;
            }
            newll.addEnd(max.val);
            Student temp = max.val;
            max.val = head.val;
            head.val = temp;
            head = head.next;
        }
        return newll;
    }
    // it takes id from user, checking every student in linked list. if it finds the id in linked list it remove from list.
    // if it could not find the id in linked list. it gives a feedback.
    public static void deleteStudent(int id, LinkedList studentLinkedList) {
        Node<Student> head = studentLinkedList.getHead();
        if (head.val.getId() == id) {
            studentLinkedList.setHead(head.next);
            System.out.println("Student has been deleted.");
            return;
        }
        Node<Student> iter = head;
        while (iter.next != null) {
            if (iter.next.val.getId() == id) {
                iter.next = iter.next.next;
                System.out.println("Student has been deleted.");
                return;
            }
            iter = iter.next;
        }
        System.out.println("Student not found.");
    }
    // it checks the linked list there is a student which has a same id with adding new student to the list.
    // if there is, it return false. if there is not, it return false.
    public static boolean isIncluded(LinkedList studentLinkedList, int id) {
        Node<Student> student = studentLinkedList.getHead();
        while (student != null) {
            if (student.val.getId() == id)
                return true;
            student = student.next;
        }
        return false;


    }


}
