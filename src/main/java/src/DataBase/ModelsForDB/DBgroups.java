package src.DataBase.ModelsForDB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "DBgroups")
public class DBgroups {

    public static final String NAME_COLUMN = "name";

    @DatabaseField( generatedId = true )
    private Integer id;


    @DatabaseField(canBeNull = false)
    private String name;


    public DBgroups() { }

    public DBgroups(  String name ) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String toString() {
        return "DBinfoStudent{" +
                "Id=" + id +
                ", name='" + name
                ;
    }
}
