package bbc.cps.microservicestrainingquery

import bbc.cps.microservicestrainingquery.api.{BaseApi, IndexApi}

object Servlets {
  def apply(): Map[BaseApi, String] = {
    Map(
      new IndexApi -> "/*"
    )
  }
}
