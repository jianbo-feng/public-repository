'use strict'
let HOST = process.argv.splice(2)[0] || 'prod'
console.log('HOST', HOST)
module.exports = {
  NODE_ENV: '"production"',
  HOST: '"' + HOST + '"'
}
