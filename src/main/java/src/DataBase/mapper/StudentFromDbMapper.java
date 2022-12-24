package src.DataBase.mapper;

import src.DataBase.ModelsForDB.DBinfoStudent;
import src.Person.Person;

public class StudentFromDbMapper {
    public static Person map(DBinfoStudent student) {
        return new Person(student.getName(), student.getGender(), student.getHomeAddress(), student.getBirthDate());
    }
}
