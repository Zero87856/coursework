package Controllers;

import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("question/")
public class QuestionsController {
    public static void listQuestions() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT questionNo, questionContent, questionDifficulty FROM main.questions");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int id = results.getInt(1);
                String questionContent = results.getString(2);
                int difficulty = results.getInt(3);
                System.out.print("Id: " + id + ",  ");
                System.out.print("Name: " + questionContent + ",  ");
                System.out.print("Quantity: " + difficulty + "\n");
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    @GET
    @Path("image/{qNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findImage(@PathParam("qNo") Integer qNo){

        System.out.println("question/image/" + qNo);

        try{
            PreparedStatement ps = Main.db.prepareStatement("SELECT image FROM questions WHERE questionNo = ?");
            ps.setInt(1, qNo);
            ResultSet results = ps.executeQuery();
            JSONObject item = new JSONObject();
            if (results.next()) {
                item.put("questionNo", qNo);
                item.put("img", results.getString(1));
            }
            return item.toString();
        }
        catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }
    @GET
    @Path("feedback/{qNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findFeedback(@PathParam("qNo") Integer qNo){
        System.out.println("question/feedback" + qNo);

        try{
            PreparedStatement ps = Main.db.prepareStatement("select feedback from questions where questionNo = ?");
            ps.setInt(1, qNo);
            ResultSet results = ps.executeQuery();
            JSONObject item = new JSONObject();
            item.put("feedback", results.getString(1));
            return item.toString();
        }
        catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }
    @GET
    @Path("startQuiz")
    @Produces(MediaType.APPLICATION_JSON)
    public String listQuestionsAndAnswers() {
        System.out.println("question/startQuiz");
        JSONArray list = new JSONArray();
        try {

            PreparedStatement ps = Main.db.prepareStatement("select questionNo, questionContent, answerA,answerB,answerC, answerD from questions inner join answers a on questions.answerSet = a.answerSet;");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                //int id = results.getInt(1);
                //String cont = results.getString(2);
                //String ansA = results.getString(3);
                //String ansB = results.getString(4);
                //String ansC = results.getString(5);
                //String ansD = results.getString(6);
                //System.out.print("Q"+ id +": " + cont + ",  ");
                //System.out.print("A: " + ansA + ",  ");
                //System.out.print("B: " + ansB + ",  ");
                //System.out.print("C: " + ansC + ",  ");
                //System.out.print("D: " + ansD + "\n");
                JSONObject item = new JSONObject();
                item.put("No", results.getInt(1));
                item.put("Q", results.getString(2));
                item.put("A", results.getString(3));
                item.put("B", results.getString(4));
                item.put("C", results.getString(5));
                item.put("D", results.getString(6));
                list.add(item);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }
    @GET
    @Path("diffCalc/{qNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String difficultyCalc(@PathParam("qNo") Integer qNo) {
        System.out.println("question/diffCalc");
        try {
            float qWin= 0;
            PreparedStatement ps = Main.db.prepareStatement("SELECT questionWin FROM successRates where questionNo = ?");
            ps.setInt(1,qNo);
            ResultSet results = ps.executeQuery();
            System.out.println("Test");
            if(results.next()){

            }
            PreparedStatement ps1 = Main.db.prepareStatement("UPDATE successRates SET questionWin = ? WHERE questionNo = ? ");
            System.out.println("inserting");
            ps1.setInt(2, qNo);
            ps1.setFloat(1, qWin);
            ps1.execute();
            System.out.println("done");
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }
    public static void insertQuestions(int questionNo, String questionContent, int questionDifficulty) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "INSERT INTO main.questions (questionNo, questionContent, questionDifficulty) VALUES (?, ?, ?)");

            ps.setInt(1, questionNo);
            ps.setString(2, questionContent);
            ps.setInt(3, questionDifficulty);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    public static void updateQuestions(int questionNo, String questionContent, int questionDifficulty) {

        try {

            var ps = Main.db.prepareStatement(
                    "UPDATE questions SET questionContent = ?, questionDifficulty = ? WHERE questionNo = ?");

            ps.setString(1, questionContent);
            ps.setInt(2, questionDifficulty);
            ps.setInt(3, questionNo);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    public static void deleteQuestions(int questionNo) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM questions WHERE questionNo = ?");

            ps.setInt(1, questionNo);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
