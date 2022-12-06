package src.Person;

import java.time.LocalDate;

enum Gender{
    MALE,
    FEMALE
}

public class Person {
    private String fullName;
    private Gender gender;
    private String homeAddress;
    private String birthDate;

     public Person(String fullname ,String gender, String homeAddress, String birthDate) {
       /* public Person(String fullname) {*/


        this.fullName = fullname;
        this.gender = getGender(gender);
        this.homeAddress = homeAddress;
        this.birthDate = birthDate;
    }

    public String getFullName() {return this.fullName;}
    public Gender getGender() {return this.gender;}
    public String getHomeAddress() {return this.homeAddress;}
    public String getBirthDate() {return this.birthDate;}
    public Gender getGender(String gender) {
        gender = gender.toLowerCase();
        return (gender == "male") ? Gender.MALE : Gender.FEMALE;
    }
    public String addGender(Gender gender) {
        return gender.toString().toLowerCase();
    }

    public String toString(Person person){
        return  String.format ("%s: %s, %s, %s", person.getFullName(), person.getGender(), person.getHomeAddress(), person.getBirthDate());

    }
}