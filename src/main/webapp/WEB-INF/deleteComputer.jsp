<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<section id="main">
	<h1>Delete Computer</h1>
	<c:out value="${computer.name}"></c:out>
	<form action="deletecomputer" method="POST">
		<button type="submit" class="btn btn-default">Delete</button>
		or <a class="btn btn-success" id="cancel" href="dashboard">Cancel</a>
		<input type="hidden" name="idComputer" value="${computer.id}">
	</form>
</section>

<jsp:include page="include/footer.jsp" />