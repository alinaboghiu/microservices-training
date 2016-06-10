package bbc.cps.microservicestrainingbroker.api

import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._

class IndexApi extends BaseApi {

  implicit def json4sFormats = DefaultFormats

  var reqList: Map[Double, String] = Map.empty
  var backup: Map[Double, String] = Map.empty

  post("/request") {
    val mapEntry = ((parse(request.body) \ "eventId").extract[Double] -> request.body)
    reqList = reqList + mapEntry
    backup = backup + mapEntry
  }

  put("/restoreFromBackup") {
    reqList = backup
  }

  get("/requests") {
    val reqListAsString = reqList.values.toList.toString

    if (reqListAsString.nonEmpty) {
      """{"results": [""" + reqListAsString.substring(5, reqListAsString.length-1) + "]}"
    } else {
      """{ "results": [] }"""
    }
  }

  delete("/request/:id") {
    reqList = reqList - params("id").toDouble
  }

}
