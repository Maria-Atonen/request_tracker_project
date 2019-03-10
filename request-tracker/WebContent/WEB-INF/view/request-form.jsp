<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<head>
	<title>Save Request</title>
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	

</head>
<body class="main">

	<div id="wrapper">
		<div id="header" class="text-center white">
			<h2>Request Manager</h2>
		</div>
		
		<div id="container" class="container">
		<h3 class="white">Save Request</h3>
		
		<form:form action="saveRequest" modelAttribute="request" method="POST">
		<form:hidden path="id" />
		
			<table class="table">
				<tbody>
					<tr>
						<td><label class="white">Description:</label></td>
						<td><form:input class="form-control" path="description"/></td>
						<td><form:errors path="description" class="error" /></td>
					</tr>
					<tr>
						<td><label class="white">Deadline:</label></td>		
						
						<td>

					     			     
				            <div class="form-group">
				                <div class='input-group date' id='datetimepicker1'>
				                    <form:input  path="deadline" type='text' class="form-control" />
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				            </div>
					      
                    	</td>
                    
						<td><form:errors path="deadline" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" class="btn btn-primary" value="Save" class="save"/></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		
		<div style="clear; both;">
		
		<p>
			<a class="white"  href="${pageContext.request.contextPath}/request/list">Back to List</a>
		</p>
		
		</div>
		
	
		</div>
	</div>

        <script type="text/javascript">
            $(function () {
            	var dateNow = new Date();            	
                $('#datetimepicker1').datetimepicker({
                    defaultDate:dateNow
                });
            });
        </script>

</body>

</html>