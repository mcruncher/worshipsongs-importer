package org.worshipsongs.importer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Pitchu on 10/18/2015.
 */
public class TopicDao {
    public int getTopicId(Connection connection, String topic)
    {
        int id = 0;
        try {
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery( "SELECT * FROM TOPICS where name = '" + topic + "';" );
            if(resultSet.next()) {
                id = resultSet.getInt("id");
            }
            resultSet.close();
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Exception:"+e);
        }
        return id;
    }

    public boolean insertTopicSongs(Connection connection, Topic topic, int songId)
    {
        try {
            String query = "insert into songs_topics (song_id, topic_id) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, songId);
            preparedStatement.setInt(2, topic.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int insertTopic(Connection connection, String topic)
    {
        try {
            String query = "insert into topics (name) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, topic);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return getTopicId(connection, topic);
    }
}