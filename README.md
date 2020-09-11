# Abstract Factory

## Runtime Environment
"/node.js server/server.js" running on Node.js 12.13.1


## Component

### Maven Dependency (For JSON)
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
