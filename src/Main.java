import java.io.*;
import java.lang.ProcessBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {
    private static String remoteBranchName ;
    private static String localBranchName ;
    public static void main(String[] args) throws IOException, InterruptedException {
        getProperties() ;
        String freshestCommit ;
        String oldestCommit ;
        oldestCommit = getOldestUnpushedCommit();
        freshestCommit = getFreshestCommit();
        if(freshestCommit == null || oldestCommit == null){
            System.out.println("Nothing to push, branch up to date");
            return ;
        }
        setHeadToSha1(getOldestUnpushedCommit());
        push() ;
        setHeadToSha1(freshestCommit);
    }

    private static void getProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream("src/application.properties");
        properties.load(in);
        in.close();

        remoteBranchName = properties.getProperty("remote_branch_name");
        localBranchName = properties.getProperty("local_branch_name");
    }

    private static void push() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "push", remoteBranchName, localBranchName) ;
        processBuilder.command(command) ;
        Process process = processBuilder.start();
    }

    /// it returns the oldest unpushed commit
    private static String getOldestUnpushedCommit() throws IOException {
        //git log origin/main..main --pretty=format:"%H %s"
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "log", remoteBranchName + "/" + localBranchName + ".." +
                localBranchName, "--pretty=format:%H");
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
        List<String> command = Arrays.asList("git", "log", remoteBranchName + "/" + localBranchName + ".." +
                localBranchName, "--pretty=format:%H");
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