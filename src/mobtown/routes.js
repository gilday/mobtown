module.exports = ['$stateProvider', function ($stateProvider) {
  $stateProvider.state({
    name: 'home',
    url: '',
    component: 'home',
    resolve: {
      events: ['events', events => events.all()]
    }
  })
}]
