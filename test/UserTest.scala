import org.junit._
import play.test._
import models._
import scala.collection.JavaConversions._
import org.scalatest.{FlatSpec,BeforeAndAfterEach}
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.Stack

class UserTest extends UnitTest with FlatSpec with ShouldMatchers{
  it should "create and retrieve a user" in {
    // Create a new user and save it
    new User("toto@gmail.com", "passwd", "Toto St-Germain", "fbClientId1", "fbAccessToken1").save()

    // Retrieve the user with bob username
    User.connect("toto@gmail.com", "passwd") should not be (None)
    User.connect("toto@gmail.com", "badpassword") should be (None)
    User.connect("tom@gmail.com", "passwd") should be (None)
  }
}
