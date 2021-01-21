import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String username;
    private String password;
    private String profileImage;
    private boolean isLoggedIn = false;
    private int coin;
    private int score;
    private ArrayList<Game> onlineGames = new ArrayList<>();
    private ArrayList<Game> pastGames = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public ArrayList<Game> getOnlineGames() {
        return onlineGames;
    }

    public void setOnlineGames(ArrayList<Game> onlineGames) {
        this.onlineGames = onlineGames;
    }

    public ArrayList<Game> getPastGames() {
        return pastGames;
    }

    public void setPastGames(ArrayList<Game> pastGames) {
        this.pastGames = pastGames;
    }
}
