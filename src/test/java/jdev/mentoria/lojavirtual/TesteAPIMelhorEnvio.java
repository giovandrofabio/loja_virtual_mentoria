package jdev.mentoria.lojavirtual;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.mentoria.lojavirtual.dto.EmpresaTransporteDTO;
import jdev.mentoria.lojavirtual.enums.ApiTokenIntegracao;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TesteAPIMelhorEnvio {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"service\":0,\"agency\":0,\"from\":{\"name\":\"string\",\"phone\":\"string\",\"email\":\"string\",\"document\":\"string\",\"company_document\":\"string\",\"state_register\":\"string\",\"address\":\"string\",\"complement\":\"string\",\"number\":\"string\",\"district\":\"string\",\"city\":\"string\",\"country_id\":\"string\",\"postal_code\":\"string\",\"state_abbr\":\"string\",\"note\":\"string\"},\"to\":{\"name\":\"string\",\"phone\":\"string\",\"email\":\"string\",\"document\":\"string\",\"company_document\":\"string\",\"state_register\":\"string\",\"address\":\"string\",\"complement\":\"string\",\"number\":\"string\",\"district\":\"string\",\"city\":\"string\",\"country_id\":\"string\",\"postal_code\":\"string\",\"state_abbr\":\"string\",\"note\":\"string\"},\"products\":[{\"name\":\"string\",\"quantity\":\"string\",\"unitary_value\":\"string\"}],\"volumes\":[{\"height\":0,\"width\":0,\"length\":0,\"weight\":0}],\"options\":[{\"insurance_value\":0,\"receipt\":true,\"own_hand\":true,\"reverse\":true,\"non_commercial\":true,\"invoice\":{\"key\":\"string\"},\"plataform\":\"string\",\"tags\":{\"tag\":\"string\",\"Url\":\"string\"}}]}");
        Request request = new Request.Builder()
                .url(ApiTokenIntegracao.URL_MELHOR_ENVIO_SAND_BOX+"api/v2/me/cart")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SAND_BOX_1)
                .addHeader("User-Agent", "giovandrofabiosantos@hotmail.com")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());

    }
}
