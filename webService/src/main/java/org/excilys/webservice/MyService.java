package org.excilys.webservice;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.excilys.model.Company;
import org.excilys.model.Computer;
import org.springframework.stereotype.Service;

@Path("/v1")
@Produces("application/json")
@Service("myWebService")
public interface MyService {

	@POST
	@Path("/computer")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	void insertComputer(Computer myComputer);

	@DELETE
	@Path("/computer")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	void deleteComputer(Computer myComputer);

	@PUT
	@Path("/computer")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	void updateComputer(Computer myComputer);

	@GET
	@Path("/user/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	Computer selectComputer(@PathParam("id") Integer id);

	@GET
	@Path("/computers/{like}/{bool}/{orderby}/{currentpage}/{cbpage}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	ArrayList<Computer> selectComputers(@PathParam("like") String myLikeParam,
			@PathParam("bool") Boolean bool,
			@PathParam("orderby") String orderBy,
			@PathParam("currentpage") int currentPage,
			@PathParam("cbpage") int NUMBER_OF_COMPUTER_BY_PAGE);

	@GET
	@Path("/countcomputers/{like}/{bool}/{orderby}/{currentpage}/{cbpage}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	int countNumberOfComputers(@PathParam("like") String myLikeParam,
			@PathParam("bool") Boolean bool,
			@PathParam("orderby") String orderBy,
			@PathParam("currentpage") int currentPage,
			@PathParam("cbpage") int NUMBER_OF_COMPUTER_BY_PAGE);

	@GET
	@Path("/companies")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	ArrayList<Company> selectCompanies();
}
