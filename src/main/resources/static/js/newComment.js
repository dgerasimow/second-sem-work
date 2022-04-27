$(document).ready(function ()
{
    $("#add-new-comment").submit(function (event) {

        var commentData = {
            postId: $("#post-id").val(),
            text: $("#comment-text").val(),
            userId: $("#user-id").val()
        }

        $.post(
            "/comments",
            commentData,
            func,
            "json"
        );

        $("#comment-text").val("")
        function func(responseData)
        {
                $("#last-divider").append(
                    '<div class="post-comment">' +
                    '<img src="/images/bogdanov-s-telefonom.jpg" alt="" class="profile-photo-sm" />' +
                    '<p><a href="timeline.html" class="profile-link">' + responseData.userFirstName + '</a>' + '  ' + responseData.text + '</p>' +
                    '</div>'
                )
        }

        event.preventDefault();
        });
});