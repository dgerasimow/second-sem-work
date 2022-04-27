$(document).ready(function ()
{
    $('#register').submit( function(event)
    {
        $('.error').remove();
        var registerData = {
            firstName: $("#firstname").val(),
            secondName: $("#lastname").val(),
            login: $("#register-login").val(),
            password: $("#register-password").val()
        }

        $.post(
            "/registration",
            registerData,
            func,
            "json"
        );

        function func(responseData)
        {
            console.log(responseData);
            if (!responseData.success) {
                if(responseData.errors.firstNameEmpty) {
                    $("#firstName-from-register-form").append(
                        '<div class="error">' + responseData.errors.firstNameEmpty + '</div>'
                    )
                }
                if(responseData.errors.secondNameEmpty) {
                    $("#secondName-from-register-form").append(
                        '<div class="error">' + responseData.errors.secondNameEmpty + '</div>'
                    )
                }
                if(responseData.errors.loginEmpty) {
                    $("#login-from-register-form").append(
                        '<div class="error">' + responseData.errors.loginEmpty + '</div>'
                    )
                }
                if(responseData.errors.passwordEmpty) {
                    $("#password-from-register-form").append(
                        '<div class="error">' + responseData.errors.passwordEmpty + '</div>'
                    )
                }
                if(responseData.errors.passwordRegexp) {
                    $("#password-from-register-form").append(
                        '<div class="error">' + responseData.errors.passwordRegexp + '</div>'
                    )
                }
            } else {
                $("#register").append(
                    '<div class="success">' + "Вы успешно зарегистрированы!" + '</div>'
                )
            }
        }
        event.preventDefault();
    });
});