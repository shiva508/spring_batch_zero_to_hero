console.log("Hello world!");

$(document).ready(function () {
  getWishesOnload();
  $("#createwish").submit(function (event) {
    event.preventDefault();
    var nameOfWisher = $("#nameOfWisher").val();
  });
  $("#search-form").submit(function (event) {
    event.preventDefault();
    var wisherName = $("#wishername").val();
    var yourwishforfriend = $("#yourwishforfriend").val();
    var wisherimagebase64 = $("#wisher-image-base64").val();
    var wishimagebase64 = $("#wish-image-base64").val();
    inputObject = {};
    inputObject.weWishId = 508;
    inputObject.wisherFriendName = wisherName;
    inputObject.wisherFriendMessaage = yourwishforfriend;
    inputObject.wisherAvtor = wisherimagebase64;
    inputObject.wisherFriendMemory = wishimagebase64;
    console.log(inputObject);
    $.ajax({
      type: "POST",
      contentType: "application/json",
      url: "/wishfriend/createwishfriendrest",
      data: JSON.stringify(inputObject),
      dataType: "json",
      cache: false,
      success: function (data) {
        dynamicHtmlContentGenerator(data);
      },
      error: function (e) {
        console.log(e);
      },
    });
  });

  $("#wisher-image").change(function () {
    display(this, "wisher-image-base64");
    Swal.fire("Any fool can use a computer");
  });
  $("#wish-image").change(function () {
    display(this, "wish-image-base64");
    Swal.fire("Any fool can use a computer");
  });

  function display(input, id) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function (event) {
        $("#" + id + "").attr("value", event.target.result);
        console.log(event.target.result);
      };
      reader.readAsDataURL(input.files[0]);
    }
  }
  function getWishesOnload() {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: "/wishfriend/getallwishfriends",
      dataType: "json",
      cache: false,
      success: function (data) {
        dynamicHtmlContentGenerator(data);
      },
      error: function (e) {
        console.log(e);
      },
    });
  }

  function dynamicHtmlContentGenerator(data) {
    console.log(data);
    var htmlDynamicContent = "";
    var imageDynamicContent = "";
    data.forEach((element) => {
      htmlDynamicContent += '<figure class="testimonial">';
      htmlDynamicContent +=
        '<img class="testimonial-img" alt="' +
        element.wisherFriendName +
        '" src="' +
        element.wisherAvtor +
        '" />';
      htmlDynamicContent +=
        '<blockquote class="testimonial-text">' +
        element.wisherFriendMessaage +
        "</blockquote>";
      htmlDynamicContent +=
        '<p class="testimonial-name">&mdash; ' +
        element.wisherFriendName +
        "</p>";
      htmlDynamicContent += "</figure>";
      imageDynamicContent += '<figure class="gallery-item">';
      imageDynamicContent +=
        '<img src="' +
        element.wisherFriendMemory +
        '" alt="Photo of beautifully arranged food" />';
      imageDynamicContent += "</figure>";
    });
    $(".testimonials").empty();
    $(".testimonials").append(htmlDynamicContent);
    $(".gallery").empty();
    $(".gallery").append(imageDynamicContent);
  }
});
