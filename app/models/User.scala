package models

import java.util.{Date,TreeSet,Set=>JSet,List=>JList,ArrayList}
 
import play.db.jpa._
import play.data.Validators._


@Entity
@Table(uniqueConstraints=Array(new UniqueConstraint(columnNames=Array("fbClientId"))))
class User(
    var email: String,
    var password: String,
    var fullname: String,
    
	@Required 
	var fbClientId: String,
	
	@Required
	var fbAccessToken: String
) extends Model {
    @OneToMany
    var dares: JList[Dare] = new ArrayList[Dare]
    
    //instance methods
    override def toString() = fbClientId
}

object User extends QueryOn[User] {
    def connect(email: String, password: String) = {
        find("byEmailAndPassword", email, password).first
    }
}

