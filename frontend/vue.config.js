const NodePolyfillPlugin = require('node-polyfill-webpack-plugin');

module.exports = {
  
  lintOnSave: false,
  configureWebpack: {
    plugins: [new NodePolyfillPlugin()]
  },
  // 개발 서버 설정, run serve할때만 작동
  devServer: {
    // 프록시 설정
    proxy: {
        // 프록시 요청을 보낼 api의 시작 부분
        '/api/v0/': {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://127.0.0.1:5001'
        }
    }
  }
}
