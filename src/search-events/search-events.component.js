const template = require('./search-events.tpl.html')

class SearchEventsController {
  constructor (events) {
    this.events = events
  }

  $onInit () {
    // start by displaying all the events sorted by permit ID
    this.results = this.events
    this.sortBy = 'permitID'
    this.reverse = false
  }

  onKeywordChanged () {
    const re = new RegExp(this.keyword, 'i')
    this.results = this.events.filter(e => re.test(e.name))
  }

  select (event) {
    console.log('selected event ', event)
  }

  sort (sortBy) {
    if (this.sortBy === sortBy) {
      // change direction
      this.reverse = !this.reverse
    } else {
      // sort by new column
      this.sortBy = sortBy
    }
  }
}

module.exports = {
  bindings: {
    events: '<'
  },
  controller: ['events', SearchEventsController],
  template
}
