package TimeTrackerTui;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;

public class gitHelper {
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
        // Stage all changes
        git.add().addFilepattern(".").call();

        // Commit
        RevCommit commit = git.commit()
                .setMessage(message)
                .call();

        // Print commit info
        System.out.println("Committed: " + commit.getFullMessage());

        // Optionally, list the files in the commit
        Status status = git.status().call();
        System.out.println("Files staged and committed:");
        status.getAdded().forEach(f -> System.out.println("Added: " + f));
        status.getChanged().forEach(f -> System.out.println("Modified: " + f));
        status.getRemoved().forEach(f -> System.out.println("Removed: " + f));
    }

}
