package answersend;

public class RunningSendAnswersCommand extends SendAnswersCommand {
    @Override
    public boolean sendAnswer(String userName, String answer, int targetQuestionId) {
        String url = processUrl(userName, answer, targetQuestionId, "http://localhost/run/answer");

        String result = sendHttpRequest(url);

        return result.equals("true");
    }
}
