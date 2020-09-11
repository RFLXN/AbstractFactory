const http = require('http');
const url = require('url');
const fs = require('fs');

const server = http.createServer((request, response) => {       // サーバ定義
    const urlData = url.parse(decodeURI(request.url), true);
    const contextPath = urlData.pathname;
    const parameters = urlData.query;

    response.writeHead(200, {'Content-Type': 'application/json'});

    if (contextPath === '/run/questions.json') {        // 本番の問題
        response.end(readFile('./run/questions.json'));
        console.log('[RUN] Received Question List Request');
    } else if (contextPath === '/test/questions.json') {    // テストの問題
        response.end(readFile('./test/questions.json'));
        console.log('[TEST] Received Question List Request')
    }

    if (contextPath === '/run/answer') {        // 本番の回答
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
    } else if (contextPath === '/test/answer') {        // テストの回答
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

server.listen(80, () => {       // サーバ実行 (80番ポート)
   console.log('Server Running on port 80');
});

function readFile(path) {       // ファインを読む関数
    return fs.readFileSync(path, 'utf8');
}

function writeFile(path, data) {        // ファインを書く関数
    fs.writeFileSync(path, data, 'utf8');
}

function processJson(path, data) {      // データをJSONファイルに追加関数
    let buff = readFile(path);
    let jsonBuff = JSON.parse(buff);

    jsonBuff.answers[jsonBuff.answers.length] = data;

    writeFile(path, JSON.stringify(jsonBuff));
}

function convertToAnswerObject(userName, targetQuestionId, answer) {        // ユーザー名, 問題ID, 回答をJSONオブジェクトに加工する関数
    return {
        "userName": userName,
        "targetQuestion": targetQuestionId,
        "answer": answer
    };
}
