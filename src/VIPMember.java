import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class VIPMember extends AbstractUser {
    private int songRequestsLeft;

    public VIPMember(String name, Date registrationDate) {
        super(name, registrationDate);
        this.songRequestsLeft = 3;
    }

    public void likeSong(Song song) {
        song.incrementLikes();
    }

    public void commentOnSong(Song song, String comment) {
        String fileName = song.getSongName() + ".txt";
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(comment + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean requestSong(String songName) {
        if (songRequestsLeft > 0) {
            // Check if the requested song exists in the pool
            if (songExistsInPool(songName)) {
                System.out.println("Requested song " + songName + " is being played.");
                songRequestsLeft--;
                return true;
            } else {
                System.out.println("Requested song " + songName + " is not available in the pool.");
                return false;
            }
        } else {
            System.out.println("No more song requests left for VIP Member " + getName());
            return false;
        }
    }

    private boolean songExistsInPool(String songName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("AllSongs.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] songData = line.split(" - ");
                String currentSongName = songData[0];
                if (currentSongName.equals(songName)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
