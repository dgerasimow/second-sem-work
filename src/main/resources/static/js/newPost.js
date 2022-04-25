$(document).ready(function ()
{
    $('#create-post-form').submit( function(event)
    {
        console.log($("#post-textarea").val())
        var newPostData = {
            postText: $("#post-textarea").val(),
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
           if (responseData.success) {

               $("#post").append(
                   '<div class="post-content">' +
                   '<img src="http://placehold.it/1920x1280" alt="post-image" class="img-responsive post-image"/>' +
                   '<div class="post-container">' +
                   '<img src="http://placehold.it/300x300" alt="user" class="profile-photo-md pull-left" />' +
                   '<div class="post-detail">' +
                   '<div class="user-info">' +
                   '<h5><a href="timeline.html" class="profile-link">' + responseData.first_name +' '+ responseData.second_name + '</a> <span class="following">following</span></h5>' +
                   '<p class="text-muted">' + responseData.creation_time + '</p>' +
                   '</div>' +
                   '<div class="reaction">' +
                   '<a class="btn text-green"><i class="icon ion-thumbsup"></i> 13</a>' +
                   '<a class="btn text-red"><i class="fa fa-thumbs-down"></i> 0</a>' +
                   '</div>' +
                   '<div class="line-divider"></div>' +
                   '<div class="post-text">' +
                   '<p>' + responseData.post_text + '</p>' +
                   '</div>' +
                   '<div class="line-divider"></div>' +
                   '</div>' +
                   '</div>' +
                   '</div>'
               )
           }
       }
       event.preventDefault();
    });
});