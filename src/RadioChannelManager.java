import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RadioChannelManager {
    public static void main(String[] args) {
        // Create a song pool
        SongPool songPool = new SongPool();
        List<Song> allSongs = songPool.getAllSongs();

        if (allSongs.isEmpty()) {
            System.out.println("No songs found in the song pool.");
            return;
        }

        // Choose a random song to play
        Song currentSong = songPool.getRandomSong();
        System.out.println("Currently playing: " + currentSong.getSongName());

        // Simulate user interactions
        NormalMember normalMember = new NormalMember("John", new Date());
        normalMember.likeSong(currentSong);

        GoldMember goldMember = new GoldMember("Jane", new Date());
        goldMember.likeSong(currentSong);
        goldMember.commentOnSong(currentSong, "Great song!");

        VIPMember vipMember = new VIPMember("Mike", new Date());
        vipMember.likeSong(currentSong);
        vipMember.commentOnSong(currentSong, "Nice melody!");
        vipMember.requestSong("New Song");

        VIPPlusMember vipPlusMember = new VIPPlusMember("Sarah", new Date());
        vipPlusMember.likeSong(currentSong);
        vipPlusMember.commentOnSong(currentSong, "Awesome beat!");
        vipPlusMember.requestSong("Popular Song");

        // Print the likes and comments of the songs
        System.out.println("Likes and Comments:");
        for (Song song : allSongs) {
            System.out.println("Song: " + song.getSongName());
            System.out.println("Likes: " + song.getLikes());
            System.out.println("Comments:");
            printComments(song);
            System.out.println();
        }

        // Find the most liked song
        Song songOfTheDay = findSongOfTheDay(allSongs);
        if (songOfTheDay != null) {
            System.out.println("Song of the Day: " + songOfTheDay.getSongName());
            // Write the song of the day to a file
            writeSongOfTheDayToFile(songOfTheDay);
        } else {
            System.out.println("No songs found in the song pool.");
        }
    }

    private static void printComments(Song song) {
        String fileName = song.getSongName() + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Song findSongOfTheDay(List<Song> songs) {
        int maxLikes = 0;
        Song songOfTheDay = null;
        for (Song song : songs) {
            int likes = song.getLikes();
            if (likes > maxLikes) {
                maxLikes = likes;
                songOfTheDay = song;
            }
        }
        return songOfTheDay;
    }

    private static void writeSongOfTheDayToFile(Song song) {
        try (FileWriter writer = new FileWriter("SongOfTheDay.txt")) {
            writer.write(song.getSongName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
