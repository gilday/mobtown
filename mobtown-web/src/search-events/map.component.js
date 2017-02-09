const config = require('../config')
const template = require('./map.tpl.html')

class MapController {
  constructor () {
    this.googleMapsUrl = `https://maps.googleapis.com/maps/api/js?key=${config.gmapsAPIKey}`
    this.center = [39.287289, -76.612474] // inner harbor
    this.zoom = 12
  }
}

module.exports = {
  controller: MapController,
  bindings: {
    event: '<'
  },
  template
}
