package protocols

import com.typesafe.config.Config
import play.api.Configuration
import play.api.libs.functional.syntax._
import play.api.libs.json.{Format, __}
import play.api.libs.mailer.{Email, SMTPConfiguration}

object AppProtocol {
  val DefaultPageSize = 30

  def smtpConfig(configuration: Configuration, path: String): SMTPConfiguration = {
    SMTPConfiguration(configuration.get[Configuration](path).underlying)
  }

  def smtpConfig(config: Config, path: String): SMTPConfiguration = {
    SMTPConfiguration(config.getConfig(path))
  }

}
