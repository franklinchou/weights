package models

import java.time.LocalDate
import java.util.UUID

import models.ExerciseModelSpec.{benchPress, squat}
import org.scalatest.FunSpec
import play.api.libs.json.Json
import values._
import values.wrappers.{DateValueWrapper, IntValueWrapper, StringValueWrapper}


class GoalModelSpec extends FunSpec {

  val today: LocalDate = LocalDate.now()

  val targetDate: LocalDate = today.plusDays(5)

  val exercises: Seq[ExerciseModel] = Seq(benchPress, squat)

  val goalMockUUID: String = UUID.randomUUID().toString

  val sessionMock =
    SessionModel(
      sessionNumber = IntValueWrapper[SessionNumberValue](1),
      date = DateValueWrapper[SessionDateValue](today),
      exercises = exercises
    )

  val goalMock =
    GoalModel(
      id = StringValueWrapper[UUIDValue](goalMockUUID),
      title = StringValueWrapper[GoalTitle]("A mock goal model"),
      targetDate = DateValueWrapper[TargetDate](targetDate),
      sessions = Seq(sessionMock)
    )

  describe("A goal model") {
    it("should have a UUID") {
      assert(goalMock.id == StringValueWrapper[UUIDValue](goalMockUUID))
    }
    it("should be json serializable") {
      val json = Json.toJson(goalMock)
      val expectedJson =
        s"""
           |{
           |  "id" : "$goalMockUUID",
           |  "title" : "A mock goal model",
           |  "targetDate" : "$targetDate",
           |  "sessions" : [ {
           |    "sessionNumber" : 1,
           |    "date" : "$today",
           |    "exercises" : [ {
           |      "lift" : "Bench Press",
           |      "weight" : 205,
           |      "weightUnit" : "LBS",
           |      "repCount" : 3,
           |      "setCount" : 8
           |    }, {
           |      "lift" : "Squat",
           |      "weight" : 235,
           |      "weightUnit" : "LBS",
           |      "repCount" : 3,
           |      "setCount" : 5
           |    } ]
           |  } ]
           |}
         """.stripMargin

      assert(json == Json.parse(expectedJson))
    }
  }

}