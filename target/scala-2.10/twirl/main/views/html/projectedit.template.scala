
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
object projectedit extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(projectId: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.21*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("New")/*5.13*/ {_display_(Seq[Any](format.raw/*5.15*/("""
    
"""),format.raw/*7.1*/("""<div ng-controller="EditProjectController" ng-init="init('"""),_display_(/*7.60*/projectId),format.raw/*7.69*/("""')">


    <div>
        <script type="text/ng-template" id="myModalContent.html">
            <div class="modal-header">
                <h4 class="modal-title">Delete Project</h4>
            </div>
            <div class="modal-body">
                This action will permanently delete the selected project. Are you sure?
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" ng-click="ok()">OK</button>
                <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
            </div>
        </script>
    </div>


    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Edit Project</h3>
        </div>
        <div class="panel-body">

           <form name="projectForm" ng-submit="saveProject()" class="form-horizontal" novalidate>

                <div class="control-group">
                    <label class="control-label" for="project-name">Name:</label>
                    <div class="controls">
                        <input ng-model="project.name" class="form-control" id="project-name" placeholder="Name">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="project-description">Description:</label>
                    <div class="controls">
                        <textarea ng-model="project.description" class="form-control" id="project-description" placeholder="Description"></textarea>
                    </div>
                </div>

                <br>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <div class="btn-group" dropdown is-open="status.isopen">
                                <button type="button" class="btn btn-primary dropdown-toggle" ng-disabled="disabled">
                                    Card <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#/item">Add Item</a></li>
                                    <li><a href="#/date">Add Date</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">Something else here</a></li>
                                </ul>
                            </div>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <br>
                        <div ng-view></div>
                        <br>
                        <ul class="list-group">
                            <div ng-repeat="card in project.cards" ng-animate=" 'slide' ">
                                <div ng-if="card.type == 'date' ">
                                    <div data-ng-include data-src="'/assets/partials/date-card-preview.html'"></div>
                                </div>
                                <div ng-if="card.type == 'item' ">
                                    <div data-ng-include data-src="'/assets/partials/item-card-preview.html'"></div>
                                </div>
                            </div>
                        </ul>

                    </div>
                </div>
                <div class="form-actions">
                    <button class="btn btn-primary">Save</button>
                    <button type='button' ng-disabled="!project.id" class="btn btn-danger" ng-click="removeProject()"><span class="glyphicon glyphicon-remove-sign"></span> Delete</button>
                </div>
            </form>
            <br>
            <pre>form = """),format.raw/*89.25*/("""{"""),format.raw/*89.26*/("""{"""),format.raw/*89.27*/("""project | json"""),format.raw/*89.41*/("""}"""),format.raw/*89.42*/("""}"""),format.raw/*89.43*/("""</pre>
        </div>



        </div>
    </div>





</div>

 
    
""")))}))}
  }

  def render(projectId:String): play.twirl.api.HtmlFormat.Appendable = apply(projectId)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (projectId) => apply(projectId)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Nov 07 13:54:58 PST 2014
                  SOURCE: /Users/antoine_lefebvre/Github/fixit/app/views/projectedit.scala.html
                  HASH: b381a2942702fad984b8773087f191d6ce3aadb7
                  MATRIX: 729->1|851->20|879->39|906->41|925->52|964->54|996->60|1081->119|1110->128|4931->3921|4960->3922|4989->3923|5031->3937|5060->3938|5089->3939
                  LINES: 26->1|29->1|31->4|32->5|32->5|32->5|34->7|34->7|34->7|116->89|116->89|116->89|116->89|116->89|116->89
                  -- GENERATED --
              */
          