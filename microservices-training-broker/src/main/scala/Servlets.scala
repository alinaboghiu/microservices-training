package bbc.cps.microservicestrainingbroker

import bbc.cps.microservicestrainingbroker.api.{BaseApi, IndexApi}

object Servlets {
  def apply(): Map[BaseApi, String] = {
    Map(
      new IndexApi -> "/*"
    )
  }
}
