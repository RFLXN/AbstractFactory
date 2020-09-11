# AbstractFactory



// Maven Dependency (For JSON)
<dependency>
    <groupId>com.googlecode.json-simple</groupId>
    <artifactId>json-simple</artifactId>
    <version>1.1.1</version>
</dependency>


//questions.json Example
{
  "questions": [
    {
      "id": 1,
      "content": "some question3",
      "answer": "some answer3"
    },
    {
      "id": 2,
      "content": "some question2",
      "answer": "some answer2"
    }
  ]
}


//answers.json Example
{
  "answers": [
    {
      "userName": "someone's name",
      "targetQuestion": 1,
      "answer": "answer"
    }
  ]
}
