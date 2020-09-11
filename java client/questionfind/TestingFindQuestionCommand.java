package questionfind;

import java.util.Map;

public class TestingFindQuestionCommand extends FindQuestionCommand {
    @Override
    public Question getQuestion(int id) {
        Map<Integer, Question> questions = getQuestionsMap("http://localhost/test/questions.json");

        if(isIdCorrect(id, questions)) {
            return questions.get(id);
        } else {
            return null;
        }
    }
}
