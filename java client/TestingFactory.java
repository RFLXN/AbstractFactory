import answersend.SendAnswersCommand;
import answersend.TestingSendAnswerCommand;
import questionfind.FindQuestionCommand;
import questionfind.TestingFindQuestionCommand;

public class TestingFactory extends QuestionFactory {
    @Override
    public FindQuestionCommand createFindingQuestion() {
        return new TestingFindQuestionCommand();
    }

    @Override
    public SendAnswersCommand createSendingAnswers() {
        return new TestingSendAnswerCommand();
    }
}
