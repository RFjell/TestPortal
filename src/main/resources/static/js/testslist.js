function getProviderTests() {
	var provider = $('#providers').find(":selected").val();

	$.getJSON('/tests.json'+'?providerId='+provider, {
		ajax : 'true'
	}, function(data){
		var html = '';
		if(data === null) {
			$('#testlist').html(html);
			return;
		}
		var len = data.length;
		for (let i = 0; i < len; i++) {
			html += '<option value="' + data[i].id + '" >'
			+ data[i].name + '</option>';
		}
		$('#testlist').html(html);
	});
}

function updateTestList(filter) {
	if(filter.length < 1) {
		//Refetch the list of tests
		getProviderTests();
		return;
	}
	//Remove elements that don't match filter
	$('#testlist option').each(function(index){
		if(! $(this).is(':contains('+filter+')') ) {
			$(this).remove();
		}
	});
}

