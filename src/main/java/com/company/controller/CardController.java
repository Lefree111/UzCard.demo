package com.company.controller;

import com.company.dto.CardDTO;
import com.company.dto.ClientCardDTO;
import com.company.dto.ClientDTO;
import com.company.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
//@Api(tags = "card")
public class CardController {

    @Autowired
    private CardService cardService;

    //    @ApiOperation(value = "Post", notes = "Bu method faqat card yaratish uchun ishlatiladi clientga bog'lanmagan holda yaratiladi")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardDTO dto) {
        return ResponseEntity.ok(cardService.create(dto));
    }

    //    @ApiOperation(value = "Post",notes = "kartaga clientni ulash uchun ishlatiladi")
    @PostMapping("/card_vs_client_ulanish")
    public ResponseEntity<?> cardga_clientni_ulash(@RequestBody ClientCardDTO dto) {
        return ResponseEntity.ok(cardService.cardga_clientni_ulash(dto));
    }

    @PutMapping("/updatestatusCard/{cardNumber}")
    public ResponseEntity<?> updateStatusCard(@PathVariable("cardNumber") String cardNumber){
        return ResponseEntity.ok(cardService.updateStatusCard(cardNumber));
    }

    @GetMapping("/getCardListByPhone/{phone}")
    public ResponseEntity<?> getCardListByPhone(@PathVariable("phone") String phone) {
        return ResponseEntity.ok(cardService.getCardListByPhone(phone));
    }

    @GetMapping("/getCardListByClientId/{clientId}")
    public ResponseEntity<?> getCardListByClientId(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(cardService.getCardListByClientId(clientId));
    }

    @GetMapping("/getCardByNumber/{number}")
    public ResponseEntity<?> getCardByNumber(@PathVariable("number") String number){
        return ResponseEntity.ok(cardService.getCardByNumber(number));
    }

    @GetMapping("/getCardBalanceByNumber/{number}")
    public ResponseEntity<?> getCardBalanceByNumber(@PathVariable("number") String number){
        return ResponseEntity.ok(cardService.getCardBalanceByNumber(number));
    }



}