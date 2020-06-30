DROP TABLE IF EXISTS totalrequests;
DROP TABLE IF EXISTS keytable;
DROP TABLE IF EXISTS resolvedrequests;
DROP TABLE IF EXISTS unresolvedrequests;
DROP TABLE IF EXISTS timeorder;
DROP TABLE IF EXISTS geographiclocation;
DROP TABLE IF EXISTS searchbycase;
DROP TABLE IF EXISTS numberofcases;

SET client_encoding = 'LATIN1';

CREATE TABLE totalrequests (
    unique_key text,
    created_date timestamp,
    closed_date timestamp,
    agency text,
    agency_name text,
    complaint_type text,
    descriptor text,
    location_type text,
    incident_zip text,
    incident_address text,
    street_name text,
    cross_street_1 text,
    cross_street_2 text,
    intersection_steet_1 text,
    intersection_street_2 text,
    address_type text,
    city text,
    landmark text, 
    facility_type text,
    status text,
    due_date timestamp,
    resolution_description text,
    resolution_action_updated_date timestamp,
    community_board text,
    bbl text,
    borough text,
    x_coordinate_state_plane numeric,
    y_coordinate_state_plane numeric,
    open_data_channel_type text,
    park_facility_name text,
    park_borough text,
    vehicle_type text,
    taxi_company_borough text,
    taxi_pick_up_location text,
    bridge_highway_name text,
    bridge_highway_direction text,
    road_ramp text,
    bridge_highway_segment text,
    latitude numeric(10, 8),
    longitude numeric(10, 8)
);

\COPY totalrequests FROM './311_Service_Requests_from_03112019.csv' WITH (FORMAT csv, HEADER, DELIMITER ',');

CREATE TABLE keytable AS
    SELECT unique_key, created_date, closed_date, complaint_type, agency_name, incident_address, incident_zip, status
    FROM totalrequests
    ORDER BY created_date;
    
CREATE TABLE resolvedrequests AS
    SELECT unique_key, created_date, closed_date, complaint_type, agency_name, incident_address, incident_zip, status
    FROM totalrequests
    WHERE closed_date IS NOT NULL
    ORDER BY created_date;

CREATE TABLE unresolvedrequests AS
    SELECT unique_key, created_date, closed_date, complaint_type, agency_name, incident_address, incident_zip, status
    FROM totalrequests
    WHERE closed_date IS NULL
    ORDER BY created_date;

CREATE TABLE timeorder AS
    SELECT unique_key, created_date, closed_date, complaint_type, agency_name, incident_address, incident_zip, status
    FROM totalrequests
    ORDER BY created_date;
    
-- sample search by timeorder query
-- SELECT *
-- FROM timeorder
-- WHERE created_date BETWEEN '2019-03-11 15:38:00' AND '2019-03-11 16:00:00';
    
CREATE TABLE geographiclocation AS
   SELECT borough, unique_key, created_date, closed_date, complaint_type, agency_name, incident_address, incident_zip
    FROM totalrequests
    ORDER BY borough;
    
CREATE TABLE searchbycase AS
    SELECT complaint_type, unique_key, created_date, closed_date, agency_name, incident_address, incident_zip, status
    FROM totalrequests
    ORDER BY complaint_type;
    
CREATE TABLE numberofcases AS
    SELECT complaint_type, COUNT(*) AS num
    FROM totalrequests
    GROUP BY complaint_type
    ORDER BY num DESC;