package com.company.controller;

import com.company.dto.ClientDTO;
import com.company.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/client")
//@Api(tags = "client")
public class ClientController {
    @Autowired
    private ClientService clientService;


    //    @ApiOperation(value = "Post", notes = "Bu method client yaratish uchun ishlatiladi")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ClientDTO dto) {
        log.info("Authorization {} "+dto);
        return ResponseEntity.ok(clientService.create(dto));
    }

    //    @ApiOperation(value = "Put", notes = "Bu method yaratilgan clientni malumotlarini o'zgartirish uchun ishlatiladi")
    @PutMapping("/update/{phone}")
    public ResponseEntity<?> update(@PathVariable("phone") String phone, @RequestBody ClientDTO dto) {
        log.info("Authorization {} "+dto);
        return ResponseEntity.ok(clientService.update(phone, dto));
    }

    //    @ApiOperation(value = "Put", notes = "Bu method clientni statusini ACTIVE  qilish uchun ishlatiladi")
    @PutMapping("/updateStatus/{phone}")
    public ResponseEntity<?> updateStatus(@PathVariable("phone") String phone) {
        return ResponseEntity.ok(clientService.updateStatus(phone));
    }

    //    @ApiOperation(value = "Put", notes = "Bu method clientni phonesini yangilash uchun ishlatiladi")
    @PutMapping("/updatePhone/{phone}")
    public ResponseEntity<?> updatePhone(@PathVariable("phone") String phone, @RequestBody ClientDTO updatePhone) {
        log.info("Authorization {} "+updatePhone);
        return ResponseEntity.ok(clientService.updatePhone(phone, updatePhone));
    }

    //    @ApiOperation(value = "Get", notes = "Bu method clientlarni yaratilgan vaqti bo'yicha limit 5 tadan olib beradi")
    @GetMapping("/pagination")
    public ResponseEntity<?> getProfileList(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(clientService.paginationList(page, size));
    }

    //    @ApiOperation(value = "Get", notes = "Bu method telefon raamkinga tegishli ekanligini bilib beradi")
    @GetMapping("/getById/{phone}/{profileName}")
    public ResponseEntity<?> getById(@PathVariable("phone") String phone,@PathVariable("profileName") String profileName) {
        return ResponseEntity.ok(clientService.getById(phone,profileName));
    }

    //    @ApiOperation(value = "Get", notes = "Bu method clientni profileName yani u qaysi ilovadan foydalanadi " +
//            "click mi yoki payme Humand Apelsin shunga o'xshaganlarni yani bir xillarini topib beradi")
    @GetMapping("/AllClient_viz_profileName/{profileName}")
    public ResponseEntity<?> AllClient_viz_profileName(@PathVariable("profileName") String profileName) {
        return ResponseEntity.ok(clientService.AllClient_viz_profileName(profileName));
    }
}
