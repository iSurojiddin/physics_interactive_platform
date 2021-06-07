package actors

import java.time.LocalDateTime

import akka.actor.Actor
import akka.pattern.pipe
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging
import doobie.common.DoobieUtil
import javax.inject.Inject
import play.api.http.Status.OK
import play.api.libs.ws.WSClient
import play.api.{Configuration, Environment}

import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContext, Future}

class ReportManager @Inject()(val configuration: Configuration,
                              val environment: Environment)
                             (implicit val ec: ExecutionContext)
  extends Actor with LazyLogging {

  implicit val defaultTimeout: Timeout = Timeout(60.seconds)


}
