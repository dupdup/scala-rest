import javax.ws.rs._
import javax.ws.rs.ext._
import com.googlecode.objectify.helper._
import com.googlecode.objectify._
import java.util._
import java.lang.{Long => JLong}
import javax.persistence.Id
import com.googlecode.objectify.annotation.Cached
import scala.reflect.BeanProperty

package net.activedatatech.vendor.data {

	@BeanProperty
	trait Contact {
	
	  @BeanProperty var workPhone: String = _
	  @BeanProperty var mobilePhone: String = _
	  @BeanProperty var fax: String = _
	  @BeanProperty var email: String = _
	  @BeanProperty var address: String = _
	  @BeanProperty var name = "someone"
	
	}

	@BeanProperty
	class Vendor extends AnyRef with Contact {
	
		@Id @BeanProperty var id: String = _
	
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
	  def getJson = { new Vendor }
	
	  @GET
	  @Path("/hello.xml")
	  @Produces(Array("text/xml"))
	  def getXml = { new Vendor }
	}
	

}




