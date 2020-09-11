import answersend.SendAnswersCommand;
import questionfind.FindQuestionCommand;

import java.util.HashMap;

public abstract class QuestionFactory {         // AbstractFactory
    private static HashMap<String, QuestionFactory> factoryMap = new HashMap<>();

    public static QuestionFactory getFactory(String key) {
        return factoryMap.get(key);
    }

    public abstract FindQuestionCommand createFindingQuestion();
    public abstract SendAnswersCommand createSendingAnswers();

    static {
        factoryMap.put("running", new RunningFactory());
        factoryMap.put("testing", new TestingFactory());
    }
}
