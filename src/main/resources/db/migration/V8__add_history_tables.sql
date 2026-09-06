CREATE TABLE train_runs (
    id BIGSERIAL PRIMARY KEY,
    train_no VARCHAR(20) NOT NULL,
    start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    end_time TIMESTAMP WITH TIME ZONE,
    final_delay_minutes DOUBLE PRECISION,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE gps_history (
    id BIGSERIAL PRIMARY KEY,
    train_no VARCHAR(20) NOT NULL,
    run_id BIGINT NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    speed_kmh DOUBLE PRECISION NOT NULL,
    timestamp TIMESTAMP WITH TIME ZONE NOT NULL,
    delay_type VARCHAR(50)
);

CREATE TABLE station_arrival_history (
    id BIGSERIAL PRIMARY KEY,
    train_no VARCHAR(20) NOT NULL,
    station_code VARCHAR(10) NOT NULL,
    scheduled_arrival TIMESTAMP WITH TIME ZONE,
    actual_arrival TIMESTAMP WITH TIME ZONE NOT NULL,
    delay_minutes DOUBLE PRECISION NOT NULL,
    run_id BIGINT NOT NULL
);
