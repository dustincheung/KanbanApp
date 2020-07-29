/*
 *  Service class that handles mapping errors (sql col errors that occur from failing conditions
 *  annotated in our jpa model entity classes)
 */

package kanbanapp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ErrorMappingService {
	
	// Method iterates through all FieldError objs and adds mapping of field w/ associate err to mapErrors
	public ResponseEntity<?> mapErrors(BindingResult result){
		if(result.hasErrors()) {
			
			Map<String, String> mapErrors = new HashMap<>();
			
			for(FieldError err: result.getFieldErrors()) {
				mapErrors.put(err.getField(), err.getDefaultMessage());
			}
			
			return new ResponseEntity<>(mapErrors, HttpStatus.BAD_REQUEST);
		}
		
		return null;
	}
}
