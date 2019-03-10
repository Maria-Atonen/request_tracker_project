<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>List Requests</title>
	
	<link type="text/css" 
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css">
			
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">			
</head>

<body class="main">

<div id="wrapper" >
	<div id="header" class="text-center white">
		<h2>Request Manager</h2>
	</div>
	
	<div id="container" class="container">
		<div id="content">
		
		<input type="button" value="Add Request"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="btn btn-primary"/>
		
		<br></br>
		<c:if test="${not empty requests}">
		<table class="table">
			<tr class="white">
				<th>Description</th>
				<th>Created</th>
				<th>Deadline</th>
				<th>Action</th>
			</tr>
			
			<c:forEach var="tempRequest" items="${requests}">
			
				<c:url var="deleteLink" value="/request/delete">
					<c:param name="requestId" value="${tempRequest.id}"/>
				</c:url>
				
				
				<tr class="white" <c:if test="${tempRequest.overdue}"> style="color:red" </c:if>>
					<td>${tempRequest.description} </td>
					<td>${tempRequest.created} </td>
					<td>${tempRequest.deadline} </td>
					<td>

						<a class="white" href="${deleteLink}">
						Resolve</a>
					</td>
				</tr>		
			
			</c:forEach>
			
		</table>
		</c:if>
		
		</div>
	</div>
</div>
</body>


</html>