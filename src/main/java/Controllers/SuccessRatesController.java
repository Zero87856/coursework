package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SuccessRatesController {
    public static void listSuccess() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT questionWin, questionNo, attempt FROM successRates");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int id = results.getInt(1);
                int name = results.getInt(2);
                int quantity = results.getInt(4);
                System.out.print("Success Rate: " + id + ",  ");
                System.out.print("Question: " + name + ",  ");
                System.out.print("Attempts: " + quantity + "\n");
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
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
    public static void updateRates(int successRate, int questionNo, int successCount, int attempts) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE successRates SET questionWin = ?, successCount = ?, attempt = ? WHERE questionNo = ?");

            ps.setInt(1, successRate);
            ps.setInt(2, questionNo);
            ps.setInt(3, successCount);
            ps.setInt(4, attempts);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
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
