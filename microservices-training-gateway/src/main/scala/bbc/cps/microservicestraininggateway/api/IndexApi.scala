package bbc.cps.microservicestraininggateway.api

import dispatch.{Http, Req, host}
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class IndexApi extends BaseApi {
  get("/people") {
    val requestPeople = host("localhost:8089") / "users"
    Await.result(Http(requestPeople), 10.seconds).getResponseBody()
  }

  get("/person/:personName") {
    val requestPerson = host("localhost:8089") / ("user/" + params("personName"))
    Await.result(Http(requestPerson), 10.seconds).getResponseBody()
  }

  post("/person/:personName") {
    val reqBody = s"""{"eventId": ${math.random}, "eventTime": ${System.currentTimeMillis}, "eventType": "submitted", "eventPayload": "${params("personName")}"}"""
    val requestPeople = (host("localhost:8086") / s"request").setMethod("POST").setBody(reqBody)

    Await.result(Http(requestPeople), 10.seconds).getResponseBody()
  }

}
