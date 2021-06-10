package doobie.repository

import cats.effect.Bracket
import doobie._
import doobie.domain.DataRepositoryAlgebra
import protocols.AppProtocol._

trait CommonSQL {

}

abstract class CommonRepositoryInterpreter[F[_]: Bracket[*[_], Throwable]](val xa: Transactor[F])
  extends DataRepositoryAlgebra[F] {

  val commonSql: CommonSQL

}