package TimeTrackerTui;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class GitHelper {
    private Git git;

    public void setRepoDir(File file) throws IOException {
        try {
            this.git = Git.open(file);
        } catch (IOException e) {
            System.out.println("Could not open Git repository at " + file.getAbsolutePath());
            throw e;
        }
    }


    public void commitChanges(String message) throws Exception {

        git.add().addFilepattern(".").call();

        RevCommit commit = git.commit()
                .setMessage(message)
                .call();

        System.out.println("Committed: " + commit.getFullMessage());
        Status status = git.status().call();
        System.out.println("Files staged and committed:");
        status.getAdded().forEach(f -> System.out.println("Added: " + f));
        status.getChanged().forEach(f -> System.out.println("Modified: " + f));
        status.getRemoved().forEach(f -> System.out.println("Removed: " + f));
    }

    public void pushChanges(String username, String tokenOrPassword) {
        try {
            git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, tokenOrPassword)).call();

            System.out.println("changes pushed successfully!");
        } catch (Exception e) {
            System.out.println("failed to push changes.");
            e.printStackTrace();
        }
    }




}
