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
        System.out.println(getOldestUnpushedCommit());
        System.out.println(getFreshestCommit());
        System.out.println("fsadgasdg\n");
        List<String> command = Arrays.asList("git", "log", "--graph", "--pretty=format:%H %s");
        ProcessBuilder processBuilder = new ProcessBuilder();
        //processBuilder.command("bash", "-c", command);
        processBuilder.command(command);
        Process process = processBuilder.start();

        // Read the output from the command
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            String[] parts = line.split(" ");
            //System.out.println("This is tha sha1 " + parts[0] + " : this the commit " + parts[1]);
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();
        System.out.println("\nExited with code : " + exitCode);
        int c9 ;int c10 ;int c11;int c12 ;int c13 ;
    }
    /// it returns the oldest unpushed commit
    private static String getOldestUnpushedCommit() throws IOException {
        //git log origin/main..main --pretty=format:"%H %s"
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "log", "origin/main..main", "--pretty=format:%H");
        processBuilder.command(command) ;
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "" ;
        String rez = "" ;
        while((line = reader.readLine()) != null){
            rez = line ;
        }
        return rez ;
    }
    private static String getFreshestCommit() throws IOException {
        //git log origin/main..main --pretty=format:"%H %s"
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "log", "origin/main..main", "--pretty=format:%H");
        processBuilder.command(command) ;
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader.readLine() ;
    }
}