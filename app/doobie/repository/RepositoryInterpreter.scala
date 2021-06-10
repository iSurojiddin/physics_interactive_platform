package doobie.repository

import cats.effect.Bracket
import com.typesafe.scalalogging.LazyLogging
import doobie._
import doobie.domain.DataRepositoryAlgebra
import doobie.implicits._
import doobie.repository.SQLPagination.paginate
import doobie.implicits.javasql._
import doobie.implicits.javatime._
import doobie.util.Read
import protocols.AppProtocol

import java.sql.Timestamp
import java.time.{LocalDate, LocalDateTime}

object MessageSQL extends CommonSQL with LazyLogging {

  implicit val han: LogHandler = LogHandler.jdkLogHandler

}

class RepositoryInterpreter[F[_] : Bracket[*[_], Throwable]](override val xa: Transactor[F])
  extends CommonRepositoryInterpreter[F](xa) with DataRepositoryAlgebra[F] {

  override val commonSql: CommonSQL = MessageSQL

}

object RepositoryInterpreter {
  def apply[F[_] : Bracket[*[_], Throwable]](xa: Transactor[F]): RepositoryInterpreter[F] =
    new RepositoryInterpreter(xa)
}