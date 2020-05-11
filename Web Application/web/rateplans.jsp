<%-- 
    Document   : rateplans
    Created on : May 11, 2020, 3:21:54 PM
    Author     : mohab
--%>

<%@page import="classes.RatePlan"%>
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
        <title>Rate Plans</title>
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
                String cID = request.getParameter("cid");
                Database db = new Database();
                customer = db.getCustomer(customerMSISDN);
                Vector<RatePlan> ratePlans = db.getRateplans();
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
                                        <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">Available RatePlans</h4>
                                        <p class="mb-0"><%= customer.getMsisdn()%></p>
                                        <div class="mt-2">
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <ul class="nav nav-tabs">
                                <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#info" role="tab" aria-controls="pills-home" aria-selected="true">Rate Plans:  </a>
                            </ul>

                            <div class="tab-content pt-3" id="info">
                                <div class="tab-pane active">

                                    <div class="row">
                                        <div class="col">
                                            <% for (RatePlan rateplan : ratePlans) {
                                                if (rateplan.getRatePlane_id() != customer.getRatePlane_id())
                                                {
                                            %>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>ID: </label>
                                                        <label><%=rateplan.getRatePlane_id()%></label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Service Package:</label>
                                                        <label><%=rateplan.getServicePkg_id()%></label>
                                                    </div>
                                                </div>

                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Free Units: </label>
                                                        <label><%=rateplan.getFreeUnits()%></label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <label>Monthly Fees:</label>
                                                        <label><%=rateplan.getMonthlyFee()%></label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group">
                                                        <form class="s003 inner-form" action="" method="GET">
                                                            <input class="form-control mr-sm-2" type="hidden" name="rid" value="<%=rateplan.getRatePlane_id()%>">                                                          
                                                            <input class="form-control mr-sm-2" type="hidden" name="cid" value="<%=cID%>">                                                        
                                                           <input class="btn btn-outline-success my-2 my-sm-0" data-toggle="pill" type="submit" aria-controls="pills-contact" value="Subscribe"></input>
                                                        </form>

                                                    </div>
                                                </div>
                                            </div>
                                            <%}%>
                                            <%}%>

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