const template = require('./search-events.tpl.html')

class SearchEventsController {
  $onInit () {
    // start by displaying all the events
    this.results = this.events
    this.selected = { name: 'None Selected' }
  }

  onKeywordChanged () {
    const re = new RegExp(this.keyword, 'i')
    this.results = this.events.filter(e => re.test(e.name))
  }

  onEventSelected (event) {
    this.selected = event
  }
}

module.exports = {
  bindings: {
    events: '<'
  },
  controller: SearchEventsController,
  template
}
