package src.DataBase.ModelsForDB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
public class DBEntity {
    public static final String NAME_COLUMN = "name";
    @DatabaseField(generatedId = true)
    private long studentId;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField()
    private String gender;

    @DatabaseField()
    private String homeAddress;

    @DatabaseField()
    private String birthDate;

    public DBEntity() { }

    public DBEntity(String name , String gender, String homeAddress, String birthDate) {
        this.name = name;
        this.gender = gender;
        this.homeAddress = homeAddress;
        this.birthDate =  birthDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {
        return "PlayerFollowersEntity{" +
                "playerId=" + studentId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", homeAddress=" + homeAddress +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
