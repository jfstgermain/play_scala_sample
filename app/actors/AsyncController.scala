package actors

import se.scalablesolutions.akka.actor._
import se.scalablesolutions.akka.dispatch._
import play._
import play.libs._
import play.mvc._
import play.cache.Cache
import com.google.gson.Gson
import play.db.jpa._

trait AsyncController extends ScalateController {
  private val longProcessActor = Actor.actorOf[LongProcessActor]

	protected def async(longProcess: () => Any) = {
		longProcessActor ! Spawn(true, longProcess, "popo")
	}

  protected def asyncWithFuture(longProcess: () => Any): Future[Any] = {
    return longProcessActor !!! Spawn(false, longProcess, "pipo")
  }

	def renderAsyncResponse(procId: String) = {
		Logger.debug("In renderAsyncResponse ****************")
		Cache.get[Any](procId) match {
			case s: Option[String] => Json(s)
			case e: Exception => Error(e.getMessage())
			case _ => NoContent
		}

		Cache.delete(procId)
	}

	// longProcessResponse can also be an exception message
    private def storeInCache(processOutput: Any, procId: String) = {
        Cache.set(procId, processOutput, "4mn")
    }   
}

