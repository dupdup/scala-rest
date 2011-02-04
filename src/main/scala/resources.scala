package net.activedatatech.vendor.rest

import javax.ws.rs._
import javax.ws.rs.ext._

@Provider
@Path("/vendor")
class VendorService {

  @GET
  @Path("/hello.json")
  @Produces(Array("application/json"))
  def getMediaType = "hello"

  @GET
  @Path("/hello.xml")
  @Produces(Array("text/json"))
  def getHTML = "hello"
}


