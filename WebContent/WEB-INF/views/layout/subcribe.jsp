<section class="subscribe parallax subscribe-parallax" data-stellar-background-ratio="0.6" data-stellar-vertical-offset="20" id="SUBSCRIBE">
    <div class="section_overlay wow lightSpeedIn">
        <div class="container">
            <div class="row">
                  <div class="col-md-10 col-md-offset-1">

                    <!-- Start Subscribe Section Title -->
                    <div class="section_title">
                        <h2>Welcome To ASChecker</h2>
                        <p>Please enter a URL which you want to check for accessibility and security</p>
                    </div>
                    <!-- End Subscribe Section Title -->
                </div>
            </div>
        </div>

        <div class="container">
          <div class="row  wow lightSpeedIn">
                <div class="col-md-6 col-md-offset-3">
                    <!-- SUBSCRIPTION SUCCESSFUL OR ERROR MESSAGES -->
                    <div class="input-success"></div>
                    <div class="input-error">${error}</div>


                    <form name="f"  class="input_form" action="/trung/check" method='POST'>
                        <div class="form-group">
                            <!-- EMAIL INPUT BOX -->
                            <input type="url" value="" name="keyword" class="required email form-control" id="mce-EMAIL" placeholder="Enter a URL for checking">
                        </div>
                        <div style= "color: #B5B5B5">
                            <h3>Configure checking option:</h3>
                            <div style="color: #98b3bd;font-size: 20px;">
                                    <input type="checkbox" class="radio" value="1" name="option" checked >Both Accessibility and Security<br>
                                    <input type="checkbox" class="radio" value="2" name="option">Accessibility<br>
                                    <input type="checkbox" class="radio" value="3" name="option">Security<br>
                            </div>
                        </div>
                        <!-- SUBSCRIBE BUTTON -->
                        <button name="submit" type="submit" class="btn btn-default subs-btn">Check</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>