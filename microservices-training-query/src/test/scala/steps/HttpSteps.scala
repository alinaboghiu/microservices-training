package steps

import bbc.cps.microservicestrainingquery.Config
import dispatch.url
import org.scalatest.MustMatchers

object HttpSteps extends ApiSteps with MustMatchers {
  val host = Config.host

  When("""^I request the application status$""") { () =>
    get(url(s"$host/status"))
  }

  Then("""^a healthy status is returned$""") { () =>
    response.getStatusCode mustBe 200
  }
}
