# **broken-web-application**
Spring Web-App that contains five different flaws -from the OWASP 2013 Top 10 List- and their fixes.

## _Vulnerability:_ **A3-Cross-Site Scripting (XSS)**

> XSS flaws occur whenever an application takes untrusted data and sends it to a web browser without proper validation or escaping. XSS allows attackers to execute scripts in the victim’s browser which can hijack user sessions, deface web sites, or redirect the user to malicious sites.

### _required steps to reproduce the vulnerability:_ 

1. Navigate to Six-Word Sories page (e.g. http://localhost:8080/sixWordStories).
2. Insert any text in the input field next to "Title:" (e.g. xss).
3. In the input field next to "Story:", write: 
  `</noscript><br><code onmouseover=a=eval;b=alert;a(b(/XSS/.source));>MOVE MOUSE OVER THIS AREA</code>` and click on "Add". 
4. You can see that new Story has been added and if you hover your mouse on it you got a Popup message-box that says "XSS".

### _where the vulnerability came from:_

First of all, We use "Thymeleaf" as our template engine. So in "sixWordStories.html", there is unescaped element in the template:

`<td th:utext="${post.content}"></td>`

here the "th:utext" (for "unescaped text") caused the XSS vulnerability, because it tells the "Thymeleaf" not to escape the text which may contain malicious javascript code.

### _how to fix it:_

Simply you can use `th:text` instead of `th:utext`. `th:text` is the default behaviour of "Thymeleaf" which makes sure that text should be escaped.

## _Vulnerability:_ **A1-Injection**

> Injection flaws, such as SQL, OS, and LDAP injection occur when untrusted data is sent to an interpreter as part of a command or query. The attacker’s hostile data can trick the interpreter into executing unintended commands or accessing data without proper authorization.

### _required steps to reproduce the vulnerability:_

1. Navigate to Quotes page (e.g. http://localhost:8080/quotes).
2. In the input field next to "ID:", type a numeric value (e.g. 15).
3. In the input field next to "Quote:", write:
  `take care'); DROP TABLE Quotes;--` and click on "Post".
4. Now you are redirected to a page that tells you: `Table "QUOTES" not found;`, that's because  DROP TABLE statement removed the table.

### _where the vulnerability came from:_

In the _QuoteService_ class specifically in the _addQuote()_ method, we used the so called "Dynamic Queries" to concatenate data that is supplied by the user -who maybe is a potential attacker- to the query itself. Take a look at the vulnerable code:

` String query = "INSERT INTO Quotes (id, content) VALUES ('" + quote.getId().toString() + "', '" + quote.getContent() + "')";`

` Statement statement = connection.createStatement();`

` statement.execute(query);`

` statement.close();`

After the Malicious Input is supplied (e.g. `15` & `take care'); DROP TABLE Quotes;--`), the query looks like this:

` "INSERT INTO Quotes (id, content) VALUES ('15', 'take care'); DROP TABLE Quotes;--')"`

### _how to fix it:_

SQL injection attacks can be prevented very easy. In our example we'll use "Parameterized Queries". As I wrote my app with Java, I'll use "Prepared Statements". The SQL statement is precompiled and stored in a PreparedStatement object. In order to fix the vulnerability we have to substitute the vulnerable code with this safe code:

` String query = "INSERT INTO Quotes (id, content) VALUES (?, ?)";`

` PreparedStatement pstmt = connection.prepareStatement(query);`

` pstmt.setInt(1, quote.getId());`

` pstmt.setString(2, quote.getContent());`

` pstmt.execute();`

` pstmt.close();`