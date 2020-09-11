import answersend.RunningSendAnswersCommand;
import answersend.SendAnswersCommand;
import questionfind.FindQuestionCommand;
import questionfind.RunningFindQuestionCommand;

public class RunningFactory extends QuestionFactory {
    @Override
    public FindQuestionCommand createFindingQuestion() {
        return new RunningFindQuestionCommand();
    }

    @Override
    public SendAnswersCommand createSendingAnswers() {
        return new RunningSendAnswersCommand();
    }
}
