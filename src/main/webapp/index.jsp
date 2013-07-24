<% String ctx = request.getContextPath(); %>
<!doctype html>
<html>
<head>
    <title>Jobengine API</title>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-responsive.min.css">
    <style type="text/css">
        .navbar .brand {
            font-family: "Helvetica Rounded Bold", "Helvetica";
            font-weight: bold;
        }
        .navbar .job {
            color: black;

        }
        .navbar .rapido {
            color: #2889c2;
        }
    </style>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <a class="brand" href="http://www.jobrapido.com"><span class="job">job</span><span class="rapido">rapido</span></a>
        <ul class="nav">
            <li class="active"><a href="<%=ctx%>/">Jobengine API</a></li>
            <li><a href="<%=ctx%>/swagger">API Documentation</a></li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="hero-unit">
        <h1>Jobengine API</h1>
        <h2>API layer to access Jobengine services</h2>
        <p><em>0.0.1 alpha</em></p>
    </div>
</div>
<div class="container">
    <div class="content">
        <jsp:include page="index.html"/>
    </div>
</div>
</body>
</html>