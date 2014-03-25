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
		console.log( "ready!" );
		$.validator
		.addMethod(
				"dateControl",
				function(value, element) {
					return value.match(/^$/)
							|| value
									.match(/^(\d{4})([\/-])(0[1-9]|1[012])\2(0[1-9]|[12][0-9]|3[01])$/);
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
console.log( "ready2" );
		});
	
});
