const config = require('../config')
const template = require('./map.tpl.html')

class MapController {
  constructor () {
    this.gmapsAPIKey = config.gmapsAPIKey
    this.zoom = 12
  }

  $onInit () {
    console.log('initialized with param ', this.event)
  }
}

module.exports = {
  controller: MapController,
  bindings: {
    event: '<'
  },
  template
}
