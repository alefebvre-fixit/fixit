// @SOURCE:/Users/antoine_lefebvre/Github/fixit/conf/routes
// @HASH:24edfbe502501004d05e355b8bd9995a63483d0a
// @DATE:Fri Nov 07 13:54:57 PST 2014


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:8
private[this] lazy val controllers_ProjectController_projects0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_ProjectController_projects0_invoker = createInvoker(
controllers.ProjectController.projects(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "projects", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:11
private[this] lazy val controllers_Assets_at1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at1_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:15
private[this] lazy val controllers_AccountController_login2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_AccountController_login2_invoker = createInvoker(
controllers.AccountController.login(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "login", Nil,"GET", """ Login""", Routes.prefix + """login"""))
        

// @LINE:16
private[this] lazy val controllers_AccountController_signup3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
private[this] lazy val controllers_AccountController_signup3_invoker = createInvoker(
controllers.AccountController.signup(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "signup", Nil,"GET", """""", Routes.prefix + """signup"""))
        

// @LINE:17
private[this] lazy val controllers_AccountController_logout4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
private[this] lazy val controllers_AccountController_logout4_invoker = createInvoker(
controllers.AccountController.logout(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "logout", Nil,"GET", """""", Routes.prefix + """logout"""))
        

// @LINE:18
private[this] lazy val controllers_AccountController_authenticate5_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("authenticate"))))
private[this] lazy val controllers_AccountController_authenticate5_invoker = createInvoker(
controllers.AccountController.authenticate(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "authenticate", Nil,"POST", """""", Routes.prefix + """authenticate"""))
        

// @LINE:21
private[this] lazy val controllers_AccountController_userProfile6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("setting/profile"))))
private[this] lazy val controllers_AccountController_userProfile6_invoker = createInvoker(
controllers.AccountController.userProfile(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "userProfile", Nil,"GET", """ User""", Routes.prefix + """setting/profile"""))
        

// @LINE:25
private[this] lazy val controllers_ProjectController_projects7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("projects"))))
private[this] lazy val controllers_ProjectController_projects7_invoker = createInvoker(
controllers.ProjectController.projects(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "projects", Nil,"GET", """ Projects          """, Routes.prefix + """projects"""))
        

// @LINE:26
private[this] lazy val controllers_ProjectController_viewProject8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("projects/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_ProjectController_viewProject8_invoker = createInvoker(
controllers.ProjectController.viewProject(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "viewProject", Seq(classOf[String]),"GET", """""", Routes.prefix + """projects/$id<[^/]+>"""))
        

// @LINE:27
private[this] lazy val controllers_ProjectController_editProject9_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("projects/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
private[this] lazy val controllers_ProjectController_editProject9_invoker = createInvoker(
controllers.ProjectController.editProject(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "editProject", Seq(classOf[String]),"GET", """""", Routes.prefix + """projects/$id<[^/]+>/edit"""))
        

// @LINE:28
private[this] lazy val controllers_ProjectController_newProject10_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("projects/new/"))))
private[this] lazy val controllers_ProjectController_newProject10_invoker = createInvoker(
controllers.ProjectController.newProject(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "newProject", Nil,"GET", """""", Routes.prefix + """projects/new/"""))
        

// @LINE:29
private[this] lazy val controllers_ProjectController_me11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("me/"))))
private[this] lazy val controllers_ProjectController_me11_invoker = createInvoker(
controllers.ProjectController.me(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "me", Nil,"GET", """""", Routes.prefix + """me/"""))
        

// @LINE:30
private[this] lazy val controllers_ProjectController_user12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/"),DynamicPart("user", """[^/]+""",true))))
private[this] lazy val controllers_ProjectController_user12_invoker = createInvoker(
controllers.ProjectController.user(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "user", Seq(classOf[String]),"GET", """""", Routes.prefix + """users/$user<[^/]+>"""))
        

// @LINE:36
private[this] lazy val controllers_api_ProjectAPIController_projects13_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/projects"))))
private[this] lazy val controllers_api_ProjectAPIController_projects13_invoker = createInvoker(
controllers.api.ProjectAPIController.projects(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "projects", Nil,"GET", """ API Project""", Routes.prefix + """api/projects"""))
        

// @LINE:37
private[this] lazy val controllers_api_ProjectAPIController_project14_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/projects/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_api_ProjectAPIController_project14_invoker = createInvoker(
controllers.api.ProjectAPIController.project(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "project", Seq(classOf[String]),"GET", """""", Routes.prefix + """api/projects/$id<[^/]+>"""))
        

// @LINE:38
private[this] lazy val controllers_api_ProjectAPIController_save15_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/projects"))))
private[this] lazy val controllers_api_ProjectAPIController_save15_invoker = createInvoker(
controllers.api.ProjectAPIController.save(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "save", Nil,"POST", """""", Routes.prefix + """api/projects"""))
        

// @LINE:39
private[this] lazy val controllers_api_ProjectAPIController_projectByOwner16_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/user/"),DynamicPart("id", """[^/]+""",true),StaticPart("/projects"))))
private[this] lazy val controllers_api_ProjectAPIController_projectByOwner16_invoker = createInvoker(
controllers.api.ProjectAPIController.projectByOwner(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "projectByOwner", Seq(classOf[String]),"GET", """""", Routes.prefix + """api/user/$id<[^/]+>/projects"""))
        

// @LINE:40
private[this] lazy val controllers_api_ProjectAPIController_deleteProject17_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/projects/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_api_ProjectAPIController_deleteProject17_invoker = createInvoker(
controllers.api.ProjectAPIController.deleteProject(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "deleteProject", Seq(classOf[String]),"POST", """""", Routes.prefix + """api/projects/$id<[^/]+>/delete"""))
        

// @LINE:44
private[this] lazy val controllers_api_ProjectAPIController_provide18_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/projects/"),DynamicPart("projectId", """[^/]+""",true),StaticPart("/cards/"),DynamicPart("cardId", """[^/]+""",true),StaticPart("/provide"))))
private[this] lazy val controllers_api_ProjectAPIController_provide18_invoker = createInvoker(
controllers.api.ProjectAPIController.provide(fakeValue[String], fakeValue[String], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "provide", Seq(classOf[String], classOf[String], classOf[Integer]),"POST", """""", Routes.prefix + """api/projects/$projectId<[^/]+>/cards/$cardId<[^/]+>/provide"""))
        

// @LINE:45
private[this] lazy val controllers_api_ProjectAPIController_card19_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/projects/"),DynamicPart("projectId", """[^/]+""",true),StaticPart("/cards/"),DynamicPart("cardId", """[^/]+""",true))))
private[this] lazy val controllers_api_ProjectAPIController_card19_invoker = createInvoker(
controllers.api.ProjectAPIController.card(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "card", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """api/projects/$projectId<[^/]+>/cards/$cardId<[^/]+>"""))
        

// @LINE:46
private[this] lazy val controllers_api_ProjectAPIController_contribution20_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/projects/"),DynamicPart("projectId", """[^/]+""",true),StaticPart("/contributions/"),DynamicPart("contributionId", """[^/]+""",true))))
private[this] lazy val controllers_api_ProjectAPIController_contribution20_invoker = createInvoker(
controllers.api.ProjectAPIController.contribution(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "contribution", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """api/projects/$projectId<[^/]+>/contributions/$contributionId<[^/]+>"""))
        

// @LINE:47
private[this] lazy val controllers_api_ProjectAPIController_cancelContribution21_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/projects/"),DynamicPart("projectId", """[^/]+""",true),StaticPart("/contributions/"),DynamicPart("contributionId", """[^/]+""",true),StaticPart("/cancel"))))
private[this] lazy val controllers_api_ProjectAPIController_cancelContribution21_invoker = createInvoker(
controllers.api.ProjectAPIController.cancelContribution(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "cancelContribution", Seq(classOf[String], classOf[String]),"POST", """""", Routes.prefix + """api/projects/$projectId<[^/]+>/contributions/$contributionId<[^/]+>/cancel"""))
        

// @LINE:51
private[this] lazy val controllers_api_AccountAPIController_account22_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/account"))))
private[this] lazy val controllers_api_AccountAPIController_account22_invoker = createInvoker(
controllers.api.AccountAPIController.account(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.AccountAPIController", "account", Nil,"GET", """""", Routes.prefix + """api/account"""))
        

// @LINE:52
private[this] lazy val controllers_api_AccountAPIController_accountSummary23_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/account/summary"))))
private[this] lazy val controllers_api_AccountAPIController_accountSummary23_invoker = createInvoker(
controllers.api.AccountAPIController.accountSummary(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.AccountAPIController", "accountSummary", Nil,"GET", """""", Routes.prefix + """api/account/summary"""))
        

// @LINE:53
private[this] lazy val controllers_api_AccountAPIController_accounts24_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/accounts"))))
private[this] lazy val controllers_api_AccountAPIController_accounts24_invoker = createInvoker(
controllers.api.AccountAPIController.accounts(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.AccountAPIController", "accounts", Nil,"GET", """""", Routes.prefix + """api/accounts"""))
        

// @LINE:54
private[this] lazy val controllers_api_AccountAPIController_signup25_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/signup"))))
private[this] lazy val controllers_api_AccountAPIController_signup25_invoker = createInvoker(
controllers.api.AccountAPIController.signup(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.api.AccountAPIController", "signup", Nil,"POST", """""", Routes.prefix + """api/signup"""))
        
def documentation = List(("""GET""", prefix,"""controllers.ProjectController.projects()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.AccountController.login()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.AccountController.signup()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.AccountController.logout()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """authenticate""","""controllers.AccountController.authenticate()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """setting/profile""","""controllers.AccountController.userProfile()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """projects""","""controllers.ProjectController.projects()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """projects/$id<[^/]+>""","""controllers.ProjectController.viewProject(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """projects/$id<[^/]+>/edit""","""controllers.ProjectController.editProject(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """projects/new/""","""controllers.ProjectController.newProject()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """me/""","""controllers.ProjectController.me()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/$user<[^/]+>""","""controllers.ProjectController.user(user:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/projects""","""controllers.api.ProjectAPIController.projects()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/projects/$id<[^/]+>""","""controllers.api.ProjectAPIController.project(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/projects""","""controllers.api.ProjectAPIController.save()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/user/$id<[^/]+>/projects""","""controllers.api.ProjectAPIController.projectByOwner(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/projects/$id<[^/]+>/delete""","""controllers.api.ProjectAPIController.deleteProject(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/projects/$projectId<[^/]+>/cards/$cardId<[^/]+>/provide""","""controllers.api.ProjectAPIController.provide(projectId:String, cardId:String, quantity:Integer ?= 1)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/projects/$projectId<[^/]+>/cards/$cardId<[^/]+>""","""controllers.api.ProjectAPIController.card(projectId:String, cardId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/projects/$projectId<[^/]+>/contributions/$contributionId<[^/]+>""","""controllers.api.ProjectAPIController.contribution(projectId:String, contributionId:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/projects/$projectId<[^/]+>/contributions/$contributionId<[^/]+>/cancel""","""controllers.api.ProjectAPIController.cancelContribution(projectId:String, contributionId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/account""","""controllers.api.AccountAPIController.account()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/account/summary""","""controllers.api.AccountAPIController.accountSummary()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/accounts""","""controllers.api.AccountAPIController.accounts()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/signup""","""controllers.api.AccountAPIController.signup()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:8
case controllers_ProjectController_projects0_route(params) => {
   call { 
        controllers_ProjectController_projects0_invoker.call(controllers.ProjectController.projects())
   }
}
        

// @LINE:11
case controllers_Assets_at1_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at1_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:15
case controllers_AccountController_login2_route(params) => {
   call { 
        controllers_AccountController_login2_invoker.call(controllers.AccountController.login())
   }
}
        

// @LINE:16
case controllers_AccountController_signup3_route(params) => {
   call { 
        controllers_AccountController_signup3_invoker.call(controllers.AccountController.signup())
   }
}
        

// @LINE:17
case controllers_AccountController_logout4_route(params) => {
   call { 
        controllers_AccountController_logout4_invoker.call(controllers.AccountController.logout())
   }
}
        

// @LINE:18
case controllers_AccountController_authenticate5_route(params) => {
   call { 
        controllers_AccountController_authenticate5_invoker.call(controllers.AccountController.authenticate())
   }
}
        

// @LINE:21
case controllers_AccountController_userProfile6_route(params) => {
   call { 
        controllers_AccountController_userProfile6_invoker.call(controllers.AccountController.userProfile())
   }
}
        

// @LINE:25
case controllers_ProjectController_projects7_route(params) => {
   call { 
        controllers_ProjectController_projects7_invoker.call(controllers.ProjectController.projects())
   }
}
        

// @LINE:26
case controllers_ProjectController_viewProject8_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ProjectController_viewProject8_invoker.call(controllers.ProjectController.viewProject(id))
   }
}
        

// @LINE:27
case controllers_ProjectController_editProject9_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ProjectController_editProject9_invoker.call(controllers.ProjectController.editProject(id))
   }
}
        

// @LINE:28
case controllers_ProjectController_newProject10_route(params) => {
   call { 
        controllers_ProjectController_newProject10_invoker.call(controllers.ProjectController.newProject())
   }
}
        

// @LINE:29
case controllers_ProjectController_me11_route(params) => {
   call { 
        controllers_ProjectController_me11_invoker.call(controllers.ProjectController.me())
   }
}
        

// @LINE:30
case controllers_ProjectController_user12_route(params) => {
   call(params.fromPath[String]("user", None)) { (user) =>
        controllers_ProjectController_user12_invoker.call(controllers.ProjectController.user(user))
   }
}
        

// @LINE:36
case controllers_api_ProjectAPIController_projects13_route(params) => {
   call { 
        controllers_api_ProjectAPIController_projects13_invoker.call(controllers.api.ProjectAPIController.projects())
   }
}
        

// @LINE:37
case controllers_api_ProjectAPIController_project14_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_api_ProjectAPIController_project14_invoker.call(controllers.api.ProjectAPIController.project(id))
   }
}
        

// @LINE:38
case controllers_api_ProjectAPIController_save15_route(params) => {
   call { 
        controllers_api_ProjectAPIController_save15_invoker.call(controllers.api.ProjectAPIController.save())
   }
}
        

// @LINE:39
case controllers_api_ProjectAPIController_projectByOwner16_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_api_ProjectAPIController_projectByOwner16_invoker.call(controllers.api.ProjectAPIController.projectByOwner(id))
   }
}
        

// @LINE:40
case controllers_api_ProjectAPIController_deleteProject17_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_api_ProjectAPIController_deleteProject17_invoker.call(controllers.api.ProjectAPIController.deleteProject(id))
   }
}
        

// @LINE:44
case controllers_api_ProjectAPIController_provide18_route(params) => {
   call(params.fromPath[String]("projectId", None), params.fromPath[String]("cardId", None), params.fromQuery[Integer]("quantity", Some(1))) { (projectId, cardId, quantity) =>
        controllers_api_ProjectAPIController_provide18_invoker.call(controllers.api.ProjectAPIController.provide(projectId, cardId, quantity))
   }
}
        

// @LINE:45
case controllers_api_ProjectAPIController_card19_route(params) => {
   call(params.fromPath[String]("projectId", None), params.fromPath[String]("cardId", None)) { (projectId, cardId) =>
        controllers_api_ProjectAPIController_card19_invoker.call(controllers.api.ProjectAPIController.card(projectId, cardId))
   }
}
        

// @LINE:46
case controllers_api_ProjectAPIController_contribution20_route(params) => {
   call(params.fromPath[String]("projectId", None), params.fromPath[String]("contributionId", None)) { (projectId, contributionId) =>
        controllers_api_ProjectAPIController_contribution20_invoker.call(controllers.api.ProjectAPIController.contribution(projectId, contributionId))
   }
}
        

// @LINE:47
case controllers_api_ProjectAPIController_cancelContribution21_route(params) => {
   call(params.fromPath[String]("projectId", None), params.fromPath[String]("contributionId", None)) { (projectId, contributionId) =>
        controllers_api_ProjectAPIController_cancelContribution21_invoker.call(controllers.api.ProjectAPIController.cancelContribution(projectId, contributionId))
   }
}
        

// @LINE:51
case controllers_api_AccountAPIController_account22_route(params) => {
   call { 
        controllers_api_AccountAPIController_account22_invoker.call(controllers.api.AccountAPIController.account())
   }
}
        

// @LINE:52
case controllers_api_AccountAPIController_accountSummary23_route(params) => {
   call { 
        controllers_api_AccountAPIController_accountSummary23_invoker.call(controllers.api.AccountAPIController.accountSummary())
   }
}
        

// @LINE:53
case controllers_api_AccountAPIController_accounts24_route(params) => {
   call { 
        controllers_api_AccountAPIController_accounts24_invoker.call(controllers.api.AccountAPIController.accounts())
   }
}
        

// @LINE:54
case controllers_api_AccountAPIController_signup25_route(params) => {
   call { 
        controllers_api_AccountAPIController_signup25_invoker.call(controllers.api.AccountAPIController.signup())
   }
}
        
}

}
     