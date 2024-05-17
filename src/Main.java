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
        String freshestCommit ;
        String oldestCommit ;
        System.out.println(oldestCommit = getOldestUnpushedCommit());
        System.out.println(freshestCommit = getFreshestCommit());
        if(freshestCommit == null || oldestCommit == null || freshestCommit.equals(oldestCommit)){
            System.out.println("Nothing to push, branch up to date");
            return ;
        }
        setHeadToSha1(getOldestUnpushedCommit());
        push() ;
        setHeadToSha1(freshestCommit);
    }

    private static void push() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "push", "origin", "main") ;
        processBuilder.command(command) ;
        Process process = processBuilder.start();
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
        String rez = null ;
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
    private static void setHeadToSha1(String sha1) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "reset", "--soft", sha1) ;
        processBuilder.command(command) ;
        Process process = processBuilder.start();
    }
}