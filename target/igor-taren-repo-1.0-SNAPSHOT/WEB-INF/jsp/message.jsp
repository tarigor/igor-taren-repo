<!DOCTYPE html>
<html lang="en">
<head>
    <title>${pageName}</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .page-name {
            text-align: center;
        }
        .message-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            flex: 1;
        }
    </style>
</head>
<body>
<div class="page-name">
    <h1>${pageName}</h1>
</div>
<div class="message-container">
    <h1>${message}</h1>
    <a href="${pageContext.request.contextPath}/">Back to home</a>
</div>
</body>
</html>