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
				placeholder="Search name" class="form-control" value="${search}">
			<input type="submit" id="searchsubmit" value="Filter by name"
				class="btn btn-primary">
		</form>
		<a class="btn btn-success" id="add" href="addcomputer">Add
			Computer</a>
		<m:pagination currentPage="${currentPage}"
			numberOfPages="${numberOfPages}" search="${search}"
			orderBy="${orderby}" bool="${bool}"></m:pagination>
	</div>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th class="col-md-2"><a
					href="dashboard?search=${search}&orderby=name&bool=${orderby=='name' ? !bool:'false'}">Computer
						Name</a></th>
				<th class="col-md-2"><a
					href="dashboard?search=${search}&orderby=introduced&bool=${orderby=='introduced' ? !bool:'false'}">Introduced
						Date</a></th>
				<th class="col-md-2"><a
					href="dashboard?search=${search}&orderby=discontinued&bool=${orderby=='discontinued' ? !bool:'false'}">Discontinued
						Date</a></th>
				<th class="col-md-2"><a
					href="dashboard?search=${search}&orderby=company&bool=${orderby=='company' ? !bool:'false'}">Company</a></th>
				<th class="col-md-2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="companie" value="${companies}"></c:set>
			<c:forEach var="computer" items="${computers}">
				<tr>
					<td class="col-md-2"><a
						href="modifycomputer?id=${computer.id}"><c:out
								value="${computer.name}" /></a></td>
					<td class="col-md-2"><c:out value="${computer.introduced}" /></td>
					<td class="col-md-2"><c:out value="${computer.discontinued}" /></td>
					<c:if test="${computer.companyId != null }">
						<td><c:out value="${companie.get(computer.companyId).name}" /></td>
					</c:if>
					<td class="col-md-2"><a
						href="deletecomputer?id=${computer.id}" class="btn btn-warning">Delete</a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<jsp:include page="include/footer.jsp" />
