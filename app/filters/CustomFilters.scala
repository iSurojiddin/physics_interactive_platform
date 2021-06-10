package filters

import play.api.http.HttpFilters

import javax.inject.Inject

class CustomFilters @Inject()(securityHeadersFilter: CustomSecurityHeadersFilter
                             )
  extends HttpFilters {

  val filters = Seq(securityHeadersFilter)
}
