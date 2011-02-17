import javax.ws.rs._
import javax.ws.rs.ext._
import javax.xml.bind.annotation._
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
	  @BeanProperty var name = "some name"
	
	}

	@BeanProperty
	@XmlRootElement
	@com.googlecode.objectify.annotation.Entity
	class Vendor extends AnyRef with Contact {
	
		@Id @BeanProperty var id: String = _
	
	}
	
	class VendorDao extends DAOBase {
	
		def getOrCreateThing(id: String) = {
			var found = ofy().find(classOf[Vendor], id)
			if ( found == null ) {
				found
			} else {
				new Vendor
			}
		}
	
	}

	object VendorDao {
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
	  @Path("/")
	  @Produces(Array("text/html"))
	  def index() = {
	  	""
	  }

	  // @GET
	  // @Path("/json/${id}")
	  // @Produces(Array("application/json"))
	  // def getJson(@PathParam("id") id: String): Vendor = { 
	  	// val o = ObjectifyService.begin();
	  	// o.get(classOf[Vendor], id)
	  	// def dao = new VendorDao()
	  	// dao.getOrCreateThing(id)
	  // }
	
	  // @GET
	  // @Path("/xml/{id}")
	  // @Produces(Array("text/xml"))
	  // def getXml(@PathParam("id") id: String) = {
	  	// val o = ObjectifyService.begin();
	  	// o.get(classOf[Vendor], id)
	  	// def dao = new VendorDao()
	  	// dao.getOrCreateThing(id)
	  // }
	  
	  @GET
	  @Path("/all.json")
	  @Produces(Array("application/json"))
	  def allJson() = { 
	  	val o = ObjectifyService.begin();
	  	o.query(classOf[Vendor]).list()
	  }
	
	  @GET
	  @Path("/all.xml")
	  @Produces(Array("text/xml"))
	  def allXml() = {
	  	val o = ObjectifyService.begin();
	  	o.query(classOf[Vendor]).list()
	  }
	  
	}
	

}




