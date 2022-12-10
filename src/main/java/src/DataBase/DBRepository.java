package src.DataBase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import src.DataBase.ModelsForDB.DBEntity;
import src.Person.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.SortedMap;

public class DBRepository {
    private final String URL = "jdbc:sqlite:C:\\Users\\artem\\OneDrive\\Рабочий стол\\JAVA база\\base.db";

    private ConnectionSource connectionSource = null;

    private Dao<DBEntity, String> studentDao = null;

    public void connect() throws SQLException {
        connectionSource = new JdbcConnectionSource(URL);

        studentDao = DaoManager.createDao(connectionSource, DBEntity.class);
    }

    public void createTable() throws SQLException {
        TableUtils.createTable(connectionSource, DBEntity.class);
    }

    public void saveStudents(SortedMap<String,  Person> students) throws SQLException {
        for (var student: students.values()) {
            studentDao.create(new DBEntity(student.getFullName(), student.getGender(), student.getHomeAddress(), student.getBirthDate()));
        }
    }

    public List<DBEntity> getPlayers() throws SQLException {
        return studentDao.queryForAll();
    }

    public List<DBEntity> getPlayersByName(String name) throws SQLException {
        return studentDao.queryBuilder()
                .where()
                .eq(DBEntity.NAME_COLUMN, name)
                .query();
    }

    public void close() throws Exception {
        connectionSource.close();
    }
}
