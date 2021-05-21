import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generate {


    public static void main(String[] args) {
        String filePath = "define_field.txt";
        try {

            List<ValidateFieldDTO> validateFieldDTOS = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String className = br.readLine();

            String fieldDefinition = null;
            while ((fieldDefinition = br.readLine()) != null) {
                String[] definitionField = fieldDefinition.split("\\|");

                String[] annnotations = definitionField[1].split(",");

                ValidateFieldDTO validateFieldDTO = new ValidateFieldDTO();
                validateFieldDTO.setFieldName(definitionField[0]);

                List<ValidateDTO> validateDTOS = new ArrayList<>();
                for(int i = 0; i < annnotations.length; i++) {
                    int instance = annnotations[i].contains("Size") ? 4 : 2;
                    validateDTOS.addAll(createValidateDTO(instance, className, annnotations[i]));
                }
                validateFieldDTO.setValidates(validateDTOS);
                validateFieldDTOS.add(validateFieldDTO);
            }
            br.close();
            GsonBuilder builder = new GsonBuilder().serializeNulls();
            Gson gson = builder.create();
            System.out.println(gson.toJson(validateFieldDTOS));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<ValidateDTO> createValidateDTO(int number, String nameMethod, String annotation) {
        List<ValidateDTO> validateDTOList = new ArrayList<>();

        String[] parseAnnotation = parseAnnotation(annotation);
        for(int i = 0; i < number; i++) {
            String prefix = "";
            if(number > 2) {
                prefix = (i <= 1) ? "Min" : "Max";
            }

            boolean valid = (i % 2 == 0);
            String validStr = valid ? "Valid" : "Invalid";
            String nameTestCase = nameMethod + "_" + parseAnnotation[0] + prefix + validStr + "_Exist" + parseAnnotation[0];

            ValidateDTO validateDTO = new ValidateDTO();
            validateDTO.setNameTestCase(nameTestCase);
            validateDTO.setValid(valid);
            validateDTO.setValidateType(parseAnnotation[0]);
            validateDTO.setMessageCode(parseAnnotation[1]);
            validateDTOList.add(validateDTO);
        }
        return validateDTOList;
    }

    public static String[] parseAnnotation(String annotation) {
        String[] params = new String[2];
        Pattern compile = Pattern.compile("(?<=\\()\\w+(?=\\))");
        String annotationName = annotation.substring(0, annotation.indexOf("("));

        params[0] = annotationName;
        Matcher matcher = compile.matcher(annotation);
        if(matcher.find()) {
            params[1] = matcher.group();
            return params;
        }
        return params;
    }
}
