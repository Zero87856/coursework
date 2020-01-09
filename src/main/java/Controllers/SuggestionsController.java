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

}

