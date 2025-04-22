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
import java.util.*;

public class MessageSystem {

    // Using separate lists for clarity - maybe overkill but easier to track
    static ArrayList<Message> sentList = new ArrayList<>();
    static ArrayList<Message> savedMessages = new ArrayList<>();
    static ArrayList<Message> tossedMessages = new ArrayList<>();

    public static void main(String[] args) {
        // Keep showing menu until user decides to exit
        while (true) {
            String[] menuChoices = {
                "1. Send Message", "2. Store Message", "3. Disregard Message",
                "4. Display All Sender/Recipients", "5. Longest Sent Message",
                "6. Search by Message ID", "7. Search by Recipient",
                "8. Delete by Message Hash", "9. Full Report", "0. Exit"
            };

            String selected = (String) JOptionPane.showInputDialog(null,
                    "Choose an action", "Message Menu",
                    JOptionPane.QUESTION_MESSAGE, null, menuChoices, menuChoices[0]);

            if (selected == null || selected.startsWith("0")) {
                // User wants out
                break;
            }

            switch (selected.charAt(0)) {
                case '1': sendNewMessage(); break;
                case '2': storeMessage(); break;
                case '3': disregardMsg(); break;
                case '4': listSendersAndRecipients(); break;
                case '5': printLongestMessage(); break;
                case '6': findByMessageId(); break;
                case '7': findByRecipient(); break;
                case '8': deleteUsingHash(); break;
                case '9': fullSentReport(); break;
                // note: no default case - assuming inputs are limited to valid ones
            }
        }
    }

    // --- Message Creation Input ---
    public static Message inputMsg() {
        // Could add validation, but assuming users will input stuff properly
        String from = JOptionPane.showInputDialog("Enter Sender:");
        String to = JOptionPane.showInputDialog("Enter Recipient:");
        String msgId = JOptionPane.showInputDialog("Enter Message ID:");
        String msgBody = JOptionPane.showInputDialog("Enter Message Content:");

        return new Message(from, to, msgId, msgBody);
    }

    // --- Core Actions ---

    public static void sendNewMessage() {
        Message msg = inputMsg();
        sentList.add(msg);
        JOptionPane.showMessageDialog(null, "Message has been sent!\n" + msg);
    }

    public static void storeMessage() {
        Message m = inputMsg();
        savedMessages.add(m);
        JOptionPane.showMessageDialog(null, "Message stored safely.\n" + m);
    }

    public static void disregardMsg() {
        Message m = inputMsg();
        tossedMessages.add(m);
        JOptionPane.showMessageDialog(null, "This one’s been tossed.\n" + m);
    }

    public static void listSendersAndRecipients() {
        StringBuilder info = new StringBuilder("All Senders and Recipients:\n");
        for (Message m : getAllMessages()) {
            info.append("From: ").append(m.sender)
                .append(" -> To: ").append(m.recipient).append("\n");
        }
        display(info.toString());
    }

    public static void printLongestMessage() {
        if (sentList.size() == 0) {
            display("No messages have been sent yet.");
            return;
        }

        Message longestSoFar = sentList.get(0); // start with first
        for (Message m : sentList) {
            // technically could combine this check inside the loop, but keeping it separate
            if (m.content.length() > longestSoFar.content.length()) {
                longestSoFar = m;
            }
        }

        display("Longest Sent Message:\n" + longestSoFar);
    }

    public static void findByMessageId() {
        String searchId = JOptionPane.showInputDialog("Type Message ID to look for:");
        for (Message msg : getAllMessages()) {
            if (msg.messageID.equals(searchId)) {
                display("Message Found!\nTo: " + msg.recipient + "\nText: " + msg.content);
                return;
            }
        }

        display("Oops, nothing found for ID: " + searchId);
    }

    public static void findByRecipient() {
        String person = JOptionPane.showInputDialog("Enter Recipient Name:");
        StringBuilder output = new StringBuilder("Messages sent to " + person + ":\n");

        boolean anythingFound = false;
        for (Message m : getAllMessages()) {
            if (m.recipient.equalsIgnoreCase(person)) {
                output.append("• ").append(m.content).append("\n");
                anythingFound = true;
            }
        }

        if (!anythingFound) {
            output.append("None.");
        }

        display(output.toString());
    }

    public static void deleteUsingHash() {
        String hashKey = JOptionPane.showInputDialog("Enter Message Hash to remove:");
        boolean removed = false;

        for (Iterator<Message> it = sentList.iterator(); it.hasNext(); ) {
            Message m = it.next();
            if (m.getHash().equals(hashKey)) {
                it.remove();
                display("Message successfully deleted:\n" + m);
                removed = true;
                break;
            }
        }

        if (!removed) {
            display("Couldn’t find any sent message with that hash.");
        }
    }

    public static void fullSentReport() {
        if (sentList.isEmpty()) {
            display("There are no sent messages.");
            return;
        }

        StringBuilder report = new StringBuilder("All Sent Messages:\n\n");
        for (Message m : sentList) {
            report.append(m.toString()).append("\n---\n");
        }

        display(report.toString());
    }

    public static List<Message> getAllMessages() {
        // Collect everything together, inefficient but fine for small sets
        ArrayList<Message> fullList = new ArrayList<>();
        fullList.addAll(sentList);
        fullList.addAll(savedMessages);
        fullList.addAll(tossedMessages);
        return fullList;
    }

    public static void display(String message) {
        JTextArea textArea = new JTextArea(message);
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea));
    }
}

// --- Message Class ---
class Message {
    String sender;
    String recipient;
    String messageID;
    String content;

    public Message(String s, String r, String id, String text) {
        sender = s;
        recipient = r;
        messageID = id;
        content = text;
    }

    // Get a quick hash of the content - used as a kind of fingerprint
    public String getHash() {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            byte[] data = content.getBytes("UTF-8");
            byte[] result = digester.digest(data);
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception err) {
            // Should probably log this properly
            return "HashError";
        }
    }

    public String toString() {
        return "From: " + sender + "\nTo: " + recipient +
               "\nMsg ID: " + messageID + "\nText: " + content +
               "\nHash: " + getHash();
    }
}
//ChatGPT.com. (2025). ChatGPT[online] available at https://chatgpt.com/c/6807c35e-e1d0-8011-8894-d32f86721127
    

