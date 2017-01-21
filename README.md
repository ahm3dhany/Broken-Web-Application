# **broken-web-application**
Spring Web-App that contains five different flaws -from the OWASP 2013 Top 10 List- and their fixes.

## _Vulnerability:_ **A3-Cross-Site Scripting (XSS)**

> XSS flaws occur whenever an application takes untrusted data and sends it to a web browser without proper validation or escaping. XSS allows attackers to execute scripts in the victim’s browser which can hijack user sessions, deface web sites, or redirect the user to malicious sites.

### _required steps to reproduce:_ 

1. Navigate to Six-Word Sories page (e.g. http://localhost:8080/sixWordStories).
2. Insert any text in the input field next to "Title:" (e.g. xss).
3. In the input field next to "Story:", write: 

`</noscript><br><code onmouseover=a=eval;b=alert;a(b(/XSS/.source));>MOVE MOUSE OVER THIS AREA</code>`

and click on "Add". 
4. You can see that new Story has been added and if you hover your mouse on it you got a Popup message-box that says "XSS".

### _where the vulnerability came from:_

First of all, We use "Thymeleaf" as our template engine. So in "sixWordStories.html", there is unescaped element in the template:

`<td th:utext="${post.content}"></td>`

here the "th:utext" (for "unescaped text") caused the XSS vulnerability, because it tells the "Thymeleaf" not to escape the text which may contain malicious javascript code.

### _how to fix it:_

Simply you can use `th:text` instead of `th:utext`. `th:text` is the default behaviour of "Thymeleaf" which makes sure that text should be escaped.