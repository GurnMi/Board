


function checkInputEmpty($target){
		
	var errorCount =0;
	
	$target.each(function(i, obj){
		if($(obj).val() == ""){
			var $next  = $(obj).next(".error");
			$next.css("display","block");
			errorCount++;
		}
	})
	if(errorCount>0){
		return false;
	}
	return true;
}