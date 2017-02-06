const path = require('path')
const ArchivePlugin = require('webpack-archive-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const webpack = require('webpack')

module.exports = {
  devServer: {
    proxy: {
      '/api/**': {
        // proxy to local mobtown service
        target: 'http://localhost:8000'
      }
    }
  },
  entry: path.join(__dirname, 'src', 'index.js'),
  module: {
    loaders: [{
      test: /\.scss$/,
      loader: 'style-loader!css-loader!sass-loader'
    }, {
      test: /\.js$/,
      exclude: /node_modules/,
      loader: 'babel-loader'
    }, {
      test: /\.html$/,
      loader: 'raw-loader'
    }, {
      test: /\.(ttf|eot|svg|woff(2)?)(\?[a-z0-9=&.]+)?$/,
      loader: 'file-loader'
    }]
  },
  output: {
    path: path.join(__dirname, 'build', 'web'),
    filename: 'bundle.js'
  },
  plugins: [
    new ArchivePlugin({ format: 'tar', output: path.join(__dirname, 'build', 'dist', 'mobtown-web') }),
    new HtmlWebpackPlugin(),
    new webpack.DefinePlugin({
      MOBTOWN_GMAPS_API_KEY: JSON.stringify(process.env.MOBTOWN_GMAPS_API_KEY)
    })
  ]
}
