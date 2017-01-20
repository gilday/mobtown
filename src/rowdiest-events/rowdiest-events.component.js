const template = require('./rowdiest-events.tpl.html')
require('./styles.scss')

module.exports = {
  bindings: {
    events: '<'
  },
  controller: class RowdiestEventsController { },
  template
}
