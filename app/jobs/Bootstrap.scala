import play._
import play.jobs._
import models._
 
@OnApplicationStart
class Bootstrap extends Job {
    
    override def doJob() {
		if (User.count <= 0) {
			new User(
				fbClientId = "2227470867", 
				fbAccessToken = "172882142739775|dc71249d8cc3b7ef2294eb1b-539151525|XAnJL3JkLZudByjMcswBpgXMLoo" 
			).save()
		}
    }    
}