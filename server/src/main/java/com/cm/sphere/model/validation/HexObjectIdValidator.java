package com.cm.sphere.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.bson.types.ObjectId;

public class HexObjectIdValidator implements ConstraintValidator<HexObjectId, String> {
    @Override
    public void initialize(HexObjectId constraintAnnotation) {}

    @Override
    public boolean isValid(String objectId, ConstraintValidatorContext context) {
        return ObjectId.isValid(objectId);
    }
}
