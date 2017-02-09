module.exports = ['$stateProvider', function initializeStateProvider ($stateProvider) {
  $stateProvider.state({
    name: 'home',
    component: 'mtHome',
    url: '',
    resolve: {
      events: ['eventsService', events => events.all()]
    }
  })
  $stateProvider.state({
    name: 'home.event',
    component: 'mtMap',
    params: {
      permitID: undefined
    },
    resolve: {
      event: ['eventsService', '$transition$', (events, $transition$) => events.get($transition$.params().permitID)]
    }
  })
}]
