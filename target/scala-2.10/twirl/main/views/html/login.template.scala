
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
object login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[AccountController.Login],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(loginForm: Form[AccountController.Login]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._

Seq[Any](format.raw/*1.44*/("""
"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("Please sign in")/*5.24*/ {_display_(Seq[Any](format.raw/*5.26*/("""
    """),_display_(/*6.6*/form(routes.AccountController.authenticate())/*6.51*/ {_display_(Seq[Any](format.raw/*6.53*/("""



"""),format.raw/*10.1*/("""<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <br>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">Sign In</h4>
            </div>
            <div class="panel-body">

                <form name="loginForm" class="form-horizontal" novalidate>

                    <div class="control-group">
                        <div class="controls">
                            <input type="email" name="email" placeholder="Enter email" class="form-control" value=""""),_display_(/*24.117*/loginForm("email")/*24.135*/.value),format.raw/*24.141*/("""">
                        </div>
                    </div>
                    <br>
                    <div class="control-group">
                        <div class="controls">
                            <input type="password" name="password"  placeholder="Enter password" class="form-control">
                        </div>
                    </div>

                    <br>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                    </div>
                </form>
                <br>
                <a class="btn btn-primary btn-block" href="/signup">Create Free Account</a>
           </div>
        </div>

    </div>
    <div class="col-md-4"></div>
</div>



""")))}),format.raw/*51.2*/("""
""")))}),format.raw/*52.2*/("""
"""))}
  }

  def render(loginForm:Form[AccountController.Login]): play.twirl.api.HtmlFormat.Appendable = apply(loginForm)

  def f:((Form[AccountController.Login]) => play.twirl.api.HtmlFormat.Appendable) = (loginForm) => apply(loginForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Nov 07 13:54:58 PST 2014
                  SOURCE: /Users/antoine_lefebvre/Github/fixit/app/views/login.scala.html
                  HASH: a3f7edda1b9e66ff0b45d65b7d9ca974307e9814
                  MATRIX: 746->1|924->43|951->95|978->97|1008->119|1047->121|1078->127|1131->172|1170->174|1201->178|1799->748|1827->766|1855->772|2667->1554|2699->1556
                  LINES: 26->1|30->1|31->4|32->5|32->5|32->5|33->6|33->6|33->6|37->10|51->24|51->24|51->24|78->51|79->52
                  -- GENERATED --
              */
          