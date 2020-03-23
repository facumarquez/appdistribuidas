package com.turnos.app.VALIDACIONES;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = LegajoUnicoValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface LegajoUnico {
	public String message() default "Legajo: Ya existe ese legajo";	
	public Class<?>[] groups() default {};	
	public Class<? extends Payload>[] payload() default{};
}