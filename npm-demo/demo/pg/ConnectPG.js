var pg = require("pg");

// 数据库配置
var config = {
    user: 'postgres',
    database: 'quartz',
    password: 'postgres',
    port: 5432,
    host: 'localhost',
    // 扩展属性
    max: 20, // 连接池最大连接数
    idleTimeoutMillis: 3000, // 连接最大空闲时间 3s
}

// 创建连接池
var pool = new pg.Pool(config);
// 查询
pool.connect(function(err, client, done) {
	if (err) {
		return console.error('数据库连接出错', err);
	}
	// 简单输出一个Helloworld
	client.query('select $1::varchar AS OUT', ['Hello world'], function(err, result) {
		done();	// 释放连接（将其返回给连接池）
		if (err) {
			return consolnpe.error('查询出错', err);
		}
		console.log(result.rows[0].out);	// output : Hello world
	});
});