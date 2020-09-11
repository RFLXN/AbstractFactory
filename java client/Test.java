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

        System.out.print("Input Question ID : ");
        String id = input.nextLine();
        Question question = findQuestionCommand.getQuestion(Integer.parseInt(id));
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
