<#include "../main-template.ftl"/>

<#macro content>
    <form action="/login/process" method="post">
        <div>
            Email: <input name="email" type="email">
        </div>
        <div>
            Password: <input name="password" type="password">
        </div>
        <input type="submit">
        <a href="/sign_up">Register</a>
        <#if error??>
            <p>Bad credential</p>
        </#if>
    </form>
</#macro>

<@main title="Login"/>