package models

import java.util.{Date,TreeSet,Set=>JSet,List=>JList,ArrayList}
 
import play.db.jpa._
import play.data.Validators._

@Entity
class Dare(
    //fields
	@Required
	var title: String,

	@Required
	var description: String,
	
	@ManyToOne
	var user: User,
	
	var postedAt: Date = new Date()
) extends Model {

}

object Dare extends QueryOn[Dare]

