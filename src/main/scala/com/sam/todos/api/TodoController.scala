package com.sam.todos.api

import com.sam.todos.services.{TodoCreator, TodoFetcher}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.{FormParam, QueryParam}


case class TodoRequest(@FormParam name: String)

case class Error(reason: String)

class TodoController extends Controller {

  get("/todos/:id") { request: Request =>
    val result = TodoFetcher.fetch(request.params("id").toInt)
    result match {
      case Some(value) => value
      case None => response.notFound(Error("Not Found"))
    }
  }

  post("/todos/") { request: TodoRequest =>
    TodoCreator.create(request.name)
  }
}