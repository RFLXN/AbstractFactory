package questionfind;

import java.io.Serializable;

public class Question implements Serializable {
    private int id;
    private String questionContent;
    private String answer;

    public int getId() {
        return id;
    }
    public String getAnswer() {
        return answer;
    }
    public String getQuestionContent() {
        return questionContent;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
