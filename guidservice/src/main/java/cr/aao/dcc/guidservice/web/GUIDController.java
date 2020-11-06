package cr.aao.dcc.guidservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("api/v1/guid")
@RestController
public class GUIDController {

	@GetMapping
	@ResponseBody
	public String generateGUID() {
		String guid = UUID.randomUUID().toString();
		System.out.println("GUID Generado: " + guid);
		return guid;
	}
}