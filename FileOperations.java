import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

/**
 * This Java program demonstrates file operations such as reading,
 * writing, and modifying text files with user input.
 */
public class FileOperations {
    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter content to write to the file:");
        String content = scanner.nextLine();
        writeFile(content);
        readFile();
        
        System.out.println("Enter the word to be replaced:");
        String oldWord = scanner.nextLine();
        
        System.out.println("Enter the new word:");
        String newWord = scanner.nextLine();
        
        modifyFile(oldWord, newWord);
        readFile();
        
        scanner.close();
    }

    /**
     * Writes text to a file.
     *
     * @param content The content to write to the file.
     */
    public static void writeFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads and prints the content of the file.
     */
    public static void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("File content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Modifies the file by replacing a specific word with another.
     *
     * @param oldWord The word to be replaced.
     * @param newWord The word to replace with.
     */
    public static void modifyFile(String oldWord, String newWord) {
        try {
            Path path = Paths.get(FILE_PATH);
            String content = new String(Files.readAllBytes(path));
            content = content.replaceAll(oldWord, newWord);
            Files.write(path, content.getBytes());
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }
}
