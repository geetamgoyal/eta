package com.railway.eta.train.dto;

import com.railway.eta.eta.EtaResponse;
import com.railway.eta.eta.TrainState;
import com.railway.eta.route.dto.RouteStationResponse;

import java.util.List;

public record TrainDashboardResponse(
        String trainNo,
        String name,
        String routeCode,
        boolean active,
        boolean live,
        TrainState liveState,
        EtaResponse eta,
        List<RouteStationResponse> routeStations
) {
}
