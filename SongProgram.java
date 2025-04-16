//package hashingAndDocumentation;//comment this out if you do not need a package Commeneted this out unless you use it in eclipse

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


/** SongProgram.java
* @author Felix Oliveira-Machado
* @Since April 8 2025
* This Class loads stored song data from a csv file and lets you search for songs
* by using a HashMap provides a main2 for the purpose of using a graphical user interface.
*
*
*
*/



public class SongProgram {

    // HashMap to store SongRecords with the song's ID as the key
    private HashMap<String, SongRecord> songMap;

    // Constructor
    public SongProgram() {
        songMap = new HashMap<>();
    }
    
    /**
    * Method to load songs from a CSV file to add to the hash map
    *
    * @param filepath The path to the CSV file conating the data for the song
    *
    *
    */
    
    public void loadSongsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            //read in first line and do nothing with it
            br.readLine();
            
            while ((line = br.readLine()) != null) {
            	
            	//System.out.println(line);//for testing
                // Create a SongRecord from the line and add it to the map
                SongRecord song = new SongRecord(line);
                songMap.put(song.getId(), song);
            }
            System.out.println("Songs successfully loaded from CSV.");
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    /**
    * This Method returns a SongRecord by its unique ID.
    * @param id The unique song ID to search for.
    * @return The SongRecord if found, or null if not found
    */

    

    // Method to retrieve a SongRecord by ID
    public SongRecord getSongById(String id) {
        return songMap.get(id);
    }
    
    /**
    * This Method is used to print out all of the songs currently stored in the song map.
    * It's used to see if the file is being read and properly mapped
    */

    // Method to print all songs (for debugging or display purposes)
    public void printAllSongs() {
        for (SongRecord song : songMap.values()) {
            System.out.println(song);
        }
    }
    
    /** 
    * GUI method to search for a song by ID
    * by allwoing a user to search for song by ID
    * then once it's found it displays the songs information
    */

    public void openSearchGui() {
        // Create the main frame
        JFrame frame = new JFrame("Song Lookup");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold input and button
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Label, Text Field, and Button
        JLabel label = new JLabel("Enter Song ID:");
        JTextField idField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        // Add label, text field, and button to panel
        panel.add(label);
        panel.add(idField);
        panel.add(searchButton);

        // Result area to display song details
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        // Add action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                SongRecord song = getSongById(id);
                if (song != null) {
                    resultArea.setText("Song Found:\n" + song.toString());
                } else {
                    resultArea.setText("Song with ID " + id + " not found.");
                }
            }
        });

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

        
    /**
    * This Main2 method is used when a GUI is being used.
    * The intent is that you remove the 2 from Main and 
    * comment out the current Main inorder of this code to be able
    * to demonstarte song loading, searching, and printing.
    */
    public static void main2(String[] args) {
        SongProgram program = new SongProgram();

        // Load songs from a CSV file
        String filePath = "data.csv";  // replace with actual file path
        program.loadSongsFromCSV(filePath);

        // Open GUI for searching songs by ID
        program.openSearchGui();
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        SongProgram program = new SongProgram();

        // Load songs from a CSV file
        String filePath = "data.csv";  // replace with actual file path
        program.loadSongsFromCSV(filePath);

        // Demonstrate retrieving a song by ID
        String testId = "4BJqT0PrAfrxzMOxytFOIz";  // replace with an actual ID from your file
        SongRecord song = program.getSongById(testId);
        if (song != null) {
            System.out.println("Retrieved song: " + song);
        } else {
            System.out.println("Song with ID " + testId + " not found.");
        }

        // Print all songs
        program.printAllSongs();
    }
}

