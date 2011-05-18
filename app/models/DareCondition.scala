package models

import java.util.{Date,TreeSet,Set=>JSet,List=>JList,ArrayList}
 
import play.db.jpa._
import play.data.Validators._


@Entity
class DareCondition(
    //fields
	@Required
	var title: String,

	@Required
	var description: String,

    @Required
    var priority: Int,
    
    @ManyToOne
	var dare: Dare,
	
	var postedAt:Date = new Date()
) extends Model {

}
