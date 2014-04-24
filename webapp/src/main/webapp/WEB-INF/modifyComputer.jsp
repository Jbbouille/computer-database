<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="main">
	<h1><spring:message code="label.updateComputer" /></h1>

	<div class="panel panel-default col-md-4 col-md-offset-4">
		<div class="panel-body">
			<form:form id="frm" commandName="computerDto" action="modifycomputer" method="POST">
				<div class="form-group">
					<label for="name"><spring:message code="label.computerName" /></label>
					<form:input type="hidden" class="form-control" path="id" value="${computerDto.id}"/>
					<form:input class="form-control" path="name" value="${computerDto.name}"/>
					<form:errors path="name" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="introduced"><spring:message code="label.computerIntroduced" /></label>
					<form:input class="form-control" path="introduced" id="datepickerIntroduced" value="${computerDto.introduced}"/>
					<form:errors path="introduced" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="discontinued"><spring:message code="label.computerDiscontinued" /></label>
					<form:input class="form-control" path="discontinued" id="datepickerDiscontinued" value="${computerDto.discontinued}"/>
					<form:errors path="discontinued" cssClass="error" />
				</div>
				<label for="company"><spring:message code="label.computerCompany" /></label>
				<div class="controls" >
					<form:select  class="form-control" path="companyId">
						<form:option value="-1" label="--- Select ---" />
						<form:options items="${companies}" itemLabel="name" itemValue="id"/>
					</form:select>
					<form:errors path="companyId" cssClass="error"/>
				</div>
				<br>
				<button type="submit" class="btn btn-default"><spring:message code="label.submit" /></button>
				or <a class="btn btn-success" id="cancel" href="dashboard"><spring:message code="label.cancel" /></a>
			</form:form>
		</div>
	</div>
</section>
<jsp:include page="include/footer.jsp" />