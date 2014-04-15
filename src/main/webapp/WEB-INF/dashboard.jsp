<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="main">
	<h1 id="homeTitle">
		<c:out value="${numberOfComputers} "></c:out>
		<spring:message code="label.numberComputer" />
	</h1>
	<div id="actions">
		<form action="dashboard" method="GET" class="form-inline">
			<input type="search" id="searchbox" name="search"
				placeholder="<spring:message code="label.searchName" />"
				class="form-control" value="${search}"> <input type="submit"
				id="searchsubmit"
				value="<spring:message code="label.filterByName" />"
				class="btn btn-primary">
		</form>
		<div class="row">
			<div class="col-md-8"></div>
			<div class="col-md-4">
				<c:if test="${deleteError != null}">
					<label class="error" for="company"> <c:out
							value="${deleteError}"></c:out>
					</label>
				</c:if>
			</div>
		</div>
		<a class="btn btn-success" id="add" href="addcomputer"><spring:message
				code="label.addComputer" /></a>
		<m:pagination currentPage="${currentPage}"
			numberOfPages="${numberOfPages}" search="${search}"
			orderBy="${orderby}" bool="${bool}"></m:pagination>
	</div>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th class="col-md-2"><a
					href="dashboard?search=${search}&orderby=name&bool=${orderby=='name' ? !bool:'false'}"><spring:message
							code="label.computerName" /></a></th>
				<th class="col-md-2"><a
					href="dashboard?search=${search}&orderby=introduced&bool=${orderby=='introduced' ? !bool:'false'}"><spring:message
							code="label.computerIntroduced" /></a></th>
				<th class="col-md-2"><a
					href="dashboard?search=${search}&orderby=discontinued&bool=${orderby=='discontinued' ? !bool:'false'}"><spring:message
							code="label.computerDiscontinued" /></a></th>
				<th class="col-md-2"><a
					href="dashboard?search=${search}&orderby=company&bool=${orderby=='company' ? !bool:'false'}"><spring:message
							code="label.computerCompany" /></a></th>
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
						href="deletecomputer?id=${computer.id}" class="btn btn-warning"
						onClick="return confirm( 'Are you sure to delete' )"><spring:message
								code="label.delete" /></a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<jsp:include page="include/footer.jsp" />
