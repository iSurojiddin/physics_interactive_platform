package controllers

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import org.webjars.play.WebJarsUtil
import play.api.Configuration
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.libs.Files.logger
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import protocols.AppProtocol._

import java.net.URL
import java.time.LocalDateTime
import javax.inject._
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}
import scala.concurrent.duration.DurationInt
import scala.util.Random

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents,
                               val configuration: Configuration,
                               indexTemplate: views.html.index,
                               signinTemplate: views.html.signin,
                               signupTemplate: views.html.signup,
                               topicsTemplate: views.html.topics,
                               topicTemplate: views.html.topic,
                               testTemplate: views.html.test,
                               resultsTemplate: views.html.results,
                               @Named("report-manager") val lessonManager: ActorRef,
                               implicit val actorSystem: ActorSystem,
                               implicit val webJarsUtil: WebJarsUtil)
  extends BaseController {

    implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher
    implicit val defaultTimeout: Timeout = Timeout(30.seconds)

    def index = Action {
      Ok(indexTemplate())
    }

    def signin = Action {
      Ok(signinTemplate())
    }

    def signup = Action {
      Ok(signupTemplate())
    }

    def topics = Action {
      Ok(topicsTemplate())
    }

    def topic = Action {
      Ok(topicTemplate())
    }

    def test = Action {
      Ok(testTemplate())
    }

    def results = Action {
      Ok(resultsTemplate())
    }
}
