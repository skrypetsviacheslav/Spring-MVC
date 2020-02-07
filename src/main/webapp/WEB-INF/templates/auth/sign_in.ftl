<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<form action="/login/process" method="post">
    <div>
        Email: <input name="email" type="email">
    </div>
    <div>
        Password: <input name="password" type="password">
    </div>
    <input type="submit">
    <#if error??>
        <p>Bad credential</p>
    </#if>
</form>
</body>
</html>
