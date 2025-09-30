package kr.co.lunasoft.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.lunasoft.vo.ResponseInfo;

@FeignClient(name = "account-service")
public interface AccountClient {

	@GetMapping("/account/{path}")
	ResponseInfo callAccount(@PathVariable("path") String path);

}
