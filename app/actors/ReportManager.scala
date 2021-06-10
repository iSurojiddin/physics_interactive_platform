package actors

import akka.actor.{Actor, ActorSystem}
import akka.pattern.pipe
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging
import doobie.common.DoobieUtil
import play.api.{Configuration, Environment}
import protocols.AppProtocol

import javax.inject.Inject
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContextExecutor, Future}

class ReportManager @Inject()(val configuration: Configuration,
                               val environment: Environment)
                              (implicit val actorSystem: ActorSystem)
  extends Actor with LazyLogging {


  implicit val executionContext: ExecutionContextExecutor = context.dispatcher
  implicit val defaultTimeout: Timeout = Timeout(60.seconds)
//  private val DoobieModule = DoobieUtil.doobieModule(configuration)

  override def receive: Receive = ???
}
