package com.railway.eta.train;

import com.railway.eta.eta.EtaCalculationService;
import com.railway.eta.eta.EtaResponse;
import com.railway.eta.eta.TrainState;
import com.railway.eta.eta.TrainStateService;
import com.railway.eta.route.RouteStationService;
import com.railway.eta.route.dto.RouteStationResponse;
import com.railway.eta.train.dto.TrainDashboardResponse;
import com.railway.eta.train.dto.TrainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
@RequiredArgsConstructor
public class TrainDashboardController {

    private final TrainService trainService;
    private final TrainStateService trainStateService;
    private final EtaCalculationService etaCalculationService;
    private final RouteStationService routeStationService;

    @GetMapping("/{trainNo}/dashboard")
    public TrainDashboardResponse getTrainDashboard(@PathVariable String trainNo) {
        TrainResponse train = trainService.getTrain(trainNo);

        List<RouteStationResponse> stations = Collections.emptyList();
        if (train.routeCode() != null) {
            stations = routeStationService.getRouteStations(train.routeCode());
        }

        TrainState liveState = trainStateService.get(trainNo);
        EtaResponse eta = null;
        boolean isLive = liveState != null;

        if (isLive) {
            try {
                eta = etaCalculationService.calculateEta(trainNo);
            } catch (Exception e) {
                // Fallback: If calculation service fails (e.g. 0 speed), return null or partial
                eta = null;
            }
        }

        return new TrainDashboardResponse(
                train.trainNo(),
                train.name(),
                train.routeCode(),
                train.active(),
                isLive,
                liveState,
                eta,
                stations
        );
    }
}
