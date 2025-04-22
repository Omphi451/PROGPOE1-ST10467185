/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */

    import javax.swing.*;                       
import java.security.MessageDigest;       
import java.util.ArrayList;               
import java.util.Base64;      
    
public class Message {
    public String messageID;
    public String recipientCell;
    public String content;

    //keeping track of all messages sent and total count
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static int totalMessages = 0;
    
        // initializing message data
    public Message(String messageID, String recipientCell, String content) {
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        this.content = content;
    }
    
        // validating if message ID is 10 characters or less
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // validating the recipients phone number is 10 characters or less
    public int checkRecipientCell() {
        return recipientCell.length() <= 10 ? 1 : 0;
    }
    
        // Generates and returns a SHA-256 hash of the message content
    public String createMessageHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(content.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            return "Error generating hash.";
        }
    }
        // Leting the user decide to send, store, or disregard the message
    public String SentMessage() {
        String[] options = {"Send", "Store", "Disregard"};
        int choice = JOptionPane.showOptionDialog(null,
                "What would you like to do with this message?",
                "Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            sendMessage();
            return "Message sent.";
        } else if (choice == 1) {
            return storeMessage();
        } else {
            return "Message disregarded.";
        }
    }
        // Adds message content to the sent messages list and increments counter
    public void sendMessage() {
        sentMessages.add(content);
        totalMessages++;
    }
    
    // storing the message
    public String storeMessage() {
    StringBuilder storedMessage = new StringBuilder();
    
    storedMessage.append("Message ID: ").append(messageID).append("\n");
    storedMessage.append("Recipient Cell: ").append(recipientCell).append("\n");
    storedMessage.append("Content: ").append(content).append("\n");
    storedMessage.append("Hash: ").append(createMessageHash());

    JOptionPane.showMessageDialog(null, "Message stored:\n\n" + storedMessage.toString());

    return "Message stored.";
}

    
        // Prints list of all messages that were sent
    public static String printMessages() {
        if (sentMessages.isEmpty()) return "No messages sent yet.";
        StringBuilder sb = new StringBuilder("Sent Messages:\n");
        for (String msg : sentMessages) {
            sb.append("- ").append(msg).append("\n");
        }
        return sb.toString();
    }
    
        // Returns the total number of sent messages
    public static int returnTotalMessages() {
        return totalMessages;
    }
}
//ChatGPT.com. (2025). ChatGPT[online] available at https://chatgpt.com/c/6807c35e-e1d0-8011-8894-d32f86721127