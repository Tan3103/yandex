package com.tannur.yandex.controllers;

import com.tannur.yandex.models.Citizen;
import com.tannur.yandex.models.CitizenDTO;
import com.tannur.yandex.services.CitizenService;
import com.tannur.yandex.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imports")
public class CitizenController {
    private final CitizenService citizenService;
    private final ModelMapper modelMapper;
    private static int n = 1;

    @Autowired
    public CitizenController(CitizenService citizenService, ModelMapper modelMapper) {
        this.citizenService = citizenService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<CitizenDTO> getPeople(){
        return citizenService.findAll().stream().map(this::convertToCitizenDTO).collect(Collectors.toList()); //Jackson конвертирует эти объекты в JSON
    }

//    @GetMapping("/{id}")
//    public PersonDTO getPerson(@PathVariable("id") int id){
//        return convertToPersonDTO(peopleService.findOne(id)); //Jackson конвертирует в JSON
//    }

    @PostMapping
    public ResponseEntity<Object> createCitizens(@RequestBody List<CitizenDTO> citizenDTOs) {
        try {

            for (CitizenDTO citizenDTO : citizenDTOs) {
                citizenService.save(convertToCitizen(citizenDTO), n);
            }

            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("import_id", n);
            n++;
            SuccessResponse successResponse = new SuccessResponse(HttpStatus.CREATED.value(), map);

            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error saving citizens");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @ExceptionHandler
    private ResponseEntity<CitizenErrorResponse> handleException(CitizenNotFoundException e){
        CitizenErrorResponse response = new CitizenErrorResponse(
                "Person with this id wasn't found!",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<CitizenErrorResponse> handleException(CitizenNotCreateException e){
        CitizenErrorResponse response = new CitizenErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Citizen convertToCitizen(CitizenDTO citizenDTO) {
        return modelMapper.map(citizenDTO, Citizen.class);
    }

    private CitizenDTO convertToCitizenDTO(Citizen citizen) {
        return modelMapper.map(citizen, CitizenDTO.class);
    }
}