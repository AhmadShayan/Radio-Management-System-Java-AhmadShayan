import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SongPool {
    private List<Song> songs;

    public SongPool() {
        this.songs = new ArrayList<>();
        loadSongsFromTextFile();
    }

    public void loadSongsFromTextFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("AllSongs.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] songData = line.split(" - ");
                String songName = songData[0];
                String composerName = songData[1];
                int likes = Integer.parseInt(songData[2]);
                Song song = new SongImpl(songName, composerName, likes);
                songs.add(song);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Song getRandomSong() {
        Random random = new Random();
        int index = random.nextInt(songs.size());
        return songs.get(index);
    }

    public List<Song> getAllSongs() {
        return songs;
    }
}
