package com.example.inicial1.controllers;

import com.example.inicial1.dtos.PersonaDto;
import com.example.inicial1.entities.Persona;
import com.example.inicial1.services.PersonaServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/personas")
 @CrossOrigin(origins = "*")

public class PersonaController {
 //  @Autowired
//PersonaServices servicio;
private PersonaServices personaServices;

public PersonaController(PersonaServices personaServices){
    this.personaServices = personaServices;
}



@GetMapping("")
 public ResponseEntity<?> getAll() {
     try {
         return ResponseEntity.status(HttpStatus.OK).body(personaServices.findAll());

     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. por favor intente mas tarde.\"}");

     }
 }
 @GetMapping("/{id}")
 public ResponseEntity <?> getOne(@PathVariable Long id) {
     try {
         return ResponseEntity.status(HttpStatus.OK).body(personaServices.findById(id));

     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. por favor intente mas tarde.\"}");

     }
 }
@PostMapping("")
 public ResponseEntity <?> save(@RequestBody Persona entity){
     try {
         return ResponseEntity.status(HttpStatus.OK).body(personaServices.save(entity));

     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. por favor intente mas tarde.\"}");

     }
 }
 @PutMapping("/{id}")
public ResponseEntity <?> update(Long id, Persona entity){
    try {
        return ResponseEntity.status(HttpStatus.OK).body(personaServices.update(id, entity));

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. por favor intente mas tarde.\"}");

    }
}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminé el registro" + id);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}