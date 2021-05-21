class ValidateDTO {
    String nameTestCase;
    Boolean valid;
    String validateType;
    String messageCode;
    Object fieldValue;

    public String getNameTestCase() {
        return nameTestCase;
    }

    public void setNameTestCase(String nameTestCase) {
        this.nameTestCase = nameTestCase;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getValidateType() {
        return validateType;
    }

    public void setValidateType(String validateType) {
        this.validateType = validateType;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
