package controllers

import akka.actor.typed.ActorRef

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(
                                cc: ControllerComponents,
                                @Named("report-manager") val reportManager: ActorRef
  ) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index())
  }

}
