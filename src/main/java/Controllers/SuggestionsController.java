package Controllers;

import Server.Main;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("suggest/")
public class SuggestionsController {
    public static void listSuggestions() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT suggestId, suggestContent, suggestAnsTrue FROM suggestions");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int id = results.getInt(1);
                String suggestContent = results.getString(2);
                String suggestAnsTrue = results.getString(3);
                System.out.print("Suggestion Id: " + id + ",  ");
                System.out.print("Suggested Content: " + suggestContent + ",  ");
                System.out.print("True answer: " + suggestAnsTrue + "\n");
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    @POST
    @Path("input")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public static void insertSuggestion(@FormDataParam("id") Integer id, @FormDataParam("content") String content, @FormDataParam("ans") String ans) {

        try {
            if (id == null || content == null || ans == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/new id=" + id);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO suggestions (suggestId, suggestContent, suggestAnsTrue) VALUES (?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, content);
            ps.setString(3, ans);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    public static void updateSuggest(int suggestId, String suggestContent, String suggestAnsTrue) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE suggestions SET suggestContent = ?, suggestAnsTrue = ? WHERE suggestId = ?");

            ps.setInt(1, suggestId);
            ps.setString(2, suggestContent);
            ps.setString(3, suggestAnsTrue);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    public static void deleteSuggest(int id) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM suggestions WHERE suggestId = ?");

            ps.setInt(1, id);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}

