<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/navbar.ftl">
    <h5>Hello, Guest!</h5>
    <p>This is a simple web page with Weather Service from OWM</p>

    <#include "parts/generalweather.ftl">
    <#include "parts/detailedweather.ftl">
    <#include "parts/footer.ftl">
</@c.page>
