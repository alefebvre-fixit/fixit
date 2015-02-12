angular.module('fixit').filter(
	'myContributions',
	function() {
	    return function(card, user) {
		var filtered = [];
		var arrayLength = card.proposals.length;
		for (var i = 0; i < arrayLength; i++) {
		    var subArrayLength = Object
			    .keys(card.proposals[i].contributions).length;
		    for (var j = 0; j < subArrayLength; j++) {
			var contribution = card.proposals[i].contributions[j];

			if (user.username == contribution.contributor
				&& contribution.status != 'Cancel') {
			    filtered.push(contribution);
			}
		    }
		}

		return filtered;

	    };
	}).filter(
	'otherContributions',
	function() {
	    return function(card, user) {
		var filtered = [];
		var arrayLength = card.proposals.length;
		for (var i = 0; i < arrayLength; i++) {
		    var subArrayLength = Object
			    .keys(card.proposals[i].contributions).length;
		    for (var j = 0; j < subArrayLength; j++) {
			var contribution = card.proposals[i].contributions[j];
			if (user.username != contribution.contributor
				&& contribution.status != 'Cancel') {
			    filtered.push(contribution);
			}
		    }
		}

		return filtered;

	    };
	});
