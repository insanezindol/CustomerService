package kr.co.lunasoft.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lunasoft.feign.AccountClient;
import kr.co.lunasoft.vo.ResponseInfo;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

	private final AccountClient accountClient;

	@GetMapping(value = "/get-map-data")
	public Map<String, Object> getMapData() {
		return success("this is customer service. 12345");
	}

	@GetMapping(value = "/test")
	public Map<String, Object> test() {
		return success("this is customer service. 67890");
	}

	@GetMapping(value = "/long-time")
	public Map<String, Object> longTime() {
		int rand = ThreadLocalRandom.current().nextInt(10, 20);
		try {
			Thread.sleep(rand * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return success("this is customer service. 12345");
	}

	@GetMapping(value = "/call-account")
	public Map<String, Object> callCustomer() {
		ResponseInfo output = accountClient.callAccount("test");
		return success(output);
	}

	private Map<String, Object> success(Object data) {
		Map<String, Object> obj = new HashMap<>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", data);
		return obj;
	}

}
