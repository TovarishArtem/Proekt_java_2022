package src.Person;

import com.vk.api.sdk.objects.base.City;
import com.vk.api.sdk.objects.base.Sex;

import java.time.LocalDate;

enum Gender{
    MALE,
    FEMALE
}

public class Person {
    private String fullName;
    private Sex gender;
    private City homeAddress;
    private String birthDate;

     public Person(String fullname , Sex gender, City homeAddress, String birthDate) {
       /* public Person(String fullname) {*/


        this.fullName = fullname;
        this.gender = gender;
        this.homeAddress = homeAddress;
        this.birthDate = birthDate;
    }


    public String addGender(Gender gender) {
        return gender.toString().toLowerCase();
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public City getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(City homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }
 public String toString() {
     return String.format("%s: %s, %s, %s",this.fullName, this.gender, this.homeAddress, this.birthDate);
 }
}