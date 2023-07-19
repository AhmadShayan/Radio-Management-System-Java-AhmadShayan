import java.util.Date;

public abstract class AbstractUser implements User {
    private String name;
    private Date registrationDate;

    public AbstractUser(String name, Date registrationDate) {
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
}
