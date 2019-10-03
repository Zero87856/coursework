package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SuccessRatesController {
    public static void listThings() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT Id, Name, Quantity FROM Things");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int id = results.getInt(1);
                String name = results.getString(2);
                int quantity = results.getInt(3);
                System.out.print("Id: " + id + ",  ");
                System.out.print("Name: " + name + ",  ");
                System.out.print("Quantity: " + quantity + "\n");
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    public static void insertThing(int id, String name, int quantity) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "INSERT INTO Things (Id, Name, Quantity) VALUES (?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, quantity);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    public static void updateThing(int id, String name, int quantity) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Things SET Name = ?, Quantity = ? WHERE Id = ?");

            ps.setString(1, name);
            ps.setInt(2, quantity);
            ps.setInt(3, id);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
    public static void deleteThing(int id) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Things WHERE Id = ?");

            ps.setInt(1, id);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
