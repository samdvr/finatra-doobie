package com.sam.todos.api

import com.sam.todos.services.{TodoCreator, TodoFetcher}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller


case class TodoRequest(name: String)

class TodoController extends Controller {

  get("/todos/:id") { request: Request =>
    val result = TodoFetcher.fetch(request.params("id").toInt)
    result match {
      case Some(value) => value
      case None => response.status(404).body("NotFound")
    }
  }

  post("/todos/") { request: Request =>
    val name = request.params.get("name")
    name match {
      case Some(value) => TodoCreator.create(value)
      case None => response.status(403).body("name is required")
    }

  }
}