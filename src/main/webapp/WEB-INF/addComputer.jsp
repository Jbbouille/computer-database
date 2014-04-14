<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section id="main">
	<h1>Add Computer</h1>

	<div class="panel panel-default col-md-4 col-md-offset-4">
		<div class="panel-body">
			<form:form id="frm" commandName="computerDto" action="addcomputer" method="POST">
				<div class="form-group">
					<label for="name">Name</label>
					<form:input class="form-control" path="name" />
					<form:errors path="name" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="introduced">Introduced</label>
					<form:input class="form-control" path="introduced" id="datepickerIntroduced"/>
					<form:errors path="introduced" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="discontinued">Discontinued</label>
					<form:input class="form-control" path="discontinued" id="datepickerDiscontinued"/>
					<form:errors path="discontinued" cssClass="error" />
				</div>
				<label for="company">Company Name:</label>
				<div class="controls">
					<form:select  class="form-control" path="companyId">
						<form:option value="-1" label="--- Select ---" />
						<form:options items="${companies}"/>
					</form:select>
					<form:errors path="companyId" cssClass="error" />
				</div>
				<br>
				<button type="submit" class="btn btn-default">Submit</button>
				or <a class="btn btn-success" id="cancel" href="dashboard">Cancel</a>
			</form:form>
		</div>
	</div>
</section>
<jsp:include page="include/footer.jsp" />