<html lang="en">
<#include "base.ftl">
<#macro title>Лента</#macro>

<#macro content>
    <#if posts??>
        <#list posts as p>
            Пост от ${p.creatorName}
            <br>
            ${p.postText}
            <br>
            написан ${p.creationTime}
            <br>
        </#list>
    </#if>
    <h4>Написать свой пост</h4>
    <form action="/posts" method="post">
        <input name="postText" type="text" >
        <br>
        <input type="submit" value="Отправить">
    </form>
</#macro>
</html>