package Controllers;

import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AnswersController {

    @GET
    @Path("listAnswers")
    @Produces(MediaType.APPLICATION_JSON)
    public static String listAnswers() {
        System.out.println("answers/listAnswers");
        JSONArray list = new JSONArray();
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT answerSet, answerTrue FROM answers");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                int id = results.getInt(1);
                String trueAns = results.getString(6);
                item.put("id", results.getInt(1));
                item.put("trueAns", results.getString(2));


            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
        return null;
    }
    @GET
    @Path("addAnswers")
    @Produces(MediaType.APPLICATION_JSON)
    public static void insertThing(int answerSet, String answerTrue) {
        System.out.println("answers/addAnswers");
        JSONArray list = new JSONArray();
        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "INSERT INTO answers (answerSet, answerTrue) VALUES (?, ?)");

            ps.setInt(1, answerSet);
            ps.setString(2, answerTrue);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    public static void updateThing(int answerSet, String answerTrue) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE answers SET answerTrue = ? WHERE answerSet = ?");

            ps.setString(1, answerTrue);
            ps.setInt(2, answerSet);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    public static void deleteThing(int answerSet) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM answers WHERE answerSet = ?");

            ps.setInt(1, answerSet);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
