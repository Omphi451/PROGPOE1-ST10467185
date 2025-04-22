
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */
public class LoginREG {
    public static void main(String[] args) {
        //Getting username input
        String username = JOptionPane.showInputDialog("Please enter your username, username must contain underscore and no more than five characters long ");
        
        //Validating username
        if (checkUsername(username)) {
            JOptionPane.showMessageDialog(null, "Username was captured successfully");
            } else { 
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that username contains an underscore and is no more than five characters long");
        }
        
        //Getting password input
        String password = JOptionPane.showInputDialog("Please enter a password, password must be atleast 8 characters long and contain a capital letter, number and special character ");
        
        //Validating password
        if (checkPasswordComplexity(password)) {
           JOptionPane.showMessageDialog(null, "Password successfully captured");
           } else {
           JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that password is atleast 8 characters long and contains a capital letter, number and special character");
            
        }
    }

    public static boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean checkPasswordComplexity(String password) {
        if (password.length() <8 ) return false;
        
        boolean hasUpper = false;
        boolean hasNumber = false; 
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {       
           if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        
        return hasUpper && hasNumber && hasSpecial;
    }
}       
//ChatGPT.com. (2025). ChatGPT[online] available at https://chatgpt.com/c/6807c35e-e1d0-8011-8894-d32f86721127

