package nn.jung.lotto.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nn.jung.lotto.repo.LtDto;
import nn.jung.lotto.repo.LtRandomDto;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class LtService {


    public LtRandomDto collectData(String times) throws IOException {

        //http client
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // GET
        HttpGet httpGet = new HttpGet("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+times);


        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        log.info("---GET INFO--");
        log.info("responseStatus: {}",httpResponse.getStatusLine().getStatusCode());

        //바로못가져옴 버퍼로읽어야됨
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String line;
        StringBuilder sb = new StringBuilder();

        while((line =reader.readLine())!= null){
            sb.append(line);
        }

        log.info("data : {}",sb.toString());

        ObjectMapper objectMapper = new ObjectMapper();


//        LtDto result = objectMapper.readValue(sb.toString(), LtDto.class);

        Map<String,Object> result = objectMapper.readValue(sb.toString(),Map.class);



        httpClient.close();
        LtRandomDto dto;


        if(result.get("returnValue").equals("success")){
            dto = new LtRandomDto(result);
            return dto;
        }


        dto = new LtRandomDto();
        dto.setResultState(false);

        return dto;


    }


    public LtRandomDto drawLt(){
        List<Integer> drawList = new ArrayList<>();
        int randomVal;
        while(drawList.size() <6){
            randomVal = (int)(Math.random()*45 +1);

            if(!drawList.contains(randomVal)){
                drawList.add(randomVal);
            }
            drawList.sort(Comparator.naturalOrder());
        }
        return  new LtRandomDto(drawList);
    }
}
