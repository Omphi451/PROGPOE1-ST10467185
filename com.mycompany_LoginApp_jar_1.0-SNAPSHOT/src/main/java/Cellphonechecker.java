
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */
public class Cellphonechecker {
   
//Created with help from ChatGPT by OpenAI
    public static void main(String[] args) {
        //Input cell number
        String number = JOptionPane.showInputDialog("Please enter cell phone number with international code (e.g, +1234567890):");
         
        //Validating cell number
        if (checkCellPhoneNumber(number)) {
           JOptionPane.showMessageDialog(null, "Cell phone number successfully captured");
        } else { 
            JOptionPane.showMessageDialog(null, "Cell phone number was incorrectly formatted or does not contain international code");
        }
    }

    public static boolean checkCellPhoneNumber(String number) {
        //Return false if input is null
        if (number == null) {
        return false;
        
        }
        //followed by 1 to 10 digits
        return number.matches("^\\+\\d{1,11}$");

}
}
 //ChatGPT.com. (2025). ChatGPT[online] available at https://chatgpt.com/c/6807c35e-e1d0-8011-8894-d32f86721127