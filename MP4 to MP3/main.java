/*
 * Given a youtube video link, convert the video and let the user download it in an MP3 format.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Create a new frame
            JFrame frame = new JFrame("MP4 to MP3");
            // Set the layout
            frame.setLayout(new FlowLayout());
            // Create the labels
            JLabel label1 = new JLabel("Youtube Video Link");
            JLabel label2 = new JLabel("Status");
            // Create the text fields
            JTextField text1 = new JTextField(20);
            JTextField text2 = new JTextField(20);
            // Create the buttons
            JButton button1 = new JButton("Convert");
            // Add the components to the frame
            frame.add(label1);
            frame.add(text1);
            frame.add(button1);
            frame.add(label2);
            frame.add(text2);
            // Add the action listener
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Get the youtube video link
                    String youtubeLink = text1.getText();
                    // Convert the video
                    String status = convertVideo(youtubeLink);
                    // Set the text of the text field to the status
                    text2.setText(status);
                }
            });
            // Set the size of the frame
            frame.setSize(300, 200);
            // Set the frame to visible
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertVideo(String youtubeLink) {
        try {
            // Create a new URL object
            URL url = new URL(youtubeLink);
            // Get the file name
            String fileName = url.getFile();
            // Get the file name without the extension
            String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
            // Get the file name without the path
            String fileNameWithoutPath = fileName.substring(fileName.lastIndexOf("/") + 1);
            // Get the file name without the path and the extension
            String fileNameWithoutPathAndExtension = fileNameWithoutPath.substring(0, fileNameWithoutPath.lastIndexOf("."));
            // Get the file name with the path and the extension
            String fileNameWithPathAndExtension = "C:\\Users\\User\\Desktop\\MP4 to MP3\\" + fileNameWithoutPathAndExtension + ".mp4";
            // Get the file name with the path and the extension
            String fileNameWithPathAndExtension2 = "C:\\Users\\User\\Desktop\\MP4 to MP3\\" + fileNameWithoutPathAndExtension + ".mp3";
            // Create a new URLConnection object
            URLConnection urlConnection = url.openConnection();
            // Get the input stream
            InputStream inputStream = urlConnection.getInputStream();
            // Create a new file output stream
            FileOutputStream fileOutputStream = new FileOutputStream(fileNameWithPathAndExtension);
            // Create a new byte array
            byte[] buffer = new byte[1024];
            // Create a new integer
            int length;
            // While the input stream is not empty
            while ((length = inputStream.read(buffer)) != -1) {
                // Write the bytes to the file output stream
                fileOutputStream.write(buffer, 0, length);
            }
            // Close the input stream
            inputStream.close();
            // Close the file output stream
            fileOutputStream.close();
            // Create a new process builder
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", "ffmpeg -i " + fileNameWithPathAndExtension + " " + fileNameWithPathAndExtension2);
            // Start the process
            processBuilder.start();
            // Return the status
            return "Video converted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            // Return the status
            return "Video conversion failed";
        }
    }
}