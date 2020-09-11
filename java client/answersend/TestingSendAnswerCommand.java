package answersend;

public class TestingSendAnswerCommand extends SendAnswersCommand {
    @Override
    public boolean sendAnswer(String userName, String answer, int targetQuestionId) {
        String url = processUrl(userName, answer, targetQuestionId, "http://localhost/test/answer");

        System.out.println();

        String result = sendHttpRequest(url);

        return result.equals("true");
    }
}
