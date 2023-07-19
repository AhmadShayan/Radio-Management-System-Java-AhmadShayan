import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class SongImpl implements Song {
    private String songName;
    private String composerName;
    private int likes;

    public SongImpl(String songName, String composerName, int likes) {
        this.songName = songName;
        this.composerName = composerName;
        this.likes = likes;
    }

    public String getSongName() {
        return songName;
    }

    public String getComposerName() {
        return composerName;
    }

    public int getLikes() {
        return likes;
    }

    public void incrementLikes() {
        likes++;
        updateLikesInTextFile();
    }

    private void updateLikesInTextFile() {
        try {
            File inputFile = new File("AllSongs.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(songName)) {
                    String[] songData = line.split(" - ");
                    writer.write(songData[0] + " - " + songData[1] + " - " + likes + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Error occurred while updating likes in AllSongs.txt");
                }
            } else {
                System.out.println("Error occurred while updating likes in AllSongs.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
