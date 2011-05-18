package actors

import se.scalablesolutions.akka.actor._
import play._
import play.libs._
import play.mvc._
import play.cache.Cache
import com.google.gson.Gson
import play.db.jpa._

trait LongProcessActorSupport extends ScalateController {
	protected def async(longProcess: => Any) {
		Actor.spawn {
			var processOutput: Any = ""

    	    try {
                JPAPlugin.startTx(false)
        	    processOutput = new Gson().toJson(longProcess)
                JPAPlugin.closeTx(false)            
        	}
        	catch {
           		case e => {
                    processOutput = e 
                    JPAPlugin.closeTx(true) 
                }
        	}

	        storeInCache(processOutput, "procId")
		}
	}

	def renderAsyncResponse(procId: String) {
		Logger.debug("In renderAsyncResponse ****************")
		Cache.get[Any](procId) match {
			case s: Option[String] => Json(s)
			case e: Exception => Error(e.getMessage())
			case _ => NoContent
		}

		Cache.delete(procId)
	}

	// longProcessResponse can also be an exception message
    private def storeInCache(processOutput: Any, procId: String) {
        Cache.set(procId, processOutput, "4mn")
    }   
}

