/**
 * 根据cookie名称获取值
 * @param {Object} __key
 */
function getCookie (__key) {
  let __val = ''
  let __cookieArr = document.cookie.split(';')
  if (__cookieArr && __cookieArr.length > 0) {
    for (var i = 0; i < __cookieArr.length; i++) {
      let __cookieVal = __cookieArr[i]
      let __cookieKey = __key + '='
      if (__cookieVal.indexOf(__cookieKey) >= 0) {
        __val = __cookieVal.substring(__cookieKey.length + 1)
        break
      }
    }
  }
  return __val
}

module.exports = {
  getCookie: getCookie
}
