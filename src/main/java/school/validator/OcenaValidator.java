package school.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import school.dto.FinalGrade;
import school.dto.Grade;

@Component
public class OcenaValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Grade.class.equals(clazz) ;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Grade ocena = (Grade) target;

        System.out.println("Validating grade: " + ocena);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "broj", "ocena.broj.empty", "ocena.broj.empty = Ocena nije uneta.");
        
        if (errors.hasErrors()) {
            return;
        }

        if (ocena.getBroj() < 1 || ocena.getBroj() > 5) {
            errors.rejectValue("broj", "ocena.broj.empty", "ocena.broj.empty = Ocena mora biti u rasponu od 1 do 5");
        }
        
    }
}
