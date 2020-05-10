<%-- 
    Document   : services
    Created on : May 10, 2020, 12:34:06 AM
    Author     : mohab
--%>

<%@page import="java.util.Vector"%>
<%@page import="classes.Services"%>
<%@page import="classes.Customer"%>
<%@page import="WebInterface.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! Customer customer;%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Services</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/e46fb9d55b.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    </head>
    <body>
        <% if (request.getParameter("success") != null && request.getParameter("success").equals("true")) {
        %>
        <script type="text/javascript">
            alert("Added Successfully.");
        </script>        
        <%
            }
        %>
        <%if (request.getParameter("key") != null) {
                String customerMSISDN = request.getParameter("key");
                Database db = new Database();
                customer = db.getCustomer(customerMSISDN);
                Vector<Services> services = db.getServices();
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
                                        <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">Available Services</h4>
                                        <p class="mb-0"><%= customer.getMsisdn()%></p>
                                        <div class="mt-2">
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <ul class="nav nav-tabs">
                                <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#info" role="tab" aria-controls="pills-home" aria-selected="true">Services </a>
                            </ul>

                            <div class="tab-content pt-3" id="info">
                                <div class="tab-pane active">

                                    <div class="row">
                                        <div class="col">
                                            <% for (Services service : services) {
                                            %>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Name:</label>
                                                        <label><%=service.getServiceName()%></label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Service Type:</label>
                                                        <label><%=service.getServiceType()%></label>
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
                                                        <label><%=service.getFees()%></label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <form class="s003 inner-form" action="AddService" method="GET">
                                                            <input class="form-control mr-sm-2" type="hidden" name="sid" value="<%=service.getServiceID()%>">
                                                            <input class="form-control mr-sm-2" type="hidden" name="stype" value="<%=service.getServiceType()%>">
                                                            <input class="form-control mr-sm-2" type="hidden" name="cid" value="<%=customer.getCustomerID()%>">
                                                            <input class="form-control mr-sm-2" type="hidden" name="fees" value="<%=service.getFees()%>">
                                                            <input class="btn btn-outline-success my-2 my-sm-0" data-toggle="pill" type="submit" aria-controls="pills-contact" value="Add Service"></input>
                                                        </form>

                                                    </div>
                                                </div>
                                            </div>
                                            <%}%>
                                            <div class="row">

                                            </div>

                                            <div class="row">


                                            </div>




                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a class="btn btn-outline-success my-2 my-sm-0" data-toggle="pill" href="main.jsp" aria-controls="pills-contact">Main</a>

        <%}
            }%>
    </body>
</html>