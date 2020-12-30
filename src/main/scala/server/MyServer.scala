package server

import akka.actor.ActorSystem
import akka.http.javadsl.model.HttpEntities
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Post
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{Directives, Route}
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.model._

import scala.concurrent.ExecutionContext

case class Person(name: String, favoriteNumber: Int)


object MyServer extends  App{

  val host1 = "0.0.0.0";
  val port1 = 8080;

  val host2= "0.0.0.0";
  val port2= 8080;

  implicit val system: ActorSystem = ActorSystem("helloworld")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  def route = path("hello") {  Directives.get {    complete("Get Request")  }}

//  def route1 = path("") {  Directives.post {    complete("My First POST Request")  }}

   println("SERVER IS RUNNING");

  def route1 = path("hello") {
    post {
      entity(as[String]) { str =>
        println(str)
        complete("Post Request")
      }
    }
  }
  val httpExt = akka.http.scaladsl.Http()

  httpExt.bindAndHandle(route1,host1, port1)
  httpExt.bindAndHandle(route,host2, port2)

  //Http().bindAndHandle(route1,host,port);
  //Http().bindAndHandle(route, host, port)


}
