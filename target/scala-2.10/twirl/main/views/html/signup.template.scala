
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object signup extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](_display_(/*2.2*/main("Please sign up")/*2.24*/ {_display_(Seq[Any](format.raw/*2.26*/("""



"""),format.raw/*6.1*/("""<div class="row">
    <div class="col-md-4"></div>

    <div class="col-md-4">
        <br>

        <div class="panel panel-default" ng-controller="SignUpController">
            <div class="panel-heading">
                <h4 class="panel-title">Create Free Account</h4>
            </div>
            <div class="panel-body">

                    <form name="signupForm" ng-submit="submitForm(signupForm.$valid)" novalidate>

                        <div class="form-group" ng-class=""""),format.raw/*20.59*/("""{"""),format.raw/*20.60*/(""" """),format.raw/*20.61*/("""'has-error' : signupForm.name.$invalid && !signupForm.name.$pristine """),format.raw/*20.130*/("""}"""),format.raw/*20.131*/("""">
                            <label>Name</label>
                            <input type="text" name="name" class="form-control" ng-model="signupRequest.name" required>
                            <p ng-show="signupForm.name.$invalid && !signupForm.name.$pristine" class="help-block">You name is required.</p>
                        </div>

                        <!-- USERNAME -->
                        <div class="form-group" ng-class=""""),format.raw/*27.59*/("""{"""),format.raw/*27.60*/(""" """),format.raw/*27.61*/("""'has-error' : signupForm.username.$invalid && !signupForm.username.$pristine """),format.raw/*27.138*/("""}"""),format.raw/*27.139*/("""">
                            <label>Username</label>
                            <input type="text" name="username" class="form-control" ng-model="signupRequest.username" ng-minlength="3" ng-maxlength="8">
                            <p ng-show="signupForm.username.$error.minlength" class="help-block">Username is too short.</p>
                            <p ng-show="signupForm.username.$error.maxlength" class="help-block">Username is too long.</p>
                        </div>

                        <!-- EMAIL -->
                        <div class="form-group" ng-class=""""),format.raw/*35.59*/("""{"""),format.raw/*35.60*/(""" """),format.raw/*35.61*/("""'has-error' : signupForm.email.$invalid && !signupForm.email.$pristine """),format.raw/*35.132*/("""}"""),format.raw/*35.133*/("""">
                            <label>Email</label>
                            <input type="email" name="email" class="form-control" ng-model="signupRequest.email">
                            <p ng-show="signupForm.email.$invalid && !signupForm.email.$pristine" class="help-block">Enter a valid email.</p>
                        </div>


                        <!-- SUBMIT BUTTON -->
                        <button type="submit" class="btn btn-primary btn-block" ng-disabled="signupForm.$invalid">Sign Up</button>


                        <pre>signupRequest = """),format.raw/*46.46*/("""{"""),format.raw/*46.47*/("""{"""),format.raw/*46.48*/("""signupRequest | json"""),format.raw/*46.68*/("""}"""),format.raw/*46.69*/("""}"""),format.raw/*46.70*/("""</pre>


                    </form>

            </div>
            <div class="panel-body">
                <a class="btn btn-primary btn-block" href="/login">Already member? Sign In.</a>
            <div>
        </div>



        <br>






    </div>
    <div class="col-md-4"></div>
</div>


""")))}))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Nov 07 13:54:58 PST 2014
                  SOURCE: /Users/antoine_lefebvre/Github/fixit/app/views/signup.scala.html
                  HASH: 9dc8b71926c73c88c406856870ed4fcc0879008f
                  MATRIX: 799->2|829->24|868->26|898->30|1413->517|1442->518|1471->519|1569->588|1599->589|2071->1033|2100->1034|2129->1035|2235->1112|2265->1113|2877->1697|2906->1698|2935->1699|3035->1770|3065->1771|3659->2337|3688->2338|3717->2339|3765->2359|3794->2360|3823->2361
                  LINES: 29->2|29->2|29->2|33->6|47->20|47->20|47->20|47->20|47->20|54->27|54->27|54->27|54->27|54->27|62->35|62->35|62->35|62->35|62->35|73->46|73->46|73->46|73->46|73->46|73->46
                  -- GENERATED --
              */
          