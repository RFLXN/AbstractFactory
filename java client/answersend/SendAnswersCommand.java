package answersend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public abstract class SendAnswersCommand {
    // 回答を送ってその回答が正解であればtrueを返す目的の抽象メソッド
    public abstract boolean sendAnswer(String userName, String answer, int targetQuestionId);

    // urlをパラメータをつけて加工
    protected String processUrl(String userName, String answer, int targetQuestionId, String url) {
        String resultUrl = url;
        try {
            String encodedUserName = URLEncoder.encode(userName, "utf8");
            String encodedAnswer = URLEncoder.encode(answer, "utf8");
            String encodedTargetId = URLEncoder.encode(Integer.toString(targetQuestionId), "utf8");

            resultUrl= resultUrl + "?user=" + encodedUserName + "&target=" + encodedTargetId +
                    "&answer=" + encodedAnswer;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultUrl;
    }

    // urlへGETメソッドでリクエストする
    protected String sendHttpRequest(String url) {
        String rawData = "";
        try {
            HttpURLConnection connection = (HttpURLConnection)(new URL(url).openConnection());
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String buff = "";
            while((buff=reader.readLine()) != null) {
                rawData += buff;
            }

            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rawData;
    }
}
