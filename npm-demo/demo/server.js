var http = require("http");
http.createServer(function(request, response) {
    // 发送 HTTP头部
    // HTTP 状态值: 200 OK
    // 内容：text/plain
    response.writeHead(200, {
        'Context-Type': 'text/plain'
    });

    // 发送数据 'Hello World'
    response.end('Hello World');
}).listen(6776);

console.log('Server running at http://127.0.0.1:6776');