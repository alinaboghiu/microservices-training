package bbc.cps.microservicestraininggateway

import bbc.cps.microservicestraininggateway.api.{BaseApi, IndexApi}

object Servlets {
  def apply(): Map[BaseApi, String] = {
    Map(
      new IndexApi -> "/*"
    )
  }
}
