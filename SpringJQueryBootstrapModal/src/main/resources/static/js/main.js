/**
 * 
 */
$(document).ready(function() {
	$('.newAddBookBtn, .table .editBtn').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');
		var text = $(this).text();
		if (text == 'Edit') {
			$("label.error").hide();
			$(".error").removeClass("error");
			$("#saveeditbutton").prop("value", "Update");
			$.get(href, function(addressbook, status) {
				$('.myForm #id').val(addressbook.id);
				$('.myForm #firstname').val(addressbook.firstname);
				$('.myForm #lastname').val(addressbook.lastname);
				$('.myForm #phonenumber').val(addressbook.phonenumber);
				$('.myForm #email').val(addressbook.email);
				$('.myForm #address').val(addressbook.address);
			});

			$('.myForm #exampleModal').modal();
		} else {
			$("label.error").hide();
			$(".error").removeClass("error");
			$("#saveeditbutton").prop("value", "Save");
			$('.myForm #id').val('');
			$('.myForm #firstname').val('');
			$('.myForm #lastname').val('');
			$('.myForm #phonenumber').val('');
			$('.myForm #email').val('');
			$('.myForm #address').val('');
			$('.myForm #exampleModal').modal();
		}
	});

	$('.table .delBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#myModal #delRef').attr('href', href);
		$('#myModal').modal();
	});
	
	$('.modal .saveBtn').on('click', function(event) {
		event.preventDefault();
//		alert("modal save");
		var href = $(this).attr('href');
//		alert(href);
//		alert($('.myForm #id').val());
//		alert($('.myForm #firstname').val());
		var formdata = $('#addressbookForm').serializeArray();
//		alert(formdata);
		
		$.ajax({
			url: href,
			type: "POST",
			data: formdata,
//			dataType: "json",
//			dataType: "text",
//			contentType: "application/json",
			async: true,
			timespan: 1000,
			})
            .done(function (data1, textStatus, jqXHR) {
              console.log(jqXHR.status); // 200
              console.log(textStatus); // success
              $('.myForm #firstname').val('');
              $('.myForm #exampleModal').modal();
              alert(data1);
              console.log(jqXHR);
            })
            .fail(function (jqXHR, textStatus, errorThrown) {
              console.log(jqXHR.status); //例：404
              console.log(textStatus); //例：error
              console.log(errorThrown); //例：NOT FOUND
            })
            .always(function () {
              console.log("complete"); // complete
            });
        });

	// $('#myTable').DataTable();

});