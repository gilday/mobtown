module.exports = ['$stateProvider', '$urlRouterProvider', function initializeStateProvider ($stateProvider, $urlRouterProvider) {
  $stateProvider.state({
    name: 'home',
    component: 'mtHome',
    url: '/',
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
  $stateProvider.state({
    name: 'new',
    component: 'mtNewEvent',
    url: '/events/new'
  })
  $stateProvider.state({
    name: 'failed',
    component: 'mtFailedEvent'
  })
  $stateProvider.state({
    name: 'saved',
    component: 'mtSavedEvent'
  })
  $urlRouterProvider.otherwise('/')
}]
