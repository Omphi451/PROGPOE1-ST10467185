
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */
public class LOGINACCOUNT {
    public static void main(String[] args) {
        //Registered account
        String storedUsername = "user_"; //Username example
        String storedPassword = "Passw0rd!"; // Password example
        String firstName = "Omphi";
        String lastName = "Modisane";
        
        //Login
        String enteredUsername = JOptionPane.showInputDialog("Please enter your username:");
        String enteredPassword = JOptionPane.showInputDialog("Please enter your password");
        
        //Checking credentials
        if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
            JOptionPane.showMessageDialog(null, "welcome" + firstName + "," + lastName + "Great to see you again");
        } else {
            JOptionPane.showMessageDialog(null, "Username or Password was incorrect, please try again");
        }
            
        
    }
}
//ChatGPT.com. (2025). ChatGPT[online] available at https://chatgpt.com/c/6807c35e-e1d0-8011-8894-d32f86721127