package com.energycommunity.energy_api.controller;
import com.energycommunity.energy_api.model.EnergyData;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/energy")
public class EnergyController {

    @GetMapping("/current")
    public EnergyData getCurrent() {
        return new EnergyData(LocalDateTime.now().withMinute(0).withSecond(0), 20.0, 18.0, 2.0);
    }

    @GetMapping("/historical")
    public List<EnergyData> getHistorical(@RequestParam String start, @RequestParam String end) {
        return List.of(
                new EnergyData(LocalDateTime.now().minusHours(2), 15.0, 14.5, 1.2),
                new EnergyData(LocalDateTime.now().minusHours(1), 16.0, 16.0, 0.5)
        );
    }

}
