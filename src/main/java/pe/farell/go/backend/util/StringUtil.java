package pe.farell.go.backend.util;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtil {

    public static String concatenarCadenas(String delimiter, String... valores) {
        return Arrays.stream(valores)
                .filter(s -> !ObjectUtils.isEmpty(s))
                .collect(Collectors.joining(delimiter));
    }

}
