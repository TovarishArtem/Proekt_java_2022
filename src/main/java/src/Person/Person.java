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
    private String gender;
    private String homeAddress;
    private String birthDate;

     public Person(String fullname , String gender, String homeAddress, String birthDate) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }
 public String toString() {
     return String.format("%s: %s, %s, %s",this.fullName, this.gender, this.homeAddress, this.birthDate);
 }
}