<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags"%>

<section id="main">
	<h1 id="homeTitle">
		<c:out value="${numberOfComputers} Computers Found"></c:out>
	</h1>
	<div id="actions">
		<form action="dashboard" method="GET" class="form-inline">
			<input type="search" id="searchbox" name="search"
				placeholder="Search name" class="form-control" value="${search}"> <input
				type="submit" id="searchsubmit" value="Filter by name"
				class="btn btn-primary">
		</form>
		<a class="btn btn-success" id="add" href="addcomputer">Add
			Computer</a>
		<m:pagination currentPage="${currentPage}"
			numberOfPages="${numberOfPages}" search="${search}"></m:pagination>
	</div>



	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th><a href="dashboard?search=${search}&orderby=name">Computer Name</a></th>
				<th><a href="dashboard?search=${search}&orderby=introduced">Introduced Date</a></th>
				<th><a href="dashboard?search=${search}&orderby=discontinued">Discontinued Date</a></th>
				<th><a href="dashboard?search=${search}&orderby=company">Company</a></th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="companie" value="${companies}"></c:set>
			<c:forEach var="computer" items="${computers}">
				<tr>
					<td><a href="modifycomputer?id=${computer.id}"><c:out
								value="${computer.name}" /></a></td>
					<td><c:out value="${computer.introduced}" /></td>
					<td><c:out value="${computer.discontinued}" /></td>
					<c:if test="${computer.companyId != null }">
						<td><c:out value="${companie.get(computer.companyId).name}" /></td>
					</c:if>
					<td><a href="deletecomputer?id=${computer.id}"
						class="btn btn-warning">Delete</a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>

<jsp:include page="include/footer.jsp" />
