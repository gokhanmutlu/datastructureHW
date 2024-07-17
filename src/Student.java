public class Student implements Comparable<Student> {
    private int id;
    private String name;
    private int mathGrade;
    private int dataGrade;

    public Student(int id, String name, int mathGrade, int dataGrade) {
        this.id = id;
        this.name = name;
        this.mathGrade = mathGrade;
        this.dataGrade = dataGrade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMathGrade() {
        return mathGrade;
    }

    public int getDataGrade() {
        return dataGrade;
    }

    //it finds the average of student's grades.
    public float overallAverage() {
        return (getDataGrade() + getMathGrade()) / 2;
    }

    // it compare student to another student's average grades.
    // if its greater than it return 1.
    // if its equal to the other average grade return 0.
    // if its less than other average grade it return -1.
    @Override
    public int compareTo(Student o) {

        if (this.overallAverage() > o.overallAverage()) {
            return 1;
        }else if (this.overallAverage() < o.overallAverage()){
            return -1;
        }else {
            return 0;
        }

    }

    @Override
    public String toString() {
        return "ID: " + id + "  Name: " + name + "       Math Grade: " + mathGrade + "       Data Grade: " + dataGrade + "      Overall Average: " + this.overallAverage();
    }


}
