$(function() {
	$(function() {
		$("#datepickerIntroduced").datepicker({
			dateFormat : "yy-mm-dd"
		});
	});
	$(function() {
		$("#datepickerDiscontinued").datepicker({
			dateFormat : "yy-mm-dd"
		});
	});
	$( document ).ready(function() {
		$.validator
		.addMethod(
				"dateControl",
				function(value, element) {
					try{
						jQuery.datepicker.parseDate("yy-mm-dd",value);
						return true;
					}catch (e) {
						return false;
					}
				},
				"Please enter a date in the format yyyy-mm-dd.");

$('#frm').validate({
	rules : {
		name : {
			minlength : 2
		},
		introducedDate : {
			dateControl : true
		},
		discontinuedDate : {
			dateControl : true
		}
	}
});
		});
	
});
