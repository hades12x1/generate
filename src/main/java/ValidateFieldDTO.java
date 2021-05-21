import java.util.ArrayList;
import java.util.List;

class ValidateFieldDTO {
    String fieldName;
    List<ValidateDTO> validates = new ArrayList<>(0);

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<ValidateDTO> getValidates() {
        return validates;
    }

    public void setValidates(List<ValidateDTO> validates) {
        this.validates = validates;
    }
}
