package nn.jung.lotto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nn.jung.lotto.repo.LtRandomDto;
import nn.jung.lotto.service.LtService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class LtController {

    private final LtService service;


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "히스토리 조회 성공", content = @Content(schema = @Schema(implementation = LtRandomDto.class)))
    })
    @Operation(summary = "역대 로또당첨번호 조회", description = "역대 로또당첨번호")
    @GetMapping("history")
    public LtRandomDto ltHistory(@Parameter(name = "times",description = "회차",example = "1022") String times) throws IOException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)  ip = req.getRemoteAddr();

        log.info("history Call");
        log.info("request Ip : {}",ip);

        return service.collectData(times);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "발번 성공", content = @Content(schema = @Schema(implementation = LtRandomDto.class)))
    })
    @Operation(summary = "번호 발번", description = "no1 ~ no6 번호발번")
    @GetMapping("draw")
    public LtRandomDto ltDraw() throws IOException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)  ip = req.getRemoteAddr();
        log.info("draw Call");
        log.info("request Ip : {}",ip);
        return service.drawLt();
    }

}
