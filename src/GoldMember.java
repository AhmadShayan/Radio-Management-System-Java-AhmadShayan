import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class GoldMember extends AbstractUser {
    public GoldMember(String name, Date registrationDate) {
        super(name, registrationDate);
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
}
