package com.energycommunity.energy_api.controller;
import com.energycommunity.energy_api.model.EnergyData;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endTime = LocalDateTime.parse(end, formatter);

        List<EnergyData> mockData = List.of(
                new EnergyData(LocalDateTime.parse("2025-01-10T10:00:00", formatter), 10, 9, 1),
                new EnergyData(LocalDateTime.parse("2025-01-10T11:00:00", formatter), 12, 10, 2),
                new EnergyData(LocalDateTime.parse("2025-01-10T12:00:00", formatter), 14, 13, 1),
                new EnergyData(LocalDateTime.parse("2025-01-10T13:00:00", formatter), 18, 17, 1),
                new EnergyData(LocalDateTime.parse("2025-01-10T14:00:00", formatter), 20, 18, 2)
        );

        return mockData.stream()
                .filter(d -> !d.getHour().isBefore(startTime) && !d.getHour().isAfter(endTime))
                .toList();
    }



}
