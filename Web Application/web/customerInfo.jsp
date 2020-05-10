<%-- 
    Document   : search
    Created on : 05-May-2020, 20:46:45
    Author     : MohabOmar
--%>

<%@page import="classes.OnetimeService"%>
<%@page import="classes.Services"%>
<%@page import="java.util.Vector"%>
<%@page import="classes.RecurringService"%>
<%@page import="classes.Customer"%>
<%@page import="WebInterface.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! Customer customer;%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/e46fb9d55b.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    </head>
    <body>

        <% if (request.getParameter("deactive") != null && request.getParameter("deactive").equals("true")) {
        %>
        <script type="text/javascript">
            alert("Service DeActivated.");
        </script>        
        <%
            }
        %>
        <%if (request.getParameter("key") != null) {
                String customerMSISDN = request.getParameter("key");
                Database db = new Database();
                customer = db.getCustomer(customerMSISDN);
                Vector<RecurringService> addedServices = db.getRecurringServices(customer.getCustomerID());
                Vector<OnetimeService> addedServices2 = db.getOnetimeServices(customer.getCustomerID());
                if (customer.getMsisdn() == null) {
                    response.sendRedirect("/BillingProject/main.jsp" + "?customerError=true");
                } else {
        %>  

        <div class="row">
            <div class="col mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="e-profile">
                            <div class="row">
                                <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                                    <div class="text-center text-sm-left mb-2 mb-sm-0">
                                        <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap"><%= customer.getCustomerName()%></h4>
                                        <p class="mb-0"><%= customer.getMsisdn()%></p>
                                        <div class="mt-2">
                                        </div>
                                    </div>

                                </div>

                            </div>
                            <form class="form-inline my-2 my-lg-0" action="services.jsp">
                                <a class="btn btn-outline-success my-2 my-sm-0" data-toggle="pill" href="main.jsp" aria-controls="pills-contact">Main</a>
                                <input class="form-control mr-sm-2" type="hidden" name="key"  value="">
                                <button class="btn btn-outline-success my-2 my-sm-0" data-toggle="pill" type="submit" aria-controls="pills-contact">Add Service</button>
                            </form>
                            <ul class="nav nav-tabs">
                                <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#info" role="tab" aria-controls="pills-home" aria-selected="true">Info: </a>
                            </ul>

                            <div class="tab-content pt-3" id="info">
                                <div class="tab-pane active">
                                    <form class="form" novalidate="">
                                        <div class="row">
                                            <div class="col">
                                                <div class="row">
                                                    <div class="col">
                                                        <div class="form-group">
                                                            <label>Name:</label>
                                                            <label><%=customer.getCustomerName()%></label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col">
                                                        <div class="form-group">
                                                            <label>Email:</label>
                                                            <label><%= customer.getEmail()%></label>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="form-group">
                                                            <label>MSISDN:</label>
                                                            <label><%=customer.getMsisdn()%></label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col">
                                                        <div class="form-group">
                                                            <label>Birthdate:</label>
                                                            <label><%=customer.getBirthDate()%></label>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="form-group">
                                                            <label>Job:</label>
                                                            <label><%=customer.getJob()%></label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col">
                                                        <div class="form-group">
                                                            <label>NationalID :</label>
                                                            <label><%=customer.getNationalID()%></label>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="form-group">
                                                            <label>RatePlan ID: </label>
                                                            <label><%=customer.getRatePlane_id()%></label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col">
                                                        <div class="form-group">
                                                            <label>Address:</label>
                                                            <label><%=customer.getAddress()%></label>
                                                        </div>
                                                    </div>
                                                </div>   


                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </div>
                            <ul class="nav nav-tabs">
                                <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="" role="tab" aria-controls="pills-home" aria-selected="false">Added Services: </a>
                            </ul>
                            <div class="tab-content pt-3">
                                <div class="tab-pane active">

                                    <div class="row">
                                        <div class="col">
                                            <% for (RecurringService service : addedServices) {
                                                    Services thisService = db.getService(service.getServiceID());
                                            %>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Name:</label>
                                                        <label><%=thisService.getServiceName()%></label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Service Type:</label>
                                                        <label><%=thisService.getServiceType()%></label>
                                                    </div>
                                                </div>

                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>MSISDN:</label>
                                                        <label><%=customer.getMsisdn()%></label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Fees:</label>
                                                        <label><%=thisService.getFees()%></label>
                                                    </div>
                                                </div>
                                                <% if (thisService.getServiceType().equalsIgnoreCase("recurring")) {%>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <form class="s003 inner-form" action="DeActivateService" method="GET">
                                                            <input class="form-control mr-sm-2" type="hidden" name="sid" value="<%=service.getServiceID()%>">
                                                            <input class="form-control mr-sm-2" type="hidden" name="cid" value="<%=customer.getCustomerID()%>">
                                                            <input class="form-control mr-sm-2" type="hidden" name="fees" value="<%=service.getFees()%>">
                                                            <input class="btn btn-outline-success my-2 my-sm-0" data-toggle="pill" type="submit" aria-controls="pills-contact" value="DeActivate"></input>
                                                        </form>

                                                    </div>
                                                </div>
                                                <%}%>
                                            </div>
                                            <%}%>
                                        </div>
                                    </div>






                                    <div class="row">
                                        <div class="col">
                                            <% for (OnetimeService service : addedServices2) {
                                                    Services thisService = db.getService(service.getServiceID());
                                            %>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Name:</label>
                                                        <label><%=thisService.getServiceName()%></label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Service Type:</label>
                                                        <label><%=thisService.getServiceType()%></label>
                                                    </div>
                                                </div>

                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>MSISDN:</label>
                                                        <label><%=customer.getMsisdn()%></label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Fees:</label>
                                                        <label><%=thisService.getFees()%></label>
                                                    </div>
                                                </div>

                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Time: </label>
                                                        <label><%=service.getDate()%></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <%}%>
                                        </div>




                                    </div>
                                </div>
                            </div>






                            <ul class="nav nav-tabs">
                                <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="" role="tab" aria-controls="pills-home" aria-selected="false">Bills: </a>
                            </ul>
                            <div id="bills"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%}
            }%>
    </body>
</html>
