package src.DataBase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import src.DataBase.ModelsForDB.DBgroups;
import src.DataBase.ModelsForDB.DBinfoStudent;
import src.Person.Person;
import src.Person.Student;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class DBRepository {
    private final String URL = "jdbc:sqlite:rawdata/base.db";

    private ConnectionSource connectionSource = null;

    private Dao<DBinfoStudent, String> studentDao = null;
    private Dao<DBgroups, String> groupDao = null;

    public void connect() throws SQLException {
        connectionSource = new JdbcConnectionSource(URL);

        studentDao = DaoManager.createDao(connectionSource, DBinfoStudent.class);
        groupDao =  DaoManager.createDao(connectionSource, DBgroups.class);
    }

    public void createTable() throws SQLException {
        TableUtils.createTable(connectionSource, DBinfoStudent.class);
    }
    public void dropTable() throws SQLException {
        TableUtils.dropTable(connectionSource, DBinfoStudent.class, false);
    }

    public void createTable1() throws SQLException {
        TableUtils.createTable(connectionSource, DBgroups.class);
    }
    public void dropTable1() throws SQLException {
        TableUtils.dropTable(connectionSource, DBgroups.class, false);
    }

    public void saveStudents(LinkedHashMap<String, Person> students, LinkedHashMap<String, Student> hashGruop) throws SQLException {
        HashSet<String> uniqueGroups = new HashSet<>();
        Random random = new Random();
        int randomWithNextInt = 0;
        for (var student: students.values()) {
            for (var name : hashGruop.keySet()) {
                String d = "аываы";
                randomWithNextInt = random.nextInt();
                if (student.getFullName() == name){
                    studentDao.create(new DBinfoStudent(student.getFullName(), student.getGender(), student.getHomeAddress(), student.getBirthDate(), hashGruop.get(name).getGroup()) );
                }


            }
        }


    }

    public List<DBinfoStudent> getStudents() throws SQLException {
        return studentDao.queryForAll();
    }
    public List<DBgroups> getStudents1() throws SQLException {
        return groupDao.queryForAll();
    }
    public List<DBgroups> getStudentsByName1(String name) throws SQLException {
        return groupDao.queryBuilder()
                .where()
                .eq(DBgroups.NAME_COLUMN, name)
                .query();
    }
    public DBinfoStudent getStudentsByName(String name) throws SQLException {
        return studentDao.queryBuilder()
                .where()
                .eq(DBinfoStudent.NAME_COLUMN, name)
                .query().get(0);
    }

    public void close() throws Exception {
        connectionSource.close();
    }
}
