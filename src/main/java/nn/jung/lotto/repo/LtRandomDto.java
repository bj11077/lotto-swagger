package nn.jung.lotto.repo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LtRandomDto {
    
    @ApiModelProperty(value = "추첨6번",dataType = "int", example = "1")
    private int drwtNo6;
    @ApiModelProperty(value = "추첨5번",dataType = "int", example = "2")
    private int drwtNo5;
    @ApiModelProperty(value = "추첨4번",dataType = "int", example = "3")
    private int drwtNo4;
    @ApiModelProperty(value = "추첨3번",dataType = "int", example = "4")
    private int drwtNo3;
    @ApiModelProperty(value = "추첨2번",dataType = "int", example = "5")
    private int drwtNo2;
    @ApiModelProperty(value = "추첨1번",dataType = "int", example = "6")
    private int drwtNo1;
    @ApiModelProperty(value = "보너스번호",dataType = "int", example = "7")
    private int bnsNo;
    @ApiModelProperty(value = "정상동작여부",dataType = "boolean", example = "true")
    private boolean resultState;


    public LtRandomDto(List<Integer> list){
        this.drwtNo1 = list.get(0);
        this.drwtNo2 = list.get(1);
        this.drwtNo3 = list.get(2);
        this.drwtNo4 = list.get(3);
        this.drwtNo5 = list.get(4);
        this.drwtNo6 = list.get(5);
        this.resultState = true;

    }

    public LtRandomDto(Map<String,Object> map){
        this.drwtNo1 = (int)(map.get("drwtNo1"));
        this.drwtNo2 = (int)(map.get("drwtNo2"));
        this.drwtNo3 = (int)(map.get("drwtNo3"));
        this.drwtNo4 = (int)(map.get("drwtNo4"));
        this.drwtNo5 = (int)(map.get("drwtNo5"));
        this.drwtNo6 = (int)(map.get("drwtNo6"));
        this.bnsNo = (int)(map.get("bnusNo"));
        this.resultState = true;

    }


}
