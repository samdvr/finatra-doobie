package com.sam.todos.api

import com.sam.todos.services.{TodoCreator, TodoFetcher}
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.{FormParam, RouteParam}

case class TodoGetRequest(@RouteParam id: String)
case class TodoRequest(@FormParam name: String)

case class Error(error: String)


class TodoController extends Controller {

  get("/todos/:id") { request: TodoGetRequest =>
    val result = TodoFetcher.fetch(request.id.toInt)
    result match {
      case Some(value) => value
      case None => response.notFound(Error("Not Found"))
    }
  }

  post("/todos/") { request: TodoRequest =>
    val result = TodoCreator.create(request.name)
    result match {
      case 1 => response.created.body("{}")
      case _ => response.badGateway.body(Error("Something went wrong"))
    }
  }
}