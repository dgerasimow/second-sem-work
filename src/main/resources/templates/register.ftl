<html lang="en">
<#include "base.ftl">
<#macro title>Registration</#macro>

<#macro content>
    <br>
    <br>
    <br>
    <h1>Sign up!</h1>
    <form action="/registration"  method="post">
        First Name:
        <input name="firstName" type="text">
        <br>
        Last Name:
        <input name="lastName" type="text">
        <br>
        Email:
        <input name="email" type="text">
        <br>
        Password:
        <input name="password" type="password">
        <br>
        <input type="submit" value="Sign up!">
    </form>
</#macro>
</html>