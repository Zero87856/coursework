package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuestionsController {
    public static void listQuestions() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT questionNo, questionContent, questionDifficulty FROM main.questions");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int id = results.getInt(1);
                String questionContent = results.getString(2);
                int difficulty = results.getInt(4);
                System.out.print("Id: " + id + ",  ");
                System.out.print("Name: " + questionContent + ",  ");
                System.out.print("Quantity: " + difficulty + "\n");
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
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

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM questions WHERE Id = ?");

            ps.setInt(1, questionNo);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
