const NodePolyfillPlugin = require("node-polyfill-webpack-plugin")

module.exports = {
  target: node,
  node: {
    fs: "empty"
  },
  
  resolve: {
    // ... rest of the resolve config
    fallback: {
      "https": false,
      "fs" : false,

    }
  },
    
	// Other rules...
	plugins: [
		new NodePolyfillPlugin()
	]
}