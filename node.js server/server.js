const http = require('http');
const url = require('url');
const fs = require('fs');

const server = http.createServer((request, response) => {
    const urlData = url.parse(decodeURI(request.url), true);
    const contextPath = urlData.pathname;
    const parameters = urlData.query;

    response.writeHead(200, {'Content-Type': 'application/json'});

    if (contextPath === '/run/questions.json') {
        response.end(readFile('./run/questions.json'));
        console.log('[RUN] Received Question List Request');
    } else if (contextPath === '/test/questions.json') {
        response.end(readFile('./test/questions.json'));
        console.log('[TEST] Received Question List Request')
    }

    if (contextPath === '/run/answer') {
        const userName = parameters.user;
        const targetQuestionId = parameters.target*1;
        const answer = parameters.answer;

        processJson('./run/answers.json', convertToAnswerObject(userName, targetQuestionId, answer));

        console.log('[RUN] Received Answer Request (userName=' + userName
            +', targetQuestionID=' + targetQuestionId + ', answer=' + answer + ')');

        const buff = JSON.parse(readFile('./run/questions.json'));

        let isCorrectId = false;
        let isCorrectAnswer = false;
        for(var i in buff.questions) {
            if(buff.questions[i].id == targetQuestionId) {
                isCorrectId = true;

                if(buff.questions[i].answer == answer) {
                    isCorrectAnswer = true;
                }

                break;
            }
        }


        response.end((isCorrectId&&isCorrectAnswer).toString());
    } else if (contextPath === '/test/answer') {
        const userName = parameters.user;
        const targetQuestionId = parameters.target*1;
        const answer = parameters.answer;

        processJson('./test/answers.json', convertToAnswerObject(userName, targetQuestionId, answer));

        const buff = JSON.parse(readFile('./test/questions.json'));

        let isCorrectId = false;
        let isCorrectAnswer = false;
        for(var i in buff.questions) {
            if(buff.questions[i].id == targetQuestionId) {
                isCorrectId = true;

                if(buff.questions[i].answer == answer) {
                    isCorrectAnswer = true;
                }

                break;
            }
        }

        response.end((isCorrectId&&isCorrectAnswer).toString());
    }
});

server.listen(80, () => {
   console.log('Server Running on port 80');
});

function readFile(path) {
    return fs.readFileSync(path, 'utf8');
}

function writeFile(path, data) {
    fs.writeFileSync(path, data, 'utf8');
}

function processJson(path, data) {
    let buff = readFile(path);
    let jsonBuff = JSON.parse(buff);

    jsonBuff.answers[jsonBuff.answers.length] = data;

    writeFile(path, JSON.stringify(jsonBuff));
}

function convertToAnswerObject(userName, targetQuestionId, answer) {
    return {
        "userName": userName,
        "targetQuestion": targetQuestionId,
        "answer": answer
    };
}
