const NodePolyfillPlugin = require('node-polyfill-webpack-plugin');


module.exports = {
  
  lintOnSave: false,
  configureWebpack: {
    plugins: [new NodePolyfillPlugin()]
  },
  publicPath: ''
  
}
