let permitID = 0

/**
 * factory for succinctly creating a fake data point
 */
function event (name, type, start, end, arrestsCount) {
  permitID++
  return {
    permitID,
    name,
    type,
    start: new Date(start),
    end: new Date(end),
    arrestsCount
  }
}

const events = [
  event('PRATT STREET ALE HOUSE Orioles Opening Day', 'Race', '2016-05-18', '2016-05-18', 58),
  event('Walk to End Alzheimer\'s', 'Race', '2016-04-18', '2016-05-18', 47),
  event('BALTIMORE INVITATIONAL REGATTA', 'Festival', '2016-06-18', '2016-05-18', 36),
  event('HEALTH FREEDOM CELEBRATION WALK', 'Festival', '2016-01-18', '2016-05-18', 34),
  event('Quigleys 1/2 Irish Pub Opening Day Celebration', 'Festival', '2016-02-18', '2016-05-18', 22),
  event('SOLE OF THE CITY 10K', 'Race', '2016-08-18', '2016-05-18', 87),
  event('Heavy Seas Island Jam', 'Unknown', '2016-05-18', '2016-05-18', 33),
  event('ORIOLES GAME (JULY, 2015)', 'Unknown', '2016-05-18', '2016-05-18', 21),
  event('Hamilton Baseball Opening Day Parade', 'Unknown', '2016-05-18', '2016-05-18', 12),
  event('KENTUCKY DERBY BLOCK PARTY', 'Block Party', '2016-05-18', '2016-05-18', 4)
]

module.exports = function fakeEventsServiceFactory ($q) {
  return {
    all () {
      return $q.resolve(events)
    },

    get (permitID) {
      const pub = {
        name: 'Pub Dog',
        arrests: [{
          location: [39.277008, -76.613461]
        }]
      }
      const union = {
        name: 'Union'
      }
      console.log('id ', permitID)
      const event = permitID === 1 ? union : pub
      return $q.resolve(event)
    }
  }
}
