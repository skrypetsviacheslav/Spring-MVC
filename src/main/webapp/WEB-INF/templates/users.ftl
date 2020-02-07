<#include "main-template.ftl"/>

<#macro content>
    <#list users >
        <ul>
            <#items as user>
                <li>${user.name} ${user.surname} ${user.email}</li>
            </#items>
        </ul>
    <#else >
        <p>No users</p>
    </#list>
    <a href="/logout">Logout</a>
</#macro>

<@main title="Users"/>