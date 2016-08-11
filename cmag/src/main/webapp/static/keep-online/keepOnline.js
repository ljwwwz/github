(function($) {
	if ($) {
		//var maxRefreshOnlineStateTime = 1000 * 60 * 60 * 10;// 10小时;
		var intervalRefreshOnlineStateTime = 10*60*1000 ;// 10分钟;  

		try {
			//var maxRefreshOnlineStateInterval = maxRefreshOnlineStateTime/ intervalRefreshOnlineStateTime;
			//var i = 0;
			var refreshOnlineStateInterval;
			$(document).ready(
					function() {
						refreshOnlineStateInterval = setInterval(
								refreshOnlineState,
								intervalRefreshOnlineStateTime);
					});

			function refreshOnlineState() {
				//debugger;
				var url = ctx+"/sys/keepOnline";
				$.get(url);
				//i++;
				//if (i > maxRefreshOnlineStateInterval)
				//	clearInterval(refreshOnlineStateInterval);
			}
		} catch (e) {
		}
	}
})(jQuery);
