package questionfind;

import java.util.Map;

public class RunningFindQuestionCommand extends FindQuestionCommand {
    @Override
    public Question getQuestion(int id) {
        Map<Integer, Question> questions = getQuestionsMap("http://localhost/run/questions.json");

        if(isIdCorrect(id, questions)) {
            return questions.get(id);
        } else {
            return null;
        }
    }
}
