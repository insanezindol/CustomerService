package kr.co.lunasoft.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lunasoft.feign.AccountClient;
import kr.co.lunasoft.vo.ResponseInfo;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	AccountClient accountClient;

	@GetMapping(value = "/get-map-data")
	public Map<String, Object> getMapData() {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", "this is customer service. 12345");
		return obj;
	}

	@GetMapping(value = "/test")
	public Map<String, Object> test() {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", "this is customer service. 67890");
		return obj;
	}

	@GetMapping(value = "/long-time")
	public Map<String, Object> longTime() {
		int rand = new Random().nextInt(10) + 10;
		try {
			Thread.sleep(rand * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", "this is customer service. 12345");
		return obj;
	}
	
	@GetMapping(value = "/call-account")
	public Map<String, Object> callCustomer() {
		ResponseInfo output = accountClient.callAccount("test");
		
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", output);
		return obj;
	}

}
