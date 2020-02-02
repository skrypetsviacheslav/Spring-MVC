<html>
<head>
    <title>Users</title>
</head>
<body>
    <#list users >
        <ul>
            <#items as user>
                <li>${user.name} ${user.surname} ${user.email}</li>
            </#items>
        </ul>
    <#else >
        <p>No users</p>
    </#list>
</body>
</html>
