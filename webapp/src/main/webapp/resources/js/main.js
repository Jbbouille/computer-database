$(function() {
	function readCookie(name) {
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ')
				c = c.substring(1, c.length);
			if (c.indexOf(nameEQ) == 0)
				return c.substring(nameEQ.length, c.length);
		}
		return null;
	}

	var cookie = readCookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE");

	var pattern = '';
	var errorMessage = '';
	var errorMin = '';

	if (cookie == 'fr') {
		pattern = 'dd-mm-yy';
		errorMessage = 'Entrez une date dans le format '+pattern+'.';
		errorMin = 'Entrez minimum 2 caract&#232;res.';
	} else {
		pattern = 'yy-mm-dd';
		errorMessage = 'Please enter a date in the format '+pattern+'.';
		errorMin = 'Please enter at least 2 characteres';
	}

	$(document).ready(function() {
		$.validator.addMethod("dateControl", function(value, element) {
			try {
				jQuery.datepicker.parseDate(pattern, value);
				return true;
			} catch (e) {
				return false;
			}
		}, errorMessage);

		$('#frm').validate({
			rules : {
				name : {
					minlength : 2
				},
				introduced : {
					dateControl : true
				},
				discontinued : {
					dateControl : true
				}
			}, messages : {
				name : {
					minlength : jQuery.format(errorMin)
				}
			}
		});	
	});
});
