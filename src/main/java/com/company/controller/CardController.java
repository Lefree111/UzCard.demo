package com.company.controller;

import com.company.dto.CardDTO;
import com.company.dto.CardFilterDTO;
import com.company.dto.ClientCardDTO;
import com.company.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/card")
@Api(tags = "card")
public class CardController {

    @Autowired
    private CardService cardService;

    @ApiOperation(value = "Post", notes = "Bu method faqat card yaratish uchun ishlatiladi clientga bog'lanmagan holda yaratiladi")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardDTO dto) {
        log.info("Authorization {} " + dto);
        return ResponseEntity.ok(cardService.create(dto));
    }

    @ApiOperation(value = "Post", notes = "kartaga clientni ulash uchun ishlatiladi")
    @PostMapping("/card_vs_client_ulanish")
    public ResponseEntity<?> cardga_clientni_ulash(@RequestBody ClientCardDTO dto) {
        return ResponseEntity.ok(cardService.cardga_clientni_ulash(dto));
    }

    @ApiOperation(value = "Post", notes = "kartaga clientni ulash uchun ishlatiladi")
    @PutMapping("/updatestatusCard/{cardNumber}")
    public ResponseEntity<?> updateStatusCard(@PathVariable("cardNumber") String cardNumber) {
        return ResponseEntity.ok(cardService.updateStatusCard(cardNumber));
    }

    @ApiOperation(value = "Post", notes = "kartaga clientni ulash uchun ishlatiladi")
    @GetMapping("/getCardListByPhone/{phone}")
    public ResponseEntity<?> getCardListByPhone(@PathVariable("phone") String phone) {
        return ResponseEntity.ok(cardService.getCardListByPhone(phone));
    }

    @ApiOperation(value = "Post", notes = "kartaga clientni ulash uchun ishlatiladi")
    @GetMapping("/getCardListByClientId/{clientId}")
    public ResponseEntity<?> getCardListByClientId(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(cardService.getCardListByClientId(clientId));
    }

    @ApiOperation(value = "Post", notes = "kartaga clientni ulash uchun ishlatiladi")
    @GetMapping("/getCardByNumber/{number}")
    public ResponseEntity<?> getCardByNumber(@PathVariable("number") String number) {
        return ResponseEntity.ok(cardService.getCardByNumber(number));
    }

    @ApiOperation(value = "Post", notes = "kartaga clientni ulash uchun ishlatiladi")
    @GetMapping("/getCardBalanceByNumber/{number}")
    public ResponseEntity<?> getCardBalanceByNumber(@PathVariable("number") String number) {
        return ResponseEntity.ok(cardService.getCardBalanceByNumber(number));
    }



    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody CardFilterDTO dto) {
        return ResponseEntity.ok(cardService.filter(dto));
    }


}