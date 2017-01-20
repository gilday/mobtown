const config = require('../config')
const template = require('./map.tpl.html')

class MapController {
  constructor () {
    this.gmapsAPIKey = config.gmapsAPIKey
  }
}

module.exports = {
  controller: MapController,
  template
}
