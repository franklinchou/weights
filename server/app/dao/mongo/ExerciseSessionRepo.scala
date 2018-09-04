package dao.mongo

import com.google.inject.Inject
import lib.containers.StringContainer
import models._
import play.api.libs.json.{JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.play.json._

import scala.concurrent.{ExecutionContext, Future}

class ExerciseSessionRepo @Inject()(val rma: ReactiveMongoApi)
                                   (implicit ec: ExecutionContext) extends MongoRepo[ExerciseSessionModel] {


  /**
    * Name of the collection where records are stored
    */
  val collectionName: String = "exercise"

  /**
    * Find a list of [[ExerciseSessionModel]]s given a user id
    *
    * @param user
    * @return
    */
  def find(user: StringContainer[AbstractUserId]): Future[List[ExerciseSessionModel]] = {

    val query: JsObject = Json.obj("user" -> user.value)

    collection
      .map(_.find(query).cursor[ExerciseSessionModel](ReadPreference.primary))
      .flatMap(_.collect[List](-1, Cursor.FailOnError[List[ExerciseSessionModel]]()))
  }

  /**
    * Insert a record into the database.
    *
    * @param record
    * @return
    */
  def create(record: AbstractModel): Future[Boolean] = ???

  /**
    * Upsert a record into the database.
    *
    * @param record
    * @return
    */
  def upsert(record: AbstractModel): Future[Boolean] = ???

  /**
    * Remove a record from the database.
    *
    * @param id
    * @return
    */
  def delete(id: StringContainer[AbstractModelId]): Future[Boolean] = ???

}