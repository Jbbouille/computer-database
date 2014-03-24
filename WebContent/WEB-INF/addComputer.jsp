<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<section id="main">
	<h1>Add Computer</h1>

	<div class="panel panel-default">
		<div class="panel-body">
			<form action="addcomputer" method="POST">
				<div class="form-group">
					<label for="name">Name</label> <input type="text"
						class="form-control" id="name" placeholder="Enter name">
				</div>
				<div class="form-group">
					<label for="date">Introduced</label> <input type=date
						class="form-control" id="introducedDate"
						placeholder="date introduced">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Discontinued</label> <input
						type="password" class="form-control" id="discontinuedDate"
						placeholder="date discontinued">
				</div>
				<label for="company">Company Name:</label>
				<div class="controls">
					<select class="form-control" name="company">
						<option value=""></option>
						<c:forEach var="company" items="${companies}">
							<option value="${company.key}"><c:out
									value="${company.value.name}" /></option>
						</c:forEach>
					</select>
				</div>
				<br>
				<button type="submit" class="btn btn-default">Submit</button>
				or <a class="btn btn-success" id="cancel" href="dashboard">Cancel</a>
			</form>

		</div>
	</div>


</section>

<jsp:include page="include/footer.jsp" />