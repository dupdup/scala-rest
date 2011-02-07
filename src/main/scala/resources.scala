import javax.ws.rs._
import javax.ws.rs.ext._
import com.googlecode.objectify.helper._
import com.googlecode.objectify._
import java.util._
import java.lang.{Long => JLong}
import javax.persistence.Id
import com.googlecode.objectify.annotation.Cached
import java.lang.reflect._
import java.io.OutputStream
import java.lang.annotation.Annotation
import java.lang.{String, Class}
import javax.ws.rs.core.{MultivaluedMap, MediaType}
import javax.ws.rs.ext.{MessageBodyWriter, Provider}
import java.lang.reflect.Type
import com.sun.jersey.core.provider._
import scala.xml.Node
import scala.xml.PrettyPrinter
import java.io._
import scala.reflect.BeanProperty


package net.activedatatech.vendor.data {

	trait Contact {
	
	  @BeanProperty var workPhone: String = _
	  @BeanProperty var mobilePhone: String = _
	  @BeanProperty var fax: String = _
	  @BeanProperty var email: String = _
	  @BeanProperty var address: String = _
	
	}

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
	@Path("/vendors")
	class VendorService {
	
	  @GET
	  @Path("/blank.json")
	  @Produces(scala.Array("application/json"))
	  def blankJSON: Vendor = { new Vendor }
	
	  @GET
	  @Path("/blank.xml")
	  @Produces(scala.Array("text/json"))
	  def blankXML: Vendor = { new Vendor }
          
	}

}

package net.activedatatech.vendor.rest.providers {

import net.activedatatech.vendor.data._
import scala.reflect.Manifest
import java.io.{InputStream, OutputStream}
import java.lang.annotation.Annotation
import java.lang.reflect.Type
import javax.ws.rs.{WebApplicationException, Produces, Consumes}
import javax.ws.rs.core.Response.Status
import javax.ws.rs.core.{Response, MultivaluedMap, MediaType}
import com.sun.jersey.core.provider.AbstractMessageReaderWriterProvider
import javax.ws.rs.ext.Provider
import org.slf4j.LoggerFactory

import org.codehaus.jackson.map.{MappingJsonFactory, ObjectMapper}
import org.codehaus.jackson.{JsonGenerator, JsonParser => JacksonParser}

@Provider
@Produces(scala.Array(MediaType.APPLICATION_JSON))
@Consumes(scala.Array(MediaType.APPLICATION_JSON))
class JsonCaseClassProvider extends AbstractMessageReaderWriterProvider[Vendor] {
  private val logger = LoggerFactory.getLogger(classOf[JsonCaseClassProvider])

  def writeTo(json: Vendor, t: Class[_], genericType: Type, annotations: scala.Array[Annotation],
              mediaType: MediaType, httpHeaders: MultivaluedMap[String, AnyRef],
              entityStream: OutputStream) {
    try {
	"Some stuff"
    } catch {
      case e => logger.error("Error encoding %s as JSON".format(json), e)
    }
  }

  def isWriteable(t: Class[_], genericType: Type, annotations: scala.Array[Annotation],
                  mediaType: MediaType) =
    mediaType == MediaType.APPLICATION_JSON_TYPE &&
            classOf[Vendor].isAssignableFrom(t)

  def readFrom(t: Class[Vendor], genericType: Type,
               annotations: scala.Array[Annotation], mediaType: MediaType,
               httpHeaders: MultivaluedMap[String, String],
               entityStream: InputStream): Vendor = {
    try {
      // Json.parse(entityStream)(Manifest.classType(t))
	null
    } catch {
      case e: Exception => {
        throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
                                          .entity(e.getMessage)
                                          .build)
      }
      case e => {
        logger.error("Error decoding JSON request entity", e)
        throw e
      }
    }
  }

  def isReadable(t: Class[_], genericType: Type, annotations: scala.Array[Annotation],
                 mediaType: MediaType): Boolean =
    mediaType == MediaType.APPLICATION_JSON_TYPE &&
            classOf[Vendor].isAssignableFrom(t)
}

}


