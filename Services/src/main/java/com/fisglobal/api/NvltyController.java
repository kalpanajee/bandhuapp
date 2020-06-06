package com.fisglobal.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fisglobal.requestbo.CalWrkFrcSvcReq;
import com.fisglobal.requestbo.ContactTrckSvcReq;
import com.fisglobal.requestbo.ErrDtls;
import com.fisglobal.requestbo.RegSvcReq;
import com.fisglobal.requestbo.SeatAllocSvcReq;
import com.fisglobal.requestbo.TrckSvcReq;
import com.fisglobal.responsebo.CalWrkFrcResp;
import com.fisglobal.responsebo.ContactTrckSvcResp;
import com.fisglobal.responsebo.RegSvcResp;
import com.fisglobal.responsebo.SeatAllocSvcResp;
import com.fisglobal.responsebo.TrckSvcResp;
import com.fisglobal.svc.CalWrkFrcSvc;
import com.fisglobal.svc.ContactTrckSvc;
import com.fisglobal.svc.RegSvc;
import com.fisglobal.svc.SeatAllocSvc;
import com.fisglobal.svc.TrckSvc;
import com.fisglobal.util.CommanUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@CrossOrigin
@RestController
@RequestMapping("/v1.0.0/Nvlty/NvltyService")
public class NvltyController {

	@Autowired
	public TrckSvc trckSvc;
	
	@Autowired
	public RegSvc regSvc;
	
	@Autowired
	public SeatAllocSvc seatAllocSvc;
	
	@Autowired
	public ContactTrckSvc contactTrckSvc;

	@Autowired
	public CalWrkFrcSvc calWrkFrcSvc;

	@Autowired
	private CommanUtils cmnUtls;

	
	@ApiOperation(value = "seatAllocSvc", nickname = "seatAllocSvc")
	@RequestMapping(value = "/seatAllocSvc", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = SeatAllocSvcResp.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Failure") })

	public @ResponseBody ResponseEntity<SeatAllocSvcResp> seatAllocSvc(@Valid @RequestBody SeatAllocSvcReq req,BindingResult bindingResults) throws Exception {
		List<ErrDtls> msgInfoLst = null;
		SeatAllocSvcResp seatAllocSvcResp=new SeatAllocSvcResp();
		if (bindingResults.hasErrors()) {
			msgInfoLst = bindingResults.getAllErrors().stream().map(x -> cmnUtls.addErrorMessage(x.getDefaultMessage()))
					.collect(Collectors.toList());
			 if(msgInfoLst.size() > 0){
				 seatAllocSvcResp.setRspCde("999");
				 seatAllocSvcResp.setRspMsg("Failure");
             }
//			seatAllocSvcResp.setErrDtls(msgInfoLst);
		}else{
			seatAllocSvcResp = seatAllocSvc.getSeatDtls(req);
		}
		return new ResponseEntity<SeatAllocSvcResp>(seatAllocSvcResp,HttpStatus.OK);
	}
	
	@ApiOperation(value = "calWrkFrcSvc", nickname = "CalWrkFrcSvc")
	@RequestMapping(value = "/calWrkFrcSvc", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CalWrkFrcResp.class),
							@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
							@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 400, message = "Bad Request"),
							@ApiResponse(code = 500, message = "Failure") })
	
	public @ResponseBody ResponseEntity<CalWrkFrcResp> calWrkFrcSvc(@Valid @RequestBody CalWrkFrcSvcReq req,BindingResult bindingResults) throws Exception {
		String usrtyp = "A";
		CalWrkFrcResp response = calWrkFrcSvc.getWrkFrcPercent(usrtyp,req.getFlrNbr());
		return new ResponseEntity<CalWrkFrcResp>(response,HttpStatus.OK);
	}
	
	@ApiOperation(value = "contactTrckSvc", nickname = "contactTrckSvc")
	@RequestMapping(value = "/contactTrckSvc", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = SeatAllocSvcResp.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Failure") })

	public @ResponseBody ResponseEntity<ContactTrckSvcResp> contactTrckSvc(@Valid @RequestBody ContactTrckSvcReq req,BindingResult bindingResults) throws Exception {
		List<ErrDtls> msgInfoLst = null;
		ContactTrckSvcResp contactTrckSvcResp =new ContactTrckSvcResp();
		if (bindingResults.hasErrors()) {
			msgInfoLst = bindingResults.getAllErrors().stream().map(x -> cmnUtls.addErrorMessage(x.getDefaultMessage()))
					.collect(Collectors.toList());
			if(msgInfoLst.size() > 0){
				contactTrckSvcResp.setRspCde("999");
				 contactTrckSvcResp.setRspMsg("Failure");
            }
//			contactTrckSvcResp.setErrDtls(msgInfoLst);
		}else{
			contactTrckSvcResp = contactTrckSvc.getContactTrckDtls(req);
		}
		return new ResponseEntity<ContactTrckSvcResp>(contactTrckSvcResp,HttpStatus.OK);
	}
    
 @ApiOperation(value = "regGetRegEidSvc", nickname = "regGetRegEidSvc")
 @RequestMapping(value = "/regGetRegEidSvc", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = RegSvcResp.class),
         @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
         @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 400, message = "Bad Request"),
         @ApiResponse(code = 500, message = "Failure") })
 public @ResponseBody ResponseEntity<RegSvcResp> regEidDtls(@Valid @RequestBody String eid,BindingResult bindingResults) throws Exception {
     List<ErrDtls> msgInfoLst = null;
     RegSvcResp regSvcResp=new RegSvcResp();
     if (bindingResults.hasErrors()) {
         msgInfoLst = bindingResults.getAllErrors().stream().map(x -> cmnUtls.addErrorMessage(x.getDefaultMessage()))
                 .collect(Collectors.toList());
         
         if(msgInfoLst.size() > 0){
             regSvcResp.setRspCde("999");
             regSvcResp.setRspMsg("Failure");
         }
     }else{
         regSvcResp = regSvc.regEidDtls(eid);
     }
     return new ResponseEntity<RegSvcResp>(regSvcResp,HttpStatus.OK);
 }
  
 
    @ApiOperation(value = "contactTrckEidSvc", nickname = "contactTrckEidSvc")
    @RequestMapping(value = "/contactTrckEidSvc", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = SeatAllocSvcResp.class),
                  @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
                  @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 400, message = "Bad Request"),
                  @ApiResponse(code = 500, message = "Failure") })

    public @ResponseBody ResponseEntity<ContactTrckSvcResp> getContactTrckEidDtls(@Valid @RequestBody String eid,BindingResult bindingResults) throws Exception {
           List<ErrDtls> msgInfoLst = null;
           ContactTrckSvcResp contactTrckSvcResp =new ContactTrckSvcResp();
           if (bindingResults.hasErrors()) {
                  msgInfoLst = bindingResults.getAllErrors().stream().map(x -> cmnUtls.addErrorMessage(x.getDefaultMessage()))
                               .collect(Collectors.toList());
                  if(msgInfoLst.size() > 0){
                	  contactTrckSvcResp.setRspCde("999");
                	  contactTrckSvcResp.setRspMsg("Failure");
                  }

//               contactTrckSvcResp.setErrDtls(msgInfoLst);
           }else{
                  contactTrckSvcResp = contactTrckSvc.getContactTrckEidDtls(eid);
           }
           return new ResponseEntity<ContactTrckSvcResp>(contactTrckSvcResp,HttpStatus.OK);
    }
    
    @ApiOperation(value = "contactTrckInsertSvc", nickname = "contactTrckInsertSvc")
    @RequestMapping(value = "/contactTrckInsertSvc", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = SeatAllocSvcResp.class),
                  @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
                  @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 400, message = "Bad Request"),
                  @ApiResponse(code = 500, message = "Failure") })

    public @ResponseBody ResponseEntity<ContactTrckSvcResp> insertContactTrckDtls(@Valid @RequestBody String eid,String flrId, String band,String nr_eid,BindingResult bindingResults) throws Exception {
           List<ErrDtls> msgInfoLst = null;
           ContactTrckSvcResp contactTrckSvcResp =new ContactTrckSvcResp();
           if (bindingResults.hasErrors()) {
                  msgInfoLst = bindingResults.getAllErrors().stream().map(x -> cmnUtls.addErrorMessage(x.getDefaultMessage()))
                               .collect(Collectors.toList());
                  if(msgInfoLst.size() > 0){
                	  contactTrckSvcResp.setRspCde("999");
                	  contactTrckSvcResp.setRspMsg("Failure");
                  }
//               contactTrckSvcResp.setErrDtls(msgInfoLst);
           }else{
                  contactTrckSvcResp = contactTrckSvc.insertContactTrckDtls(eid,flrId, band,nr_eid);
           }
           return new ResponseEntity<ContactTrckSvcResp>(contactTrckSvcResp,HttpStatus.OK);
    }

	
}
