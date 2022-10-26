package pe.farell.go.backend.util;

import org.springframework.util.ObjectUtils;
import pe.farell.go.backend.constant.EnumResponse;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;

import java.util.List;
import java.util.Objects;

public class ResponseUtil {

    public static <M> Response validateList(List<M> data, String msgCasoNull) {
        Response responseClient = new Response();
        if (ObjectUtils.isEmpty(data) || data.isEmpty()) {
            responseClient.setCode(EnumResponse.C002.getCode());
            responseClient.setMsg(Objects.isNull(msgCasoNull) ? EnumResponse.C002.getMsg() : msgCasoNull);
        } else {
            responseClient.setCode(EnumResponse.C001.getCode());
            responseClient.setMsg(EnumResponse.C001.getMsg());
        }
        Content content = new Content();
        content.setContent(data);

        responseClient.setData(content);
        return responseClient;
    }

    public static <M> Response<M> validateObj(M data, String msgCasoNull) {
        Response<M> responseClient = new Response();
        if (ObjectUtils.isEmpty(data)) {
            responseClient.setCode(EnumResponse.C002.getCode());
            responseClient.setMsg(Objects.isNull(msgCasoNull) ? EnumResponse.C002.getMsg() : msgCasoNull);
        } else {
            responseClient.setCode(EnumResponse.C001.getCode());
            responseClient.setMsg(EnumResponse.C001.getMsg());
        }
        responseClient.setData(data);
        return responseClient;
    }

}
