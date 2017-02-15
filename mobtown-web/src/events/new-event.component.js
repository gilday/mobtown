const template = require('./new-event.tpl.html')

class NewEventController {
  constructor ($state, eventsService) {
    this.$state = $state
    this.eventsService = eventsService
  }

  $onInit () {
    this.event = {}
  }

  onSave () {
    this.eventsService.save(this.event)
      .then(() => {
        this.$state.go('saved', null, { reload: true })
      })
      .catch(() => {
        this.$state.go('failed')
      })
  }

  // TODO add a date range validator to ngModel for start and end
}

module.exports = {
  controller: ['$state', 'eventsService', NewEventController],
  template
}
