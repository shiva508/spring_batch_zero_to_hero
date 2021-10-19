console.log("Hello world!");

$(document).ready(function() {

	$("#search-form").submit(function(event) {

		//stop submit the form, we will post it manually.
		event.preventDefault();


	});
	$("#exampleFormControlFile1").change(function() {
		display(this);
		Swal.fire('Any fool can use a computer')

	});

	function display(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(event) {
				$('#myid').attr('src', event.target.result);
				console.log(event.target.result)
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
});
