<html lang="en">
<head>
    <title>date</title>
</head>
<body>
<div>
    日期: ${date?date}
    时间: ${date?time}
    日期时间: ${date?datetime}
    指定格式: ${date?string("yyyy-MM-dd HH:mm:ss")}
</div>

</body>
</html>