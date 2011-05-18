package controllers

import play._
import play.libs._
import play.mvc._
import com.restfb._
import com.restfb.types.{User => FBUser}
import models._
import actors._

object Users extends ScalateController with AsyncController {
//object Users extends ScalateController {
    def index {
//		var con: FBUser

		async {
			val user = User.find("fbClientId","2227470867").fetch().head
		}
/*		async {
			val facebookClient = new DefaultFacebookClient(User.find("fbClientId","2227470867").fetch().head.fbAccessToken)

	        val user: FBUser = facebookClient.fetchObject("me", classOf[FBUser])
    	    val con  = facebookClient.fetchConnection("me/friends", classOf[FBUser]).getData().asScala
			Logger.debug("" + User.count)
			""
        	//val a = count.toString
		}*/
        render(user)
    }

	def create(code: String, access_token: String) {

        Logger.debug("TESTESTEST********************" + params.toString())
        Logger.debug(code)
        Logger.debug(access_token)
        if (access_token == "") {
            Logger.debug(WS.url("https://graph.facebook.com/oauth/access_token?client_id=172882142739775&redirect_uri=" + WS.encode("http://creatix.kicks-ass.net:9000/users/create") + "&client_secret=9da2e3396c8f1fb551225b4b566bb270&code=" + WS.encode(code)).get().getString().toString())
            // extract token
            val fbUser = WS.url("https://graph.facebook.com/me?access_token=" + WS.encode(access_token)).get().getJson()
        }
       /*

Logger.debug("" + User.count)
https://graph.facebook.com/oauth/authorize?client_id=172882142739775&redirect_uri=http://www.example.com/callback&scope=user_photos,user_videos,publish_stream
*/

		// check that the user doesn't already exist
		
	}
}

