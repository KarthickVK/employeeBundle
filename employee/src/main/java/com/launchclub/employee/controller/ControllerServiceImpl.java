package com.launchclub.employee.controller;

import com.launchclub.employee.model.Employee;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implement http methods using rest-api.
 */
@Component(service = ControllerService.class)
public class ControllerServiceImpl implements ControllerService {

    private static final EmployeeController employeeController = EmployeeController.getInstance();

    /**
    *Post using rest-api.
     */
    @Override
    @Path("/post")
    @Produces("application/json")
    @POST
    public boolean postEmployee(final Employee employee) {
        return employeeController.createEmployee(employee);
    }

    /**
     * Put using rest-api.
     */
    @Override
    @Path("/put")
    @Consumes("application/json")
    @PUT
    public boolean putEmployee(final Employee employeeDetails) {
        return employeeController.updateEmployee(employeeDetails);
    }

    /**
     *Delete using rest-api.
     */
    @Override
    @Path("/{employeeId}")
    @Produces("application/json")
    @DELETE
    public boolean deleteEmployee(@PathParam("employeeId") final String employeeId) {
        return employeeController.deleteEmployee(employeeId);
    }

    /**
     *Get using rest-api.
     */
    @Override
    @Path("/get")
    @Produces("application/json")
    @GET
    public List<Employee> getEmployees(@QueryParam("page") final int page, @QueryParam("limit") final int limit) {

        if(page == 0 && limit == 0){
            return employeeController.showAllEmployees();
        }

        if(page > 0 && limit >= 0)
        {
            return employeeController.pagination(page, limit);
        }else if(page <= 0) {
            return new ArrayList<Employee>();
        }
        return employeeController.showAllEmployees();
    }
}
