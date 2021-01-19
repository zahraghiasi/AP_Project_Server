import java.util.ArrayList;

public class Database {
    ArrayList<User> users;
    ArrayList<Question> questions = new ArrayList<>();

    public Database(ArrayList<User> users){
        this.users = users;
    }

    public void addQuestion(Question question1){
        Question q = new Question();
        q.question = question1.question;
        q.answer[0] = question1.answer[0];
        q.answer[1] = question1.answer[1];
        q.answer[2] = question1.answer[2];
        q.answer[3] = question1.answer[3];
        q.correct_answer = question1.correct_answer;
        questions.add(q);

    }

    public boolean has_username(String username){
        for (User user :users){
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public User find(String username){
        for (User user :users){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
