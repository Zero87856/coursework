package Controllers;

import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("success/")
public class SuccessRatesController {
    @GET
    @Path("results")
    @Produces(MediaType.APPLICATION_JSON)
    public String findSuccess() {
        System.out.println("success/results");
        JSONArray list = new JSONArray();

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT questionWin, questionNo, successCount, attempt FROM successRates");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                //int id = results.getInt(1);
                //int name = results.getInt(2);
                //int guess = results.getInt(3);
                //int quantity = results.getInt(4);
                //System.out.print("Success Rate: " + id + ",  ");
                //System.out.print("Question: " + name + ",  ");
                //System.out.print("Times correct: " + guess + ",  ");
                //System.out.print("Attempts: " + quantity + "\n");
                JSONObject item = new JSONObject();
                item.put("Success Rate", results.getInt(1));
                item.put("Question", results.getInt(2));
                item.put("Times Correct", results.getInt(3));
                item.put("Attempts", results.getInt(4));
                list.add(item);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }
    @GET
    @Path("calc")
    @Produces(MediaType.APPLICATION_JSON)
    public String calcSuccess() {
        System.out.println("success/calc");
        JSONArray list = new JSONArray();

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT questionWin, successCount, attempt FROM successRates where questionNo = ?");

            ResultSet results = ps.executeQuery();

            JSONObject item = new JSONObject();
            list.add(item);
            item.get(results.getInt(2));
            item.get(results.getInt(3));

            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }
    public static void insertRate(int successRate, int questionNo, int successCount, int attempts) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "INSERT INTO successRates (questionWin, questionNo, successCount, attempt) VALUES (?, ?, ?, ?)");

            ps.setInt(1, successRate);
            ps.setInt(2, questionNo);
            ps.setInt(3, successCount);
            ps.setInt(4, attempts);


            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateRates(int successRate, int questionNo, int successCount, int attempts) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE successRates SET questionWin = ?, successCount = ?, attempt = ? WHERE questionNo = ?");

            ps.setInt(1, successRate);
            ps.setInt(2, questionNo);
            ps.setInt(3, successCount);
            ps.setInt(4, attempts);

            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }
    public static void deleteRates(int questionNo) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM successRates WHERE questionNo = ?");

            ps.setInt(1, questionNo);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
