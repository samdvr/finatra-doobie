package com.sam.todos.repositories

import doobie._
import doobie.implicits._
import cats.effect.IO
import com.sam.todos.data.Todo

object TodoRepository {
  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", "jdbc:postgresql:todos", "postgres", ""
  )

  def find(id: Int): IO[Option[Todo]] =
    sql"select * from todo where id = $id".query[Todo].option.transact(xa)

  def create(name: String) = {
    val status = "pending"
    sql"insert into todo (name, status) values (${name}, ${status})".update.run.transact(xa)
  }


}
