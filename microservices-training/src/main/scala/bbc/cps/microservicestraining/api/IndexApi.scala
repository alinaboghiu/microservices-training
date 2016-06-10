package bbc.cps.microservicestraining.api

import dispatch.{Http, host}
import org.json4s.native.JsonMethods._
import org.json4s.{DefaultFormats, JValue}

import scala.annotation.tailrec
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class IndexApi extends BaseApi {

  implicit def json4sFormats = DefaultFormats

  var users: Seq[String] = Seq.empty
  var events: Seq[Event] = Seq.empty

  get("/events") {
    events
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
        val eventPayload = (req \ "eventPayload").extract[String]

        eventType match {
          case "submitted" =>
            users = users :+ eventPayload
            println(s">>>>>>>>>>>> reqPayload >>> $eventPayload")
            val reqBody = s"""{"eventId": ${math.random}, "eventTime": ${System.currentTimeMillis}, "eventType": "created", "eventPayload": "$eventPayload"}"""
            println(s">>>>>>>>>>>> reqBody >>> $reqBody")
            val notifyBroker = (host("localhost:8086") / s"request").setMethod("POST").setBody(reqBody)

            Http(notifyBroker) map ( brokerResponse => brokerResponse.getStatusCode match {
              case 200 =>
                Http((host("localhost:8086") / "request" / s"$eventId").setMethod("DELETE")) map { brokerDeleteResponse =>
                  brokerDeleteResponse.getStatusCode match {
                    case 200 => println("userDeleted")
                    case otherStatusCode => println(s"user not Deleted: $otherStatusCode")
                  }
                }
              case _ => new RuntimeException("Invalid response from broker!!!")
            })

          case _ => new RuntimeException("Invalid request method!!!")
        }
      }
    }

    Thread.sleep(1000)
    poll()
  }

}

