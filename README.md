# Abstract Factory
| QuestionFactory | RunningFactory | TestingFactory |
| ---------- | -------------- | -------------- |
| SendAnswersCommand | RunningSendAnswersCommand | TestingSendAnswerCommand |
| FindQuestionCommand | RunningFindQuestionCommand | TestingFindQuestionCommand |


QuestionFactoryのgetFactory(String key)クラスメソッドの引数に"running"を入れればRunningFactoryのインスタンスを、"testing"を入れればTestingFactoryのインスタンスを返します。         
QuestionFactoryの実装クラスのインスタンスでcreateFindingQuestion(), createSendingAnswers()メソッドでFindQuestionCommand, SendAnswersCommandの実装クラスをインスタンス化します。       
FindQuestionCommandのgetQuestion(int id)メソッドでNode.jsサーバの"{SERVER_APP_HOME}/run/quesions.json"または"{SERVER_APP_HOME}/test/questions.json"をパーシングして問題の情報を返します。         
SendAnswersCommandのsendAnswer(String userName, String answer, int id)メソッドでNode.jsサーバの{SERVER_APP_HOME}/run/answers.json"または{SERVER_APP_HOME}/test/answers.json"に回答を記録し、回答が正しいかが戻されます。

## Getting Start
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
