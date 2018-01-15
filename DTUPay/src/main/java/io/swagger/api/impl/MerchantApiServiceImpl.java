package io.swagger.api.impl;

import io.swagger.api.*;


import io.swagger.api.model.Merchant;

import io.swagger.api.NotFoundException;
import mdb.utils.GsonWrapper;

import javax.ejb.Stateless;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Stateless
@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/queue/CreateMerchantMDB",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "CreateMerchantQueue"
                )}
)
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2018-01-13T13:38:28.848Z")
public class MerchantApiServiceImpl extends MerchantApiService {
    core.user.Merchant lastMerchant;

      @Override
      public Response createMerchant(Merchant body,SecurityContext securityContext) throws NotFoundException {
          core.user.Merchant merchant = new core.user.Merchant(body.getCvr(),body.getName());

          if(!body.getCvr().matches("^(?!\\s*$)[0-9\\s]{8}$"))
              return Response.status(405).entity("Invalid input").build();



          if(!merchant.equals(lastMerchant))
              lastMerchant = merchant;
          else
              return Response.status(400).entity("An error occurred with the account").build();


      return Response.status(201).entity("65980983").build();
  }
      @Override
      public Response getMerchantByCVR(String cvr,SecurityContext securityContext) throws NotFoundException {
          Merchant dtoMerchant = new Merchant();
          dtoMerchant.setName(lastMerchant.getFirstName());
          dtoMerchant.setCvr(lastMerchant.getCvr());
          if (!dtoMerchant.getCvr().equals(cvr))
              return Response.status(404).entity("Merchant not found").build();


          String lmao = GsonWrapper.toJson(lastMerchant);
          return Response.status(200).entity(GsonWrapper.toJson(lastMerchant)).build();
  }
      @Override
      public Response merchantCvrDelete(String cvr,SecurityContext securityContext) throws NotFoundException {
      // do some magic!
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
}
