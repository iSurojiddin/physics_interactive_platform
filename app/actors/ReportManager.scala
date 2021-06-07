package actors

import akka.actor.Actor
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging
import play.api.{Configuration, Environment}

import javax.inject.Inject
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

class ReportManager @Inject()(val configuration: Configuration,
                              val environment: Environment)
                             (implicit val ec: ExecutionContext)
  extends Actor with LazyLogging {

  implicit val defaultTimeout: Timeout = Timeout(60.seconds)

  override def receive: Receive = ???
}
