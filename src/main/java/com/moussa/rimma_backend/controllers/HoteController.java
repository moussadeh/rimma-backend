package com.moussa.rimma_backend.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "HÔTE", description = "Gestion des actions que peut effectuer un hôte (personne qui reserve des annonces)")
@RestController
@RequestMapping("/rimma/api/hote")
public class HoteController {


}
