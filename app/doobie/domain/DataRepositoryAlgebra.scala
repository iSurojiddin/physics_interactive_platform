package doobie.domain

import protocols.AppProtocol._


trait DataRepositoryAlgebra[F[_]] {

}

trait ChatRepositoryAlgebra[F[_]] extends DataRepositoryAlgebra[F]
