package bbc.cps.microservicestrainingquery.api

import dispatch.{Http, host}
import org.json4s.native.JsonMethods._
import org.json4s.{DefaultFormats, JValue}

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global

class IndexApi extends BaseApi {

  implicit def json4sFormats = DefaultFormats

  var users: Map[String, String] = Map.empty

  get("/user/:id") {
    val name = params("id")
    users.get(name)
  }

  get("/users") {
    users
  }

  get("/triggerPolling") {
    poll()
  }

  @tailrec
  private def poll(): Any = {
    Http(host("localhost:8086") / "requests") map { result =>
      val requestList = (parse(result.getResponseBody) \ "results").extract[List[JValue]]

      requestList foreach { req =>
        val eventType = (req \ "eventType").extract[String]
        val eventId = (req \ "eventId").extract[String]
        val name = (req \ "eventPayload").extract[String]

        eventType match {
          case "created" =>
            users = users + (name -> name)
            Http((host("localhost:8086") / "request" / s"$eventId").setMethod("DELETE"))
          case _ => new RuntimeException("Invalid request method!!!")
        }
      }
    }

    Thread.sleep(1000)
    poll()
  }
}
