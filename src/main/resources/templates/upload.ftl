<html lang="en">
<#include "base.ftl">
<#macro title>Upload file</#macro>

<#macro content>
    <p>Upload file:</p>
    <form action="upload" method="post" enctype="multipart/form-data">
        <input name="file" type="file">
    </form>
</#macro>
</html>