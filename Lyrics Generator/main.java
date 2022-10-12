/*
 * Given a song title and artist name, this program will generate the lyrics 
 * of the song using the lyrics.wikia.com API.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;


public class Main {

	public static void main(String[] args) {
		try {
            // Create a new frame
            JFrame frame = new JFrame("Lyrics Generator");
            // Set the layout
            frame.setLayout(new FlowLayout());
            // Create the labels
            JLabel label1 = new JLabel("Song Title");
            JLabel label2 = new JLabel("Artist Name");
            JLabel label3 = new JLabel("Lyrics");
            // Create the text fields
            JTextField text1 = new JTextField(20);
            JTextField text2 = new JTextField(20);
            JTextField text3 = new JTextField(20);
            // Create the buttons
            JButton button1 = new JButton("Generate");
            // Add the components to the frame
            frame.add(label1);
            frame.add(text1);
            frame.add(label2);
            frame.add(text2);
            frame.add(button1);
            frame.add(label3);
            frame.add(text3);
            // Add the action listener
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Get the song title and artist name
                    String songTitle = text1.getText();
                    String artistName = text2.getText();
                    // Generate the lyrics
                    String lyrics = generateLyrics(songTitle, artistName);
                    // Set the text of the text field to the lyrics
                    text3.setText(lyrics);
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

    public static String generateLyrics(String songTitle, String artistName) {
        try {
            // Create a new URL object
            URL url = new URL("http://lyrics.wikia.com/api.php?func=getSong&artist=" + artistName + "&song=" + songTitle + "&fmt=xml");
            // Create a new BufferedReader object
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            // Create a new StringBuilder object
            StringBuilder builder = new StringBuilder();
            // Create a new String object
            String line;
            // Read the lines from the URL
            while ((line = reader.readLine()) != null) {
                // Append the line to the StringBuilder object
                builder.append(line);
            }
            // Close the BufferedReader object
            reader.close();
            // Convert the StringBuilder object to a String object
            String lyrics = builder.toString();
            // Create a new Pattern object
            Pattern pattern = Pattern.compile("<url>(.*?)</url>");
            // Create a new Matcher object
            Matcher matcher = pattern.matcher(lyrics);
            // Check if the Matcher object finds a match
            if (matcher.find()) {
                // Get the URL of the lyrics
                String lyricsURL = matcher.group(1);
                // Create a new URL object
                URL url2 = new URL(lyricsURL);
                // Create a new BufferedReader object
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(url2.openStream()));
                // Create a new StringBuilder object
                StringBuilder builder2 = new StringBuilder();
                // Create a new String object
                String line2;
                // Read the lines from the URL
                while ((line2 = reader2.readLine()) != null) {
                    // Append the line to the StringBuilder object
                    builder2.append(line2);
                }
                // Close the BufferedReader object
                reader2.close();
                // Convert the StringBuilder object to a String object
                String lyrics2 = builder2.toString();
                // Create a new Pattern object
                Pattern pattern2 = Pattern.compile("<div class=\"lyricbox\">(.*?)</div>");
                // Create a new Matcher object
                Matcher matcher2 = pattern2.matcher(lyrics2);
                // Check if the Matcher object finds a match
                if (matcher2.find()) {
                    // Get the lyrics
                    String lyrics3 = matcher2.group(1);
                    // Remove the HTML tags
                    lyrics3 = lyrics3.replaceAll("<.*?>", "");
                    // Return the lyrics
                    return lyrics3;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return an error message
        return "Error: Lyrics not found.";
    }
}

