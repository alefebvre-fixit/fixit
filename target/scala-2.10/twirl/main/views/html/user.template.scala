
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
object user extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(user: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.16*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("Me")/*5.12*/ {_display_(Seq[Any](format.raw/*5.14*/("""

"""),format.raw/*7.1*/("""<div ng-controller="ProjectController" ng-init="init('"""),_display_(/*7.56*/session()/*7.65*/.get("username")),format.raw/*7.81*/("""')">
	<div class="row">
	
	   <h4>"""),format.raw/*10.9*/("""{"""),format.raw/*10.10*/("""{"""),format.raw/*10.11*/("""username"""),format.raw/*10.19*/("""}"""),format.raw/*10.20*/("""}"""),format.raw/*10.21*/("""</h4>
	
       <div class="col-sm-3" ng-repeat="project in projects">
	    <div class="thumbnail" >
	      <img src=""""),_display_(/*14.19*/routes/*14.25*/.Assets.at("images/photo-little.jpg")),format.raw/*14.62*/("""" alt="..." >
	      <div class="caption">
	        <h4>"""),format.raw/*16.14*/("""{"""),format.raw/*16.15*/("""{"""),format.raw/*16.16*/("""project.name"""),format.raw/*16.28*/("""}"""),format.raw/*16.29*/("""}"""),format.raw/*16.30*/(""" """),format.raw/*16.31*/("""<br> by """),format.raw/*16.39*/("""{"""),format.raw/*16.40*/("""{"""),format.raw/*16.41*/("""project.username"""),format.raw/*16.57*/("""}"""),format.raw/*16.58*/("""}"""),format.raw/*16.59*/("""</h4>
	        <p>"""),format.raw/*17.13*/("""{"""),format.raw/*17.14*/("""{"""),format.raw/*17.15*/("""project.description"""),format.raw/*17.34*/("""}"""),format.raw/*17.35*/("""}"""),format.raw/*17.36*/("""</p>
	        <p>
	        <i class="glyphicon glyphicon-map-marker"></i>
	        """),format.raw/*20.10*/("""{"""),format.raw/*20.11*/("""{"""),format.raw/*20.12*/("""project.city"""),format.raw/*20.24*/("""}"""),format.raw/*20.25*/("""}"""),format.raw/*20.26*/(""", """),format.raw/*20.28*/("""{"""),format.raw/*20.29*/("""{"""),format.raw/*20.30*/("""project.country"""),format.raw/*20.45*/("""}"""),format.raw/*20.46*/("""}"""),format.raw/*20.47*/("""
	        """),format.raw/*21.10*/("""</p>
	        <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
	      </div>
	    </div>
	  </div>
	</div>

</div>
	

    
    





 
    
""")))}))}
  }

  def render(user:String): play.twirl.api.HtmlFormat.Appendable = apply(user)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (user) => apply(user)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Nov 07 13:54:58 PST 2014
                  SOURCE: /Users/antoine_lefebvre/Github/fixit/app/views/user.scala.html
                  HASH: 00739c5b013f153e03642c61264132177cc15424
                  MATRIX: 722->1|839->15|867->34|894->36|912->46|951->48|979->50|1060->105|1077->114|1113->130|1174->164|1203->165|1232->166|1268->174|1297->175|1326->176|1471->294|1486->300|1544->337|1628->393|1657->394|1686->395|1726->407|1755->408|1784->409|1813->410|1849->418|1878->419|1907->420|1951->436|1980->437|2009->438|2055->456|2084->457|2113->458|2160->477|2189->478|2218->479|2329->562|2358->563|2387->564|2427->576|2456->577|2485->578|2515->580|2544->581|2573->582|2616->597|2645->598|2674->599|2712->609
                  LINES: 26->1|29->1|31->4|32->5|32->5|32->5|34->7|34->7|34->7|34->7|37->10|37->10|37->10|37->10|37->10|37->10|41->14|41->14|41->14|43->16|43->16|43->16|43->16|43->16|43->16|43->16|43->16|43->16|43->16|43->16|43->16|43->16|44->17|44->17|44->17|44->17|44->17|44->17|47->20|47->20|47->20|47->20|47->20|47->20|47->20|47->20|47->20|47->20|47->20|47->20|48->21
                  -- GENERATED --
              */
          