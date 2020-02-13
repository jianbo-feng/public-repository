const pg = require("pg");
var connUrl = "postgres://postgres:postgres@localhost:5432/quartz";

var client = new pg.Client(connUrl);
client.connect(function(err) {
    if (err) {
        return console.error('连接postgresql数据库失败', err);
    }
    client.query("select * from quartz.qrtz_simple_triggers", function(err, data) {
        if (err) {
            return console.error('查询失败', err);
        } else {
            console.log('查询成功\n\t', data.rows, data.rowCount);
            console.log('Query success: ', JSON.stringify(data.rows));
        }
        client.end();
    });
});