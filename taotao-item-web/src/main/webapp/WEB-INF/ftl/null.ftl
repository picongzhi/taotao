<html lang="en">
<head>
    <title>null</title>
</head>
<body>
<div>
    <#include "hello.ftl">
    ${null!"null"}
    <#if null??>
        ${null}
    <#else>
        null
    </#if>
</div>

</body>
</html>