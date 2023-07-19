import java.util.Date;
public class NormalMember extends AbstractUser {
    public NormalMember(String name, Date registrationDate) {
        super(name, registrationDate);
    }

    public void likeSong(Song song) {
        song.incrementLikes();
    }
}
