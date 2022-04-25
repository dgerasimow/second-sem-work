<html lang="en">
<#include "base.ftl">
<#macro title>Профиль</#macro>

<#macro content>
    <h1>Sign up!</h1>
    <form action="/register"  method="post">
        First Name:
        <input name="firstName" type="text">
        <br>
        Last Name:
        <input name="lastName" type="text">
        <br>
        Login:
        <input name="login" type="text">
        <br>
        Password:
        <input name="password" type="text">
        <br>
        <input type="submit", value="Sign up!">
    </form>
</#macro>
</html>