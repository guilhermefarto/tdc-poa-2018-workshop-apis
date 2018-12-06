package br.com.tdc.workshopapis.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.tdc.workshopapis.annotations.ApiTDC;

@ApiTDC
@Controller
@RequestMapping("/api/hello")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String hello() {
		return "Hello, TDC";
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getHelloPath(@PathVariable("name") String name) {
		return "Hello, " + name + "!";
	}

	@RequestMapping(value = "/withParam", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getHelloParam(@RequestParam("name") String name) {
		return "Hello, " + name + "!";
	}

	@RequestMapping(value = "/withHeader", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getHelloHeader(@RequestHeader("name") String name) {
		return "Hello, " + name + "!";
	}

}
