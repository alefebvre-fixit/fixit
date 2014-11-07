
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
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html ng-app="FixIt">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>"""),_display_(/*14.13*/title),format.raw/*14.18*/("""</title>
    <link rel="stylesheet" media="screen" href=""""),_display_(/*15.50*/routes/*15.56*/.Assets.at("stylesheets/main.css")),format.raw/*15.90*/("""">

    <link rel="shortcut icon" type="image/png" href=""""),_display_(/*17.55*/routes/*17.61*/.Assets.at("images/favicon.png")),format.raw/*17.93*/("""">

    <script src=""""),_display_(/*19.19*/routes/*19.25*/.Assets.at("javascripts/hello.js")),format.raw/*19.59*/("""" type="text/javascript"></script>
    
    <script src=""""),_display_(/*21.19*/routes/*21.25*/.Assets.at("javascripts/jquery-1.11.1.js")),format.raw/*21.67*/("""" type="text/javascript"></script>
    <script src=""""),_display_(/*22.19*/routes/*22.25*/.Assets.at("javascripts/angular/angular.js")),format.raw/*22.69*/("""" type="text/javascript"></script>
    <script src=""""),_display_(/*23.19*/routes/*23.25*/.Assets.at("javascripts/angular/angular-route.js")),format.raw/*23.75*/("""" type="text/javascript"></script>
    <script src=""""),_display_(/*24.19*/routes/*24.25*/.Assets.at("javascripts/angular/angular-animate.js")),format.raw/*24.77*/("""" type="text/javascript"></script>

    <script src=""""),_display_(/*26.19*/routes/*26.25*/.Assets.at("javascripts/angular/ui-bootstrap-tpls-0.11.0.js")),format.raw/*26.86*/("""" type="text/javascript"></script>

    <script src=""""),_display_(/*28.19*/routes/*28.25*/.Assets.at("javascripts/fixit.js")),format.raw/*28.59*/("""" type="text/javascript"></script>
    <script src=""""),_display_(/*29.19*/routes/*29.25*/.Assets.at("javascripts/project-controllers.js")),format.raw/*29.73*/("""" type="text/javascript"></script>
    <script src=""""),_display_(/*30.19*/routes/*30.25*/.Assets.at("javascripts/account-controllers.js")),format.raw/*30.73*/("""" type="text/javascript"></script>

    <link rel="stylesheet" media="screen" href=""""),_display_(/*32.50*/routes/*32.56*/.Assets.at("stylesheets/bootstrap.css")),format.raw/*32.95*/("""">
    <link rel="stylesheet" media="screen" href=""""),_display_(/*33.50*/routes/*33.56*/.Assets.at("stylesheets/bootstrap-theme.css")),format.raw/*33.101*/("""">
    
    <script src=""""),_display_(/*35.19*/routes/*35.25*/.Assets.at("javascripts/bootstrap.js")),format.raw/*35.63*/("""" type="text/javascript"></script>


  </head>

  <body>
      <!-- Static navbar -->
    <div class="navbar navbar-default navbar-static-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Fix-It</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class=""""),_display_(/*55.25*/{if (title.equals("Home")) "active" else "inactive"}),format.raw/*55.77*/(""""><a href=""""),_display_(/*55.89*/routes/*55.95*/.ProjectController.projects()),format.raw/*55.124*/("""">Home</a></li>
            <li class=""""),_display_(/*56.25*/{if (title.equals("Me")) "active" else "inactive"}),format.raw/*56.75*/(""""><a href=""""),_display_(/*56.87*/routes/*56.93*/.ProjectController.me()),format.raw/*56.116*/("""">Me</a></li>
            <li class=""""),_display_(/*57.25*/{if (title.equals("New")) "active" else "inactive"}),format.raw/*57.76*/(""""><a href=""""),_display_(/*57.88*/routes/*57.94*/.ProjectController.newProject()),format.raw/*57.125*/("""">New Project</a></li>           
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">"""),_display_(/*61.75*/session()/*61.84*/.get("username")),format.raw/*61.100*/(""" """),format.raw/*61.101*/("""<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href=""""),_display_(/*63.31*/routes/*63.37*/.AccountController.userProfile()),format.raw/*63.69*/("""">Edit Account</a></li>
                <li class="divider"></li>
                <li><a href=""""),_display_(/*65.31*/routes/*65.37*/.AccountController.logout()),format.raw/*65.64*/("""">logout</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

      <div class="container" ng-controller="FixItController" ng-init="init('"""),_display_(/*73.78*/session()/*73.87*/.get("username")),format.raw/*73.103*/("""')">
        	"""),_display_(/*74.11*/content),format.raw/*74.18*/("""
      """),format.raw/*75.7*/("""</div><!--/.container-->

      <footer class="container">
        <p>&copy; Company 2014</p>
      </footer>
  </body>
  
</html>
"""))}
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Nov 07 13:54:58 PST 2014
                  SOURCE: /Users/antoine_lefebvre/Github/fixit/app/views/main.scala.html
                  HASH: 07253c9897ed73d0466f15e74b9b9afed60b0fc9
                  MATRIX: 727->1|845->31|873->33|1201->334|1227->339|1312->397|1327->403|1382->437|1467->495|1482->501|1535->533|1584->555|1599->561|1654->595|1739->653|1754->659|1817->701|1897->754|1912->760|1977->804|2057->857|2072->863|2143->913|2223->966|2238->972|2311->1024|2392->1078|2407->1084|2489->1145|2570->1199|2585->1205|2640->1239|2720->1292|2735->1298|2804->1346|2884->1399|2899->1405|2968->1453|3080->1538|3095->1544|3155->1583|3234->1635|3249->1641|3316->1686|3369->1712|3384->1718|3443->1756|4193->2479|4266->2531|4305->2543|4320->2549|4371->2578|4438->2618|4509->2668|4548->2680|4563->2686|4608->2709|4673->2747|4745->2798|4784->2810|4799->2816|4852->2847|5088->3056|5106->3065|5144->3081|5174->3082|5298->3179|5313->3185|5366->3217|5489->3313|5504->3319|5552->3346|5790->3557|5808->3566|5846->3582|5888->3597|5916->3604|5950->3611
                  LINES: 26->1|29->1|31->3|42->14|42->14|43->15|43->15|43->15|45->17|45->17|45->17|47->19|47->19|47->19|49->21|49->21|49->21|50->22|50->22|50->22|51->23|51->23|51->23|52->24|52->24|52->24|54->26|54->26|54->26|56->28|56->28|56->28|57->29|57->29|57->29|58->30|58->30|58->30|60->32|60->32|60->32|61->33|61->33|61->33|63->35|63->35|63->35|83->55|83->55|83->55|83->55|83->55|84->56|84->56|84->56|84->56|84->56|85->57|85->57|85->57|85->57|85->57|89->61|89->61|89->61|89->61|91->63|91->63|91->63|93->65|93->65|93->65|101->73|101->73|101->73|102->74|102->74|103->75
                  -- GENERATED --
              */
          