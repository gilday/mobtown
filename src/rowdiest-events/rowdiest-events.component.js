const template = require('./rowdiest-events.tpl.html')
require('./styles.scss')

const fakeEvents = [{
  citations: 57,
  name: 'Heavy Seas Island Jam'
}, {
  citations: 34,
  name: 'Quigleys 1/2 Irish Pub Opening Day Celebration'
}, {
  citations: 12,
  name: 'ORIOLES GAME (JULY, 2015)'
}, {
  citations: 5,
  name: 'Baltimore Pride 2015'
}]

class RowdiestEventsController {
  constructor () {
    this.events = fakeEvents
  }
}

module.exports = {
  controller: RowdiestEventsController,
  template
}
