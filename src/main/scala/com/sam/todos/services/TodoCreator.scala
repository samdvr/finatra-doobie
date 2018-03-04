package com.sam.todos.services

import com.sam.todos.repositories.TodoRepository

object TodoCreator {
  def create(name: String) = {
    TodoRepository.create(name).unsafeRunSync
  }
}
