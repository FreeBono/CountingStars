const NodePolyfillPlugin = require("node-polyfill-webpack-plugin")

module.exports = {
  resolve: {
    // ... rest of the resolve config
    fallback: {
      "https": false,
    }
  },
	// Other rules...
	plugins: [
		new NodePolyfillPlugin()
	]
}