$(document).ready(function ()
{
    $('#create-post-form').submit( function(event)
    {
        console.log($("#post-textarea").val())
        var newPostData = {
            text: $("#post-textarea").val(),
            currentUserId: $("#userId").val(),
        }

        $.post(
            "/posts",
            newPostData,
            func,
            "json"
        )

       function func(responseData)
       {
           console.log(responseData)

               $("#post").append(
                   '<div class="post-content">' +
                   '<div class="post-container">' +
                   '<img src="/images/bogdanov-s-telefonom.jpg" alt="user" class="profile-photo-md pull-left" />' +
                   '<div class="post-detail">' +
                   '<div class="user-info">' +
                   '<h5><a href="timeline.html" class="profile-link">' + responseData.firstName +' '+ responseData.lastName + '</a> <span class="following">following</span></h5>' +
                   '<p class="text-muted">' + responseData.date + '</p>' +
                   '</div>' +
                   '<div class="reaction">' +
                   '<a class="btn text-green"><i class="icon ion-thumbsup"></i> 13</a>' +
                   '<a class="btn text-red"><i class="fa fa-thumbs-down"></i> 0</a>' +
                   '</div>' +
                   '<div class="line-divider"></div>' +
                   '<div class="post-text">' +
                   '<p>' + responseData.text + '</p>' +
                   '</div>' +
                   '<div class="line-divider"></div>' +
                   '</div>' +
                   '</div>' +
                   '</div>'
               )
           }
       event.preventDefault();
    });
});