# Abstract Factory
## Java
| QuestionFactory | RunningFactory | TestingFactory | Total 11 Java Classes |
| --------------- | -------------- | -------------- | ------------------- |
| SendAnswersCommand | RunningSendAnswersCommand | TestingSendAnswerCommand | (Test.java) |
| FindQuestionCommand | RunningFindQuestionCommand | TestingFindQuestionCommand | (Question) |


* QuestionFactoryのgetFactory(String key)クラスメソッドの引数に"running"を入れればRunningFactoryのインスタンスを、"testing"を入れればTestingFactoryのインスタンスを返します。         
* QuestionFactoryの実装クラスのインスタンスでcreateFindingQuestion(), createSendingAnswers()メソッドでFindQuestionCommand, SendAnswersCommandの実装クラスをインスタンス化します。       
* FindQuestionCommandのgetQuestion(int id)メソッドでNode.jsサーバの"{SERVER_APP_HOME}/run/quesions.json"または"{SERVER_APP_HOME}/test/questions.json"をパーシングして問題の情報を返します。         
* SendAnswersCommandのsendAnswer(String userName, String answer, int id)メソッドでNode.jsサーバの{SERVER_APP_HOME}/run/answers.json"または{SERVER_APP_HOME}/test/answers.json"に回答を記録し、回答が正しいかが戻されます。

## Node.js (JavaScript)
* Node.jsのhttpモジュールでサーバを定義
* "/run/questions.json"または"/test/questions.json"パスのリクエストをもらった場合
  * readFile(path)関数でJSONファイルを読み込んでクライアントに返す
* "/run/answer"または"/test/answer"パスのリクエストをもらった場合
  * 3つのパラメータ (user -> userName, targer -> targetQuestionId, answer -> answer)
  * パラメータをconvertToAnswerObject(userName, targetQuestionId, answer)関数に加工してprocessJson(path, data)関数でanswers.jsonに保存
  * readFile(path)関数で元のJSONファイルのデータを読み込んでパラメータと比較して結果を返す

## Getting Started
1. Javaのcom.googlecode.json-simpleライブラリを追加
2. ライブラリをクラスパスに追加
3. Javaファイルをコンパイルする
4. Node.jsでserver.jsを実行
5. JavaでTest.javaを実行

## Runtime Environment
"/node.js server/server.js" running on Node.js 12.13.1        
"/java client/Test.java" running on Java8

## Component

### Maven Dependency (For JSON jar Library)
FindQuestionCommandのコンパイルのために以下のライブラリをクラスパスに追加してください。      
[Repository Link](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple/1.1.1)
<pre>
&ltdependency&gt    
  &ltgroupId&gtcom.googlecode.json-simple&lt/groupId&gt    
  &ltartifactId&gtjson-simple&lt/artifactId&gt
  &ltversion>'1.1.1'&lt/version&gt   
&lt/dependency>
</pre>


### questions.json Example
<pre>
{    
  "questions": [    
    {    
      "id": 1,    
      "content": "some question3",    
      "answer": "some answer3"    
    }    
  ]    
}    
</pre>

### answers.json Example
<pre>
{
  "answers": [
    {
      "userName": "someone's name",
      "targetQuestion": 1,
      "answer": "answer"
    }
  ]
}
</pre>
