package lesson2.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TreeSet;

public class JdbcInteractor {

    private JdbcController jc;

    public JdbcInteractor() {
        this.jc = JdbcController.getIdbc();
    }
    public Statement statement;

    // Аутентификация по базе
    public synchronized String getNickByLoginPass(String login, String pass) {
        String result = null;

        String sql = String.format("select nickname from users where login = '%s' and password = '%s'", login, pass);

        ResultSet rs = jc.executeQuery(sql);

        if (rs != null) {
            try {
                if (rs.next()) {
                    result = rs.getString("nickname");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public synchronized Integer getNickIdByNick(String nick) {
        Integer result = null;

        String sql = String.format("select id from users where nickname = '%s'", nick);

        ResultSet rs = jc.executeQuery(sql);

        if (rs != null) {
            try {
                if (rs.next()) {
                    result = rs.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Прочитать blacklist пользователя
    public synchronized TreeSet<String> getBlackList(String nick) {
        ResultSet rs = jc.executeQuery(String.format(
                "SELECT nickname FROM users WHERE id IN " +
                        "(SELECT blocked_nick_id FROM blacklist WHERE nick_id = " +
                        "(SELECT id FROM users WHERE nickname ='%s'))", nick));

        TreeSet<String> result = new TreeSet<>();

        if (rs != null) {
            try {
                while (rs.next()) {
                    result.add(rs.getString("nickname"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // Добавить в базу список заблокированных пользователей
    public synchronized void addToBlackList(String nick, TreeSet<String> blocked) {
        for (String b : blocked) {
            String query = String.format(
                    "INSERT INTO blacklist (nick_id, blocked_nick_id) VALUES\n" +
                            "((SELECT A.id FROM users A where nickname = '%s'),\n" +
                            "(SELECT B.id FROM users B WHERE B.nickname = '%s'))", nick, b);

            jc.executeUpdate(query);
        }
    }

    // Добавить в базу историй сообщений
    public synchronized void addMessageToHistory(String nick, String message) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        String query = String.format(
                "INSERT INTO history (nick_id_sender, send_time, message) VALUES\n" +
                        "(%s, '%s', '%s')", getNickIdByNick(nick), sdf.format(cal.getTime()), message);

        jc.executeUpdate(query);
    }

    public synchronized void removeFromBlackList(String nick, TreeSet<String> unblocked) {
        for (String b : unblocked) {
            String query = String.format(
                    "DELETE FROM blacklist WHERE nick_id = " +
                            "(SELECT id FROM users WHERE nickname = '%s') " +
                            "AND blocked_nick_id = " +
                            "(SELECT id FROM users WHERE nickname = '%s')", nick, b);

            jc.executeUpdate(query);
        }
    }
}
