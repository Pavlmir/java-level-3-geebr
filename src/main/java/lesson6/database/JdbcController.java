package lesson6.database;

import java.sql.*;

import static lesson6.utils.Share.JDBC_CLASS_NAME;
import static lesson6.utils.Share.URL_JDBC;

public class JdbcController {

    public Connection connection;

    public Statement getStatement() {
        return statement;
    }

    public Statement statement;

    private JdbcController() {
        try {
            Class.forName(JDBC_CLASS_NAME);
            connection = DriverManager.getConnection(URL_JDBC);
            statement = connection.createStatement();

            createTables();
            fillUsersTableTestData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillUsersTableTestData() {
        try {
            //очищаем таблицу перед заполнением
            statement.execute("DELETE from users");

            //сбрасываем счетчик автоинкрементирования первичного ключа на ноль
            statement.execute("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='users'");

            for (int i = 0; i < 10; i++) {
                String query = String.format(
                        "INSERT INTO users (login, password, nickname) VALUES ('%s', '%s', '%s')", "login"+i,"pass"+i,"nick"+i);

                statement.execute(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class JdbcControllerHolder {
        public static JdbcController instance = new JdbcController();
    }

    public static JdbcController getIdbc() {
        return JdbcControllerHolder.instance;
    }

    // Отключиться от базы
    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    // Выполнить SELECT
    public synchronized ResultSet executeQuery(String sql) {
        ResultSet rs = null;

        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {e.printStackTrace();}

        return rs;
    }

    // Выполнить INSERT
    public synchronized void executeUpdate(String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {e.printStackTrace();}
    }

    private void createTables(){
        try {
            //Создаем таблицу пользователей если она еще не была создана
            statement.execute("CREATE TABLE IF NOT EXISTS users(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                    "login TEXT NOT NULL UNIQUE, " +
                    "password TEXT NOT NULL, " +
                    "nickname TEXT NOT NULL UNIQUE)");

            //Создаем таблицу черного списка если она еще не была создана
            statement.execute("CREATE TABLE IF NOT EXISTS blacklist(" +
                    "nick_id INTEGER NOT NULL, " +
                    "blocked_nick_id INTEGER NOT NULL)");

            //Создаем таблицу истории сообщений если она еще не была создана
            statement.execute("CREATE TABLE IF NOT EXISTS history(" +
                    "message_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                    "nick_id_sender INTEGER NOT NULL, " +
                    "send_time text NOT NULL, " +
                    "message text NOT NULL)");

            //Создаем таблицу пользователей которые не получали сообщений, потому что бы в ЧС если она еще не была создана
            statement.execute("CREATE TABLE IF NOT EXISTS recipients(" +
                    "message_id INTEGER NOT NULL, " +
                    "nick_id_not_recipient INTEGER NOT NULL)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
