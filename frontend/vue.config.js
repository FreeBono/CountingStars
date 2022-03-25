const NodePolyfillPlugin = require('node-polyfill-webpack-plugin');
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer')
    .BundleAnalyzerPlugin


module.exports = {
  lintOnSave: false,
  configureWebpack: {
    plugins: [new NodePolyfillPlugin(), new BundleAnalyzerPlugin()]
  }
  
}
