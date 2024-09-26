package com.example.celulares.controller;

import java.util.Map;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.celulares.model.*;
import com.example.celulares.service.*;

@RestController
@RequestMapping("/api/celulares")
public class CelularController {

    @Autowired
    private CelularService celularService;

    // Obtener todos los celulares
    @GetMapping("/")
    public List<Celular> getAllCelulares() {
        return celularService.getAllCelulares();
    }

    // Obtener un celular por ID
    @GetMapping("/{id}")
    public ResponseEntity<Celular> getCelularById(@PathVariable Long id) {
        Optional<Celular> celular = celularService.getCelularById(id);
        return celular.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

 // Crear uno o más celulares
    @PostMapping("/")
    public List<Celular> createCelulares(@RequestBody List<Celular> celulares) {
        return celularService.saveAllCelulares(celulares);
    }


    // Actualizar un celular existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCelular(@PathVariable Long id, @RequestBody Celular celularDetails) {
        Optional<Celular> celular = celularService.getCelularById(id);
        if (celular.isPresent()) {
            Celular updatedCelular = celular.get();
            updatedCelular.setProcesador(celularDetails.getProcesador());
            updatedCelular.setMarca(celularDetails.getMarca());
            updatedCelular.setTipoPantalla(celularDetails.getTipoPantalla());
            updatedCelular.setRam(celularDetails.getRam());
            updatedCelular.setModelo(celularDetails.getModelo());
            updatedCelular.setCantidadCamaras(celularDetails.getCantidadCamaras());
            updatedCelular.setBateria(celularDetails.getBateria());
            updatedCelular.setOS(celularDetails.getOS());
            celularService.saveCelular(updatedCelular);
            return ResponseEntity.ok(updatedCelular);
        } else {
        	return ResponseEntity.status(404).body("El celular con el ID " + id + " no existe.");
        }
    }
 // Método para verificar si se están intentando actualizar campos inválidos
    private void checkInvalidFields(Map<String, Object> fields) throws IllegalArgumentException {
        // Lista de campos válidos en la tabla "celulares"
        List<String> validFields = List.of("procesador", "marca", "tipoPantalla", "ram", "modelo", "cantidadCamaras", "bateria", "os");

        for (String field : fields.keySet()) {
            if (!validFields.contains(field)) {
                throw new IllegalArgumentException("La columna '" + field + "' no existe en la tabla celulares.");
            }
        }
    }
 // Actualizar parcialmente un celular existente
    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateCelular(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Optional<Celular> celular = celularService.getCelularById(id);

        if (celular.isPresent()) {
            try {
                // Verifica si hay campos inválidos antes de actualizar
                checkInvalidFields(fields);
            } catch (IllegalArgumentException e) {
                // Si se encuentra un campo inválido, retorna un error 400 con el mensaje
                return ResponseEntity.status(400).body(e.getMessage());
            }

            Celular existingCelular = celular.get();

            // Actualiza solo los campos válidos
            fields.forEach((key, value) -> {
                switch (key) {
                    case "procesador":
                        existingCelular.setProcesador((String) value);
                        break;
                    case "marca":
                        existingCelular.setMarca((String) value);
                        break;
                    case "tipoPantalla":
                        existingCelular.setTipoPantalla((String) value);
                        break;
                    case "ram":
                        existingCelular.setRam((Integer) value);
                        break;
                    case "modelo":
                        existingCelular.setModelo((String) value);
                        break;
                    case "cantidadCamaras":
                        existingCelular.setCantidadCamaras((Integer) value);
                        break;
                    case "bateria":
                        existingCelular.setBateria((Integer) value);
                        break;
                    case "os":
                        existingCelular.setOS((String) value);
                        break;
                }
            });

            // Guarda los cambios parciales
            celularService.saveCelular(existingCelular);
            return ResponseEntity.ok(existingCelular);
        } else {
            // Si no se encuentra el celular, retorna 404
            return ResponseEntity.status(404).body("El celular con el ID " + id + " no existe.");
        }
    }


    // Eliminar un celular
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCelular(@PathVariable Long id) {
        if (celularService.getCelularById(id).isPresent()) {
            celularService.deleteCelular(id);
            return ResponseEntity.noContent().build();
        } else {
        	return ResponseEntity.status(404).body("El celular con el ID " + id + " no existe.");
        }
    }
}
