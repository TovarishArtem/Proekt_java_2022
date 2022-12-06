package src.Person;

public class Student {
    private final String studentNameSurname;
   /* private final String group;*/
    /*Student(String studentNameSurname,String group){*/

    public Student(String studentNameSurname){
        this.studentNameSurname = studentNameSurname;
        /*this.group = group;*/
    }

    public String getStudentName() {
        return studentNameSurname;
    }

    /*public String getGroup() {
        return group;
    }*/
}