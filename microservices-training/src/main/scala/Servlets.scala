package bbc.cps.microservicestraining

import bbc.cps.microservicestraining.api.{BaseApi, IndexApi}

object Servlets {
  def apply(): Map[BaseApi, String] = {
    Map(
      new IndexApi -> "/*"
    )
  }
}
