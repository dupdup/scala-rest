import javax.ws.rs._
import javax.ws.rs.ext._
import com.googlecode.objectify.helper._
import com.googlecode.objectify._
import java.util._
import java.lang.{Long => JLong}
import javax.persistence.Id
import com.googlecode.objectify.annotation.Cached


package net.activedatatech.vendor.data {

	trait Contact {
	
	  var workPhone: String = _
	  var mobilePhone: String = _
	  var fax: String = _
	  var email: String = _
	  var address: String = _
	
	}

	class Vendor extends AnyRef with Contact {
	
		@Id var id: String = _
	
	}

	object Dao extends DAOBase {
		def register = {
			ObjectifyService.register(classOf[Vendor]);
		}
	}

}

package net.activedatatech.vendor.rest {

	import net.activedatatech.vendor.data._

	@Provider
	@Path("/vendor")
	class VendorService {
	
	  @GET
	  @Path("/hello.json")
	  @Produces(Array("application/json"))
	  def getMediaType = { new Vendor }
	
	  @GET
	  @Path("/hello.xml")
	  @Produces(Array("text/xml"))
	  def getHTML = "hello"
	}
	

}




