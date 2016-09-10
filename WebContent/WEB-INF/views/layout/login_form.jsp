<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="login parallax login-parallax" data-stellar-background-ratio="0.6" data-stellar-vertical-offset="20" id="LOGIN">
    <div class="section_overlay wow lightSpeedIn">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">

                    <!-- Start Subscribe Section Title -->
                    <div class="section_title">
                        <h2>LOGIN</h2>
                        <p>Please login with your username and password </p>
                    </div>
                    <!-- End Subscribe Section Title -->
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row  wow lightSpeedIn">
                <div class="col-md-6 col-md-offset-3">
                    <!-- SUBSCRIPTION SUCCESSFUL OR ERROR MESSAGES -->
                    <div class="login-success"></div>
                    <div class="login-error"></div>

                    <div class="form_error text-center">
                        <div class="username_error error" style="color: red">${error}</div>
                    </div>

                    <form id="f" class="login_form" action="<c:url value='j_spring_security_check' />" method='POST'>
                        <div class="form-group">
                            <!-- EMAIL INPUT BOX -->
                            <input type="text" value="" name="j_username" class="required email form-control" id="username" placeholder="Enter Your Username">
                        </div>
                        <div class="form-group">
                            <!-- EMAIL INPUT BOX -->
                            <input type="password" value="" name="j_password" class="required email form-control" id="password" placeholder="Enter Your Password">
                        </div>
                        <!-- SUBSCRIBE BUTTON -->
                        <button type="submit" class="btn btn-default subs-btn">LOGIN</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>