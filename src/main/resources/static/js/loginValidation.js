$(document).ready(function(){
    $('#login').submit( function(event)
    {
        $(".error").remove();
        var loginData = {
            email: $("#login-login").val(),
            password: $("#password-password").val(),
        };

        $.post(
            "/login",
            loginData,
            func,
            "json"
        );

        function func(responseData)
        {
            if (responseData === "Логин или пароль неверны")
                    $("#login-from-login-form").append(
                        '<div class="error">' + "Логин или пароль неверны" + '</div>'
                    )
        }
        event.preventDefault();
    });
});