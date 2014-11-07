
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
object projectview extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[java.lang.String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(projectId: java.lang.String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.31*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("New")/*5.13*/ {_display_(Seq[Any](format.raw/*5.15*/("""
    
"""),format.raw/*7.1*/("""<div ng-controller="ViewProjectController" ng-init="init('"""),_display_(/*7.60*/projectId),format.raw/*7.69*/("""')">
    <div class="row">
        <div class="col-md-8">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">"""),format.raw/*13.45*/("""{"""),format.raw/*13.46*/("""{"""),format.raw/*13.47*/("""project.name"""),format.raw/*13.59*/("""}"""),format.raw/*13.60*/("""}"""),format.raw/*13.61*/("""</h3>
                </div>
                <div class="panel-body">

                    <img src=""""),_display_(/*17.32*/routes/*17.38*/.Assets.at("images/photo-little.jpg")),format.raw/*17.75*/("""" alt="..." >
                    <div class="caption">
                        <h4>"""),format.raw/*19.29*/("""{"""),format.raw/*19.30*/("""{"""),format.raw/*19.31*/("""project.name"""),format.raw/*19.43*/("""}"""),format.raw/*19.44*/("""}"""),format.raw/*19.45*/(""" """),format.raw/*19.46*/("""<br> by """),format.raw/*19.54*/("""{"""),format.raw/*19.55*/("""{"""),format.raw/*19.56*/("""project.username"""),format.raw/*19.72*/("""}"""),format.raw/*19.73*/("""}"""),format.raw/*19.74*/(""" """),format.raw/*19.75*/("""</h4>
                        <p>"""),format.raw/*20.28*/("""{"""),format.raw/*20.29*/("""{"""),format.raw/*20.30*/("""project.description"""),format.raw/*20.49*/("""}"""),format.raw/*20.50*/("""}"""),format.raw/*20.51*/("""</p>
                        <p>
                            <i class="glyphicon glyphicon-map-marker"></i>
                            """),format.raw/*23.29*/("""{"""),format.raw/*23.30*/("""{"""),format.raw/*23.31*/("""project.city"""),format.raw/*23.43*/("""}"""),format.raw/*23.44*/("""}"""),format.raw/*23.45*/(""", """),format.raw/*23.47*/("""{"""),format.raw/*23.48*/("""{"""),format.raw/*23.49*/("""project.country"""),format.raw/*23.64*/("""}"""),format.raw/*23.65*/("""}"""),format.raw/*23.66*/("""
                            """),format.raw/*24.29*/("""<br>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Card
                            </h3>
                        </div>
                        <div class="panel-body">
                            <ul class="list-group">
                                <div ng-repeat="card in project.cards">
                                    <div ng-if="card.type == 'date' ">
                                        <div data-ng-include data-src="'/assets/partials/date-card-display.html'"></div>
                                    </div>
                                    <div ng-if="card.type == 'item' ">
                                        <div data-ng-include data-src="'/assets/partials/item-card-display.html'"></div>
                                    </div>
                                </div>
                            </ul>

                        </div>
                    </div>
                    <pre>form = """),format.raw/*47.33*/("""{"""),format.raw/*47.34*/("""{"""),format.raw/*47.35*/("""project | json"""),format.raw/*47.49*/("""}"""),format.raw/*47.50*/("""}"""),format.raw/*47.51*/("""</pre>
                </div>

            </div>
        </div>

        <div class="col-md-4">


            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Contributions
                    </h3>
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <ul class="list-group">
                            <div ng-repeat="card in project.cards">
                                <div ng-repeat="card in project.cards">
                                    <div ng-repeat="contribution in card.contributions ">
                                        <div ng-if="contribution.status != 'Canceled' ">
                                            <div ng-if="contribution.type == 'date' ">
                                                <div data-ng-include data-src="'/assets/partials/date-card-contribution.html'"></div>
                                            </div>
                                            <div ng-if="contribution.type == 'item' ">
                                                <div data-ng-include data-src="'/assets/partials/item-card-contribution.html'"></div>
                                            </div>
                                            <pre>form = """),format.raw/*75.57*/("""{"""),format.raw/*75.58*/("""{"""),format.raw/*75.59*/("""contribution | json"""),format.raw/*75.78*/("""}"""),format.raw/*75.79*/("""}"""),format.raw/*75.80*/("""</pre>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </ul>
                    </ul>
                </div>
            </div>
        </div>
    </div>







</div>

 
    
""")))}))}
  }

  def render(projectId:java.lang.String): play.twirl.api.HtmlFormat.Appendable = apply(projectId)

  def f:((java.lang.String) => play.twirl.api.HtmlFormat.Appendable) = (projectId) => apply(projectId)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Nov 07 13:54:58 PST 2014
                  SOURCE: /Users/antoine_lefebvre/Github/fixit/app/views/projectview.scala.html
                  HASH: 8c614f3197d74f076e52c899d289d936fa6d16c9
                  MATRIX: 739->1|871->30|899->49|926->51|945->62|984->64|1016->70|1101->129|1130->138|1351->331|1380->332|1409->333|1449->345|1478->346|1507->347|1636->449|1651->455|1709->492|1821->576|1850->577|1879->578|1919->590|1948->591|1977->592|2006->593|2042->601|2071->602|2100->603|2144->619|2173->620|2202->621|2231->622|2292->655|2321->656|2350->657|2397->676|2426->677|2455->678|2619->814|2648->815|2677->816|2717->828|2746->829|2775->830|2805->832|2834->833|2863->834|2906->849|2935->850|2964->851|3021->880|4150->1981|4179->1982|4208->1983|4250->1997|4279->1998|4308->1999|5709->3372|5738->3373|5767->3374|5814->3393|5843->3394|5872->3395
                  LINES: 26->1|29->1|31->4|32->5|32->5|32->5|34->7|34->7|34->7|40->13|40->13|40->13|40->13|40->13|40->13|44->17|44->17|44->17|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|46->19|47->20|47->20|47->20|47->20|47->20|47->20|50->23|50->23|50->23|50->23|50->23|50->23|50->23|50->23|50->23|50->23|50->23|50->23|51->24|74->47|74->47|74->47|74->47|74->47|74->47|102->75|102->75|102->75|102->75|102->75|102->75
                  -- GENERATED --
              */
          