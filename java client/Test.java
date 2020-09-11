import answersend.SendAnswersCommand;
import questionfind.FindQuestionCommand;
import questionfind.Question;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        QuestionFactory factory = QuestionFactory.getFactory("running");

        FindQuestionCommand findQuestionCommand = factory.createFindingQuestion();
        SendAnswersCommand sendAnswersCommand = factory.createSendingAnswers();

        Question question = findQuestionCommand.getQuestion(2);

        System.out.println("Question ID : " + question.getId());
        System.out.println("Question Content : " + question.getQuestionContent());
        System.out.print("Insert Your Answer : ");
        String answer = input.nextLine();
        System.out.print("Insert Your Name : ");
        String userName = input.nextLine();

        boolean result = sendAnswersCommand.sendAnswer(userName, answer, question.getId());

        if(result) {
            System.out.println("Your Answer is Correct!");
        } else {
            System.out.println("Your Answer is Incorrect :(");
        }
    }
}
