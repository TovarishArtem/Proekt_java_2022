package src.DataBase.ModelsForDB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "DBinfoStudent")
public class DBinfoStudent {
    public static final String NAME_COLUMN = "name";


    @DatabaseField(generatedId = true)
    private long studentId;

    @DatabaseField(  foreign = true, canBeNull = false,  foreignColumnName = "name", foreignAutoCreate = true )
    private DBgroups groupId;
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }


    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField()
    private String gender;

    @DatabaseField()
    private String homeAddress;

    @DatabaseField()
    private String birthDate;

    public DBinfoStudent() { }

    public DBinfoStudent(String name , String gender, String homeAddress, String birthDate, String groupId) {
        this.name = name;
        this.gender = gender;
        this.homeAddress = homeAddress;
        this.birthDate =  birthDate;
        this.groupId = new DBgroups(groupId);

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

    public DBgroups getGroupId() {
        return groupId;
    }

    public void setGroupId(DBgroups groupId) {
        this.groupId = groupId;
    }
    @Override
    public String toString() {
        return "DBinfoStudent{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", group='" + groupId + '\'' +
                ", gender=" + gender +
                ", homeAddress=" + homeAddress +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
