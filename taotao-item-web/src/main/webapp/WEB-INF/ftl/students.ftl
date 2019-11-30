<html lang="en">
<head>
    <title>list</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>序号</th>
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>住址</th>
        </tr>
        <#list students as stuent>
            <#if stuent_index % 2 == 0>
                <tr bgcolor="red">
            <#else >
                <tr bgcolor="blue">
            </#if>
            <td>${stuent_index}</td>
            <td>${stuent.id}</td>
            <td>${stuent.name}</td>
            <td>${stuent.age}</td>
            <td>${stuent.address}</td>
            </tr>
        </#list>
    </table>
</div>

</body>
</html>