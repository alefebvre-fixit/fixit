
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
object userprofile extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.4*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("Profile")/*5.17*/ {_display_(Seq[Any](format.raw/*5.19*/("""

"""),format.raw/*7.1*/("""<div ng-controller="EditAccountController" ng-init="init()">

<div class="row">
  <div class="col-md-4">
  
  	<div data-ng-include data-src="'/assets/partials/account/profile-summary.html'"></div>
	<br>
	<div class="list-group">
        <a href ng-click="section='account'" class="list-group-item" ng-class=""""),format.raw/*15.80*/("""{"""),format.raw/*15.81*/("""'active':section=='account'"""),format.raw/*15.108*/("""}"""),format.raw/*15.109*/("""">Account</a>
        <a href ng-click="section='password'" class="list-group-item" ng-class=""""),format.raw/*16.81*/("""{"""),format.raw/*16.82*/("""'active':section=='password'"""),format.raw/*16.110*/("""}"""),format.raw/*16.111*/("""">Password</a>
        <a href ng-click="section='notification'" class="list-group-item" ng-class=""""),format.raw/*17.85*/("""{"""),format.raw/*17.86*/("""'active':section=='notification'"""),format.raw/*17.118*/("""}"""),format.raw/*17.119*/("""">Email Notifications</a>
        <a href ng-click="section='profile'" class="list-group-item" ng-class=""""),format.raw/*18.80*/("""{"""),format.raw/*18.81*/("""'active':section=='profile'"""),format.raw/*18.108*/("""}"""),format.raw/*18.109*/("""">Profile</a>
    </div>

  </div>
  
  <div class="col-md-8">

      <div class="animate-switch-container" >
          <ng-switch on="section">
              <div class="animate-switch" ng-switch-when="account">
                  <div class="animate-switch" data-ng-include data-src="'/assets/partials/account/settings-account.html'"></div>
              </div>
              <div class="animate-switch" ng-switch-when="password">
                  <div class="animate-switch" data-ng-include data-src="'/assets/partials/account/settings-password.html'"></div>
              </div>
              <div class="animate-switch" ng-switch-when="notification">
                  notification section
              </div>
              <div class="animate-switch" ng-switch-when="profile">
                  <div class="animate-switch" data-ng-include data-src="'/assets/partials/account/settings-profile.html'"></div>
              </div>
              <div class="animate-switch" ng-switch-default>
                  <div data-ng-include data-src="'/assets/partials/account/settings-account.html'"></div>
              </div>
          </ng-switch>
      </div>
  </div>

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
                  SOURCE: /Users/antoine_lefebvre/Github/fixit/app/views/userprofile.scala.html
                  HASH: 98f14d1fcb7c99cca4ba72c6cad05101fe5fdcb7
                  MATRIX: 722->1|826->3|854->22|881->24|904->39|943->41|971->43|1308->352|1337->353|1393->380|1423->381|1545->475|1574->476|1631->504|1661->505|1788->604|1817->605|1878->637|1908->638|2041->743|2070->744|2126->771|2156->772
                  LINES: 26->1|29->1|31->4|32->5|32->5|32->5|34->7|42->15|42->15|42->15|42->15|43->16|43->16|43->16|43->16|44->17|44->17|44->17|44->17|45->18|45->18|45->18|45->18
                  -- GENERATED --
              */
          