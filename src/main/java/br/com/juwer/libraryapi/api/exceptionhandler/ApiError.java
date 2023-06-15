package br.com.juwer.libraryapi.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ApiError {

    private List<String> errors;

    public ApiError(BindingResult bindingResult) {
        this.setErrors(bindingResult);
    }

    public void setErrors(BindingResult result) {
        this.errors = result.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

}
