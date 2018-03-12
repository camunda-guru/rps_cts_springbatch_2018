package com.cts.hc.processors;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.cts.hc.model.Trade;

import org.springframework.batch.item.validator.Validator;
@Configuration
public class TradeValidator implements Validator<Trade>{
	 @Autowired
	  private javax.validation.Validator validator;
	
	private void generateValidationException(Set<ConstraintViolation<Object>>   constraintViolations) {
        StringBuilder message = new StringBuilder();

        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            message.append(constraintViolation.getMessage() + "\n");
        }

        throw new ValidationException(message.toString());
}
	@Override
	public void validate(Trade value) throws ValidationException {
		// TODO Auto-generated method stub
		Set<ConstraintViolation<Object>> constraintViolations =  validator.validate(value);

        if(constraintViolations.size() > 0) {
            generateValidationException(constraintViolations);
        }
	}
}
