
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
object projects extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[com.fixit.model.Project],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(projects: List[com.fixit.model.Project]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.43*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("Home")/*5.14*/ {_display_(Seq[Any](format.raw/*5.16*/("""


"""),format.raw/*8.1*/("""<div ng-controller="ProjectController" ng-init="init()">
	
	<div class="row">
       <div class="col-sm-3" ng-repeat="project in projects">
	    <div class="thumbnail" >
	      <img src=""""),_display_(/*13.19*/routes/*13.25*/.Assets.at("images/photo-little.jpg")),format.raw/*13.62*/("""" alt="..." >
	      <div class="caption">
	        <h4>"""),format.raw/*15.14*/("""{"""),format.raw/*15.15*/("""{"""),format.raw/*15.16*/("""project.name"""),format.raw/*15.28*/("""}"""),format.raw/*15.29*/("""}"""),format.raw/*15.30*/(""" """),format.raw/*15.31*/("""<br> by """),format.raw/*15.39*/("""{"""),format.raw/*15.40*/("""{"""),format.raw/*15.41*/("""project.username"""),format.raw/*15.57*/("""}"""),format.raw/*15.58*/("""}"""),format.raw/*15.59*/(""" """),format.raw/*15.60*/("""</h4>
	        <p>"""),format.raw/*16.13*/("""{"""),format.raw/*16.14*/("""{"""),format.raw/*16.15*/("""project.description"""),format.raw/*16.34*/("""}"""),format.raw/*16.35*/("""}"""),format.raw/*16.36*/("""</p>
	        <p>
	        <i class="glyphicon glyphicon-map-marker"></i>
	        """),format.raw/*19.10*/("""{"""),format.raw/*19.11*/("""{"""),format.raw/*19.12*/("""project.city"""),format.raw/*19.24*/("""}"""),format.raw/*19.25*/("""}"""),format.raw/*19.26*/(""", """),format.raw/*19.28*/("""{"""),format.raw/*19.29*/("""{"""),format.raw/*19.30*/("""project.country"""),format.raw/*19.45*/("""}"""),format.raw/*19.46*/("""}"""),format.raw/*19.47*/("""
	        """),format.raw/*20.10*/("""</p>

	        <p>
                <a href="/projects/"""),format.raw/*23.36*/("""{"""),format.raw/*23.37*/("""{"""),format.raw/*23.38*/("""project.id"""),format.raw/*23.48*/("""}"""),format.raw/*23.49*/("""}"""),format.raw/*23.50*/("""" class="btn btn-info"><span class="glyphicon glyphicon-zoom-in"></span> Details</a>
                <a ng-if="project.username == currentuser " href="/projects/"""),format.raw/*24.77*/("""{"""),format.raw/*24.78*/("""{"""),format.raw/*24.79*/("""project.id"""),format.raw/*24.89*/("""}"""),format.raw/*24.90*/("""}"""),format.raw/*24.91*/("""/edit" class="btn btn-default" role="button"><span class="glyphicon glyphicon-edit"></span>Edit</a>
            </p>
	      </div>
	    </div>
	  </div>
	</div>

</div>
	
	
	


    
    

      <div class="row row-offcanvas row-offcanvas-right">


        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1>Hello, world!</h1>
            <p>This is an example to show the potential of an offcanvas layout pattern in Bootstrap. Try some responsive-range viewport sizes to see it in action.</p>
          </div>
          
          
          """),_display_(/*53.12*/for(project <- projects) yield /*53.36*/ {_display_(Seq[Any](format.raw/*53.38*/("""
          """),format.raw/*54.11*/("""<div class="row">
            <div class="col-6 col-sm-6 col-lg-4">
              <h2>"""),_display_(/*56.20*/project/*56.27*/.name),format.raw/*56.32*/("""</h2>
              <p>
              """),_display_(/*58.16*/project/*58.23*/.description),format.raw/*58.35*/("""
              """),format.raw/*59.15*/("""</p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a>
              <i class="icon-edit">AAAA</i>
              
              </p>
            </div><!--/span-->
          </div><!--/row-->          
          """)))}),format.raw/*66.12*/("""
          
          
        """),format.raw/*69.9*/("""</div><!--/span-->


      </div><!--/row-->



    
    





 
    
""")))}))}
  }

  def render(projects:List[com.fixit.model.Project]): play.twirl.api.HtmlFormat.Appendable = apply(projects)

  def f:((List[com.fixit.model.Project]) => play.twirl.api.HtmlFormat.Appendable) = (projects) => apply(projects)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Nov 07 13:54:58 PST 2014
                  SOURCE: /Users/antoine_lefebvre/Github/fixit/app/views/projects.scala.html
                  HASH: b55e5fee84355b17fd17304c00a21e11c61fb806
                  MATRIX: 749->1|893->42|921->61|948->63|968->75|1007->77|1036->80|1251->268|1266->274|1324->311|1408->367|1437->368|1466->369|1506->381|1535->382|1564->383|1593->384|1629->392|1658->393|1687->394|1731->410|1760->411|1789->412|1818->413|1864->431|1893->432|1922->433|1969->452|1998->453|2027->454|2138->537|2167->538|2196->539|2236->551|2265->552|2294->553|2324->555|2353->556|2382->557|2425->572|2454->573|2483->574|2521->584|2603->638|2632->639|2661->640|2699->650|2728->651|2757->652|2946->813|2975->814|3004->815|3042->825|3071->826|3100->827|3870->1570|3910->1594|3950->1596|3989->1607|4103->1694|4119->1701|4145->1706|4211->1745|4227->1752|4260->1764|4303->1779|4589->2034|4647->2065
                  LINES: 26->1|29->1|31->4|32->5|32->5|32->5|35->8|40->13|40->13|40->13|42->15|42->15|42->15|42->15|42->15|42->15|42->15|42->15|42->15|42->15|42->15|42->15|42->15|42->15|43->16|43->16|43->16|43->16|43->16|43->16|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|47->20|50->23|50->23|50->23|50->23|50->23|50->23|51->24|51->24|51->24|51->24|51->24|51->24|80->53|80->53|80->53|81->54|83->56|83->56|83->56|85->58|85->58|85->58|86->59|93->66|96->69
                  -- GENERATED --
              */
          