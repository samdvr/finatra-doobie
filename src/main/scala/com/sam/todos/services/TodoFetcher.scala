package com.sam.todos.services

import com.sam.todos.repositories.TodoRepository

object TodoFetcher {

  def fetch(id: Int) = {
    TodoRepository.find(id).unsafeRunSync
  }
}
