module.exports = function follow(api, rootPath, relArray) {
	const root = api({
		method: 'GET',
		path: rootPath,
		headers: { 
					'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njc4MzQwNDgsInVzZXJfbmFtZSI6InZhbmVzc2EiLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjAzMDY1NmQyLTUzYTUtNDkxNS1iM2ZjLTQxOTc5MTMxNmQ1ZSIsImNsaWVudF9pZCI6ImZvb0NsaWVudElkIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.sdoECnSgOMTGsplvGPxCmHBrcbp6Eswkpo-2onQqWAA' 
				}
	});

	return relArray.reduce(function(root, arrayItem) {
		const rel = typeof arrayItem === 'string' ? arrayItem : arrayItem.rel;
		return traverseNext(root, rel, arrayItem);
	}, root);

	function traverseNext (root, rel, arrayItem) {
		return root.then(function (response) {
			if (hasEmbeddedRel(response.entity, rel)) {
				return response.entity._embedded[rel];
			}

			if(!response.entity._links) {
				return [];
			}

			if (typeof arrayItem === 'string') {
				return api({
					method: 'GET',
					path: response.entity._links[rel].href,
					headers: { 
						'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njc4MzQwNDgsInVzZXJfbmFtZSI6InZhbmVzc2EiLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjAzMDY1NmQyLTUzYTUtNDkxNS1iM2ZjLTQxOTc5MTMxNmQ1ZSIsImNsaWVudF9pZCI6ImZvb0NsaWVudElkIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.sdoECnSgOMTGsplvGPxCmHBrcbp6Eswkpo-2onQqWAA' 
					}
				});
			} else {
				return api({
					method: 'GET',
					path: response.entity._links[rel].href,
					params: arrayItem.params,
					headers: { 
						'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njc4MzQwNDgsInVzZXJfbmFtZSI6InZhbmVzc2EiLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjAzMDY1NmQyLTUzYTUtNDkxNS1iM2ZjLTQxOTc5MTMxNmQ1ZSIsImNsaWVudF9pZCI6ImZvb0NsaWVudElkIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.sdoECnSgOMTGsplvGPxCmHBrcbp6Eswkpo-2onQqWAA' 
					}
				});
			}
		});
	}

	function hasEmbeddedRel (entity, rel) {
		return entity._embedded && entity._embedded.hasOwnProperty(rel);
	}
};
