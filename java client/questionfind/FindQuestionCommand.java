package questionfind;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class FindQuestionCommand {
    public abstract Question getQuestion(int id);

    protected boolean isIdCorrect(int id, Map<Integer, Question> list) {
        return list.containsKey(id);
    }

    protected Map<Integer, Question> getQuestionsMap(String url) {
        HashMap<Integer, Question> questionMap = new HashMap<>();

        JSONObject rootObject = parseJson(url);
        JSONArray jsonQuestions = (JSONArray)rootObject.get("questions");

        for(int i=0 ; i< jsonQuestions.size() ; i++) {
            JSONObject questionBuff = (JSONObject)jsonQuestions.get(i);
            Question question = new Question();
            Long idValue = (Long)questionBuff.get("id");
            question.setId(idValue.intValue());
            question.setQuestionContent((String)questionBuff.get("content"));
            question.setAnswer((String)questionBuff.get("answer"));

            questionMap.put(question.getId(), question);
        }

        return questionMap;
    }

    private JSONObject parseJson(String url) {
        String rawData = getFileFromServer(url);
        JSONObject jsonObject = null;

        try {
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject)parser.parse(rawData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private String getFileFromServer(String url) {
        String rawData = "";

        try {
            HttpURLConnection connection = (HttpURLConnection)(new URL(url).openConnection());
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            connection.connect();

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
