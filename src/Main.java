import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //String command = "git log --oneline" ;
        //String command = "git log --pretty=format:\"%H %s\"";
        List<String> command = Arrays.asList("git", "log", "--pretty=format:%H %s");
        ProcessBuilder processBuilder = new ProcessBuilder();
        //processBuilder.command("bash", "-c", command);
        processBuilder.command(command);
        Process process = processBuilder.start();

        // Read the output from the command
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            System.out.println("This is tha sha1 " + parts[0] + " : this the commit " + parts[1]);
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();
        System.out.println("\nExited with code : " + exitCode);
        int c9 ;
    }
}