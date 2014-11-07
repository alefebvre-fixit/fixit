// @SOURCE:/Users/antoine_lefebvre/Github/fixit/conf/routes
// @HASH:24edfbe502501004d05e355b8bd9995a63483d0a
// @DATE:Fri Nov 07 13:54:57 PST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:21
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:11
// @LINE:8
package controllers {

// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:8
class ReverseProjectController {
    

// @LINE:30
def user(user:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "users/" + implicitly[PathBindable[String]].unbind("user", dynamicString(user)))
}
                        

// @LINE:25
// @LINE:8
def projects(): Call = {
   () match {
// @LINE:25
case ()  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "projects")
                                         
   }
}
                                                

// @LINE:28
def newProject(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "projects/new/")
}
                        

// @LINE:27
def editProject(id:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "projects/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)) + "/edit")
}
                        

// @LINE:29
def me(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "me/")
}
                        

// @LINE:26
def viewProject(id:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "projects/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                        
    
}
                          

// @LINE:11
class ReverseAssets {
    

// @LINE:11
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        
    
}
                          

// @LINE:21
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
class ReverseAccountController {
    

// @LINE:21
def userProfile(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "setting/profile")
}
                        

// @LINE:16
def signup(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "signup")
}
                        

// @LINE:17
def logout(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "logout")
}
                        

// @LINE:18
def authenticate(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "authenticate")
}
                        

// @LINE:15
def login(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "login")
}
                        
    
}
                          
}
                  

// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
package controllers.api {

// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
class ReverseAccountAPIController {
    

// @LINE:53
def accounts(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "api/accounts")
}
                        

// @LINE:54
def signup(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/signup")
}
                        

// @LINE:51
def account(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "api/account")
}
                        

// @LINE:52
def accountSummary(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "api/account/summary")
}
                        
    
}
                          

// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
class ReverseProjectAPIController {
    

// @LINE:37
def project(id:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "api/projects/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                        

// @LINE:36
def projects(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "api/projects")
}
                        

// @LINE:39
def projectByOwner(id:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "api/user/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)) + "/projects")
}
                        

// @LINE:40
def deleteProject(id:String): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/projects/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)) + "/delete")
}
                        

// @LINE:46
def contribution(projectId:String, contributionId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "api/projects/" + implicitly[PathBindable[String]].unbind("projectId", dynamicString(projectId)) + "/contributions/" + implicitly[PathBindable[String]].unbind("contributionId", dynamicString(contributionId)))
}
                        

// @LINE:44
def provide(projectId:String, cardId:String, quantity:Integer = 1): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/projects/" + implicitly[PathBindable[String]].unbind("projectId", dynamicString(projectId)) + "/cards/" + implicitly[PathBindable[String]].unbind("cardId", dynamicString(cardId)) + "/provide" + queryString(List(if(quantity == 1) None else Some(implicitly[QueryStringBindable[Integer]].unbind("quantity", quantity)))))
}
                        

// @LINE:45
def card(projectId:String, cardId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "api/projects/" + implicitly[PathBindable[String]].unbind("projectId", dynamicString(projectId)) + "/cards/" + implicitly[PathBindable[String]].unbind("cardId", dynamicString(cardId)))
}
                        

// @LINE:38
def save(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/projects")
}
                        

// @LINE:47
def cancelContribution(projectId:String, contributionId:String): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/projects/" + implicitly[PathBindable[String]].unbind("projectId", dynamicString(projectId)) + "/contributions/" + implicitly[PathBindable[String]].unbind("contributionId", dynamicString(contributionId)) + "/cancel")
}
                        
    
}
                          
}
                  


// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:21
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:11
// @LINE:8
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:8
class ReverseProjectController {
    

// @LINE:30
def user : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ProjectController.user",
   """
      function(user) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("user", encodeURIComponent(user))})
      }
   """
)
                        

// @LINE:25
// @LINE:8
def projects : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ProjectController.projects",
   """
      function() {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "projects"})
      }
      }
   """
)
                        

// @LINE:28
def newProject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ProjectController.newProject",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "projects/new/"})
      }
   """
)
                        

// @LINE:27
def editProject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ProjectController.editProject",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/edit"})
      }
   """
)
                        

// @LINE:29
def me : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ProjectController.me",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "me/"})
      }
   """
)
                        

// @LINE:26
def viewProject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ProjectController.viewProject",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        
    
}
              

// @LINE:11
class ReverseAssets {
    

// @LINE:11
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:21
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
class ReverseAccountController {
    

// @LINE:21
def userProfile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AccountController.userProfile",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "setting/profile"})
      }
   """
)
                        

// @LINE:16
def signup : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AccountController.signup",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
      }
   """
)
                        

// @LINE:17
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AccountController.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

// @LINE:18
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AccountController.authenticate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "authenticate"})
      }
   """
)
                        

// @LINE:15
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AccountController.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        
    
}
              
}
        

// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
package controllers.api.javascript {
import ReverseRouteContext.empty

// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
class ReverseAccountAPIController {
    

// @LINE:53
def accounts : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.AccountAPIController.accounts",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/accounts"})
      }
   """
)
                        

// @LINE:54
def signup : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.AccountAPIController.signup",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/signup"})
      }
   """
)
                        

// @LINE:51
def account : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.AccountAPIController.account",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/account"})
      }
   """
)
                        

// @LINE:52
def accountSummary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.AccountAPIController.accountSummary",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/account/summary"})
      }
   """
)
                        
    
}
              

// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
class ReverseProjectAPIController {
    

// @LINE:37
def project : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.ProjectAPIController.project",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:36
def projects : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.ProjectAPIController.projects",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/projects"})
      }
   """
)
                        

// @LINE:39
def projectByOwner : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.ProjectAPIController.projectByOwner",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/user/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/projects"})
      }
   """
)
                        

// @LINE:40
def deleteProject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.ProjectAPIController.deleteProject",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/delete"})
      }
   """
)
                        

// @LINE:46
def contribution : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.ProjectAPIController.contribution",
   """
      function(projectId,contributionId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("projectId", encodeURIComponent(projectId)) + "/contributions/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("contributionId", encodeURIComponent(contributionId))})
      }
   """
)
                        

// @LINE:44
def provide : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.ProjectAPIController.provide",
   """
      function(projectId,cardId,quantity) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("projectId", encodeURIComponent(projectId)) + "/cards/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("cardId", encodeURIComponent(cardId)) + "/provide" + _qS([(quantity == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("quantity", quantity))])})
      }
   """
)
                        

// @LINE:45
def card : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.ProjectAPIController.card",
   """
      function(projectId,cardId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("projectId", encodeURIComponent(projectId)) + "/cards/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("cardId", encodeURIComponent(cardId))})
      }
   """
)
                        

// @LINE:38
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.ProjectAPIController.save",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/projects"})
      }
   """
)
                        

// @LINE:47
def cancelContribution : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.api.ProjectAPIController.cancelContribution",
   """
      function(projectId,contributionId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("projectId", encodeURIComponent(projectId)) + "/contributions/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("contributionId", encodeURIComponent(contributionId)) + "/cancel"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:21
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:11
// @LINE:8
package controllers.ref {


// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:8
class ReverseProjectController {
    

// @LINE:30
def user(user:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ProjectController.user(user), HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "user", Seq(classOf[String]), "GET", """""", _prefix + """users/$user<[^/]+>""")
)
                      

// @LINE:8
def projects(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ProjectController.projects(), HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "projects", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:28
def newProject(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ProjectController.newProject(), HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "newProject", Seq(), "GET", """""", _prefix + """projects/new/""")
)
                      

// @LINE:27
def editProject(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ProjectController.editProject(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "editProject", Seq(classOf[String]), "GET", """""", _prefix + """projects/$id<[^/]+>/edit""")
)
                      

// @LINE:29
def me(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ProjectController.me(), HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "me", Seq(), "GET", """""", _prefix + """me/""")
)
                      

// @LINE:26
def viewProject(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ProjectController.viewProject(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.ProjectController", "viewProject", Seq(classOf[String]), "GET", """""", _prefix + """projects/$id<[^/]+>""")
)
                      
    
}
                          

// @LINE:11
class ReverseAssets {
    

// @LINE:11
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:21
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
class ReverseAccountController {
    

// @LINE:21
def userProfile(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AccountController.userProfile(), HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "userProfile", Seq(), "GET", """ User""", _prefix + """setting/profile""")
)
                      

// @LINE:16
def signup(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AccountController.signup(), HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "signup", Seq(), "GET", """""", _prefix + """signup""")
)
                      

// @LINE:17
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AccountController.logout(), HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "logout", Seq(), "GET", """""", _prefix + """logout""")
)
                      

// @LINE:18
def authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AccountController.authenticate(), HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "authenticate", Seq(), "POST", """""", _prefix + """authenticate""")
)
                      

// @LINE:15
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AccountController.login(), HandlerDef(this.getClass.getClassLoader, "", "controllers.AccountController", "login", Seq(), "GET", """ Login""", _prefix + """login""")
)
                      
    
}
                          
}
        

// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
package controllers.api.ref {


// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
class ReverseAccountAPIController {
    

// @LINE:53
def accounts(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.AccountAPIController.accounts(), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.AccountAPIController", "accounts", Seq(), "GET", """""", _prefix + """api/accounts""")
)
                      

// @LINE:54
def signup(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.AccountAPIController.signup(), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.AccountAPIController", "signup", Seq(), "POST", """""", _prefix + """api/signup""")
)
                      

// @LINE:51
def account(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.AccountAPIController.account(), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.AccountAPIController", "account", Seq(), "GET", """""", _prefix + """api/account""")
)
                      

// @LINE:52
def accountSummary(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.AccountAPIController.accountSummary(), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.AccountAPIController", "accountSummary", Seq(), "GET", """""", _prefix + """api/account/summary""")
)
                      
    
}
                          

// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
class ReverseProjectAPIController {
    

// @LINE:37
def project(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.ProjectAPIController.project(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "project", Seq(classOf[String]), "GET", """""", _prefix + """api/projects/$id<[^/]+>""")
)
                      

// @LINE:36
def projects(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.ProjectAPIController.projects(), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "projects", Seq(), "GET", """ API Project""", _prefix + """api/projects""")
)
                      

// @LINE:39
def projectByOwner(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.ProjectAPIController.projectByOwner(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "projectByOwner", Seq(classOf[String]), "GET", """""", _prefix + """api/user/$id<[^/]+>/projects""")
)
                      

// @LINE:40
def deleteProject(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.ProjectAPIController.deleteProject(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "deleteProject", Seq(classOf[String]), "POST", """""", _prefix + """api/projects/$id<[^/]+>/delete""")
)
                      

// @LINE:46
def contribution(projectId:String, contributionId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.ProjectAPIController.contribution(projectId, contributionId), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "contribution", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """api/projects/$projectId<[^/]+>/contributions/$contributionId<[^/]+>""")
)
                      

// @LINE:44
def provide(projectId:String, cardId:String, quantity:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.ProjectAPIController.provide(projectId, cardId, quantity), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "provide", Seq(classOf[String], classOf[String], classOf[Integer]), "POST", """""", _prefix + """api/projects/$projectId<[^/]+>/cards/$cardId<[^/]+>/provide""")
)
                      

// @LINE:45
def card(projectId:String, cardId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.ProjectAPIController.card(projectId, cardId), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "card", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """api/projects/$projectId<[^/]+>/cards/$cardId<[^/]+>""")
)
                      

// @LINE:38
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.ProjectAPIController.save(), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "save", Seq(), "POST", """""", _prefix + """api/projects""")
)
                      

// @LINE:47
def cancelContribution(projectId:String, contributionId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.api.ProjectAPIController.cancelContribution(projectId, contributionId), HandlerDef(this.getClass.getClassLoader, "", "controllers.api.ProjectAPIController", "cancelContribution", Seq(classOf[String], classOf[String]), "POST", """""", _prefix + """api/projects/$projectId<[^/]+>/contributions/$contributionId<[^/]+>/cancel""")
)
                      
    
}
                          
}
        
    