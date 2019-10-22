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
    public static void listQuestionsAndAnswers() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("select questionNo, questionContent, answerA,answerB,answerC, answerD from questions inner join answers a on questions.answerSet = a.answerSet;");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int id = results.getInt(1);
                String cont = results.getString(2);
                String ansA = results.getString(3);
                String ansB = results.getString(4);
                String ansC = results.getString(5);
                String ansD = results.getString(6);
                System.out.print("Q"+ id +": " + cont + ",  ");
                System.out.print("A: " + ansA + ",  ");
                System.out.print("B: " + ansB + ",  ");
                System.out.print("C: " + ansC + ",  ");
                System.out.print("D: " + ansD + "\n");
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
