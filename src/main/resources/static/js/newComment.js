$(document).ready(function ()
{
    $("#add-new-comment").submit(function (event) {

        var commentData = {
            post_id: $("#post-id").val(),
            comment_text: $("#comment-text").val(),
            user_id: $("#user-id").val()
        }

        $.post(
            "/comment",
            commentData,
            func,
            "json"
        );

        $("#comment-text").val("")
        function func(responseData)
        {
            if (responseData.success) {
                $("#last-divider").append(
                    '<div class="post-comment">' +
                    '<img src="http://placehold.it/300x300" alt="" class="profile-photo-sm" />' +
                    '<p><a href="timeline.html" class="profile-link">' + responseData.firstName + '</a>' + '  ' + responseData.commentText + '</p>' +
                    '</div>'
                )
            }
        }

        event.preventDefault();
        });
});