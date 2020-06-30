using System;
using Newtonsoft.Json.Linq;
using System.Collections.Generic;

namespace Json311
{

	
        /// <summary>
        /// Used to hold the Data from API and move it to our PostgresDB
        /// </summary>
    class Json311
    {
        public string Unique_key { get; set; } = null;

        /// <summary>
        /// The Question mark after DateTime makes it nullable and allows us to 
        /// assign it a startig value of null
        /// </summary>
        public DateTime? Created_date { get; set; } = null;
        public DateTime? Closed_date { get; set; } = null;
        public string Agency_name { get; set; } = null;
        public string Complaint_type { get; set; } = null;
        public string Descriptor { get; set; } = null;
        public string Location_type { get; set; } = null;
        public string Incident_zip { get; set; } = null;
        public string Incident_address { get; set; } = null;
        public string Street_name { get; set; } = null;
        public string Cross_street_1 { get; set; } = null;
        public string Cross_street_2 { get; set; } = null;
        public string Intersection_street_1 { get; set; } = null;
        public string Intersection_street_2 { get; set; } = null;
        public string Address_type { get; set; } = null;
        public string City { get; set; } = null;
        public string Landmark { get; set; } = null;
        public string Facility_type { get; set; } = null;
        public string Status { get; set; } = null;
        public DateTime? Due_date { get; set; } = null;
        public string Resolution_description { get; set; } = null;
        public DateTime? Resolution_action_updated_date { get; set; } = null;
        public string Community_board { get; set; } = null;
        public string Bbl { get; set; } = null;
        public string Borough { get; set; } = null;
        public string X_coordinate_state_plane { get; set; } = null;
        public string Y_coordinate_state_plane { get; set; } = null;
        public string Open_data_channel_type { get; set; } = null;
        public string Park_facility_name { get; set; } = null;
        public string Park_borough { get; set; } = null;
        public string Vehicle_type { get; set; } = null;
        public string Taxi_company_borough { get; set; } = null;
        public string Taxi_pick_up_location { get; set; } = null;
        public string Bridge_highway_name { get; set; } = null;
        public string Bridge_highway_direction { get; set; } = null;
        public string Road_ramp { get; set; } = null;
        public string Bridge_highway_segment { get; set; } = null;
        public string Latitude { get; set; } = null;
        public string Longitude { get; set; } = null;
        public string Location_city { get; set; } = null;

        /// <summary>
        /// need to figure out how to store this in postgres
        /// </summary>
        public Newtonsoft.Json.Linq.JObject Location { get; set; } = null;
        public string Location_zip { get; set; } = null;
        public string Location_state { get; set; } = null;

        /// <summary>
        /// An empty constructor
        /// </summary>
        public Json311() { }

        /// <summary>
        /// A constructor that recieves a dictionary and assigns the types to the object
        /// </summary>
        /// <param name="entry">A dictionary containing the data entry to create the object</param>
        public Json311(Dictionary<string, object> entry) { Setter(entry); }

        /// <summary>
        /// A helper method for the constructor
        /// </summary>
        /// <param name="entry">date recieved from constructor to instantiate the object</param>
        private void Setter(Dictionary<string, object> entry)
        {
            foreach (KeyValuePair<string, object> iterate in entry)
            {
                switch (iterate.Key.ToString())
                {
                    case "unique_key":
                        this.Unique_key = iterate.Value.ToString();
                        break;

                    case "created_date":
                        this.Created_date = Convert.ToDateTime(iterate.Value);
                        break;

                    case "closed_date":
                        this.Closed_date = Convert.ToDateTime(iterate.Value);
                        break;

                    case "agency_name":
                        this.Agency_name = iterate.Value.ToString();
                        break;

                    case "complaint_type":
                        this.Complaint_type = iterate.Value.ToString();
                        break;

                    case "descriptor":
                        this.Descriptor = iterate.Value.ToString();
                        break;

                    case "location_type":
                        this.Location_type = iterate.Value.ToString();
                        break;

                    case "incident_zip":
                        this.Incident_zip = iterate.Value.ToString();
                        break;

                    case "incident_address":
                        this.Incident_address = iterate.Value.ToString();
                        break;

                    case "street_name":
                        this.Street_name = iterate.Value.ToString();
                        break;

                    case "cross_street_1":
                        this.Cross_street_1 = iterate.Value.ToString();
                        break;

                    case "cross_street_2":
                        this.Cross_street_2 = iterate.Value.ToString();
                        break;

                    case "intersection_street_1":
                        this.Intersection_street_1 = iterate.Value.ToString();
                        break;

                    case "intersection_street_2":
                        this.Intersection_street_2 = iterate.Value.ToString();
                        break;

                    case "address_type":
                        this.Address_type = iterate.Value.ToString();
                        break;

                    case "city":
                        this.City = iterate.Value.ToString();
                        break;

                    case "landmark":
                        this.Landmark = iterate.Value.ToString();
                        break;

                    case "facility_type":
                        this.Facility_type = iterate.Value.ToString();
                        break;


                    case "status":
                        this.Status = iterate.Value.ToString();
                        break;

                    case "due_date":
                        this.Due_date = Convert.ToDateTime(iterate.Value);
                        break;

                    case "resolution_description":
                        this.Resolution_description = iterate.Value.ToString();
                        break;

                    case "resolution_action_updated_date":
                        this.Resolution_action_updated_date = Convert.ToDateTime(iterate.Value);
                        break;

                    case "community_board":
                        this.Community_board = iterate.Value.ToString();
                        break;

                    case "bbl":
                        this.Bbl = iterate.Value.ToString();
                        break;

                    case "borough":
                        this.Borough = iterate.Value.ToString();
                        break;

                    case "x_coordinate_state_plane":
                        this.X_coordinate_state_plane = iterate.Value.ToString();
                        break;

                    case "y_coordinate_state_plane":
                        this.Y_coordinate_state_plane = iterate.Value.ToString();
                        break;

                    case "open_data_channel_type":
                        this.Open_data_channel_type = iterate.Value.ToString();
                        break;

                    case "park_facility_name":
                        this.Park_facility_name = iterate.Value.ToString();
                        break;

                    case "park_borough":
                        this.Park_borough = iterate.Value.ToString();
                        break;

                    case "vehicle_type":
                        this.Vehicle_type = iterate.Value.ToString();
                        break;

                    case "taxi_company_borough":
                        this.Taxi_company_borough = iterate.Value.ToString();
                        break;

                    case "taxi_pick_up_location":
                        this.Taxi_pick_up_location = iterate.Value.ToString();
                        break;

                    case "bridge_highway_name":
                        this.Bridge_highway_name = iterate.Value.ToString();
                        break;

                    case "bridge_highway_direction":
                        this.Bridge_highway_direction = iterate.Value.ToString();
                        break;

                    case "road_ramp":
                        this.Road_ramp = iterate.Value.ToString();
                        break;

                    case "bridge_highway_segment":
                        this.Bridge_highway_segment = iterate.Value.ToString();
                        break;

                    case "latitude":
                        this.Latitude = iterate.Value.ToString();
                        break;

                    case "longitude":
                        this.Longitude = iterate.Value.ToString();
                        break;

                    case "location_city":
                        this.Location_city = iterate.Value.ToString();
                        break;

                    case "location":
                        this.Location = JObject.Parse(iterate.Value.ToString());
                        break;

                    case "location_zip":
                        this.Location_zip = iterate.Value.ToString();
                        break;

                    case "location_state":
                        this.Location_state = iterate.Value.ToString();
                        break;

                    /// <remarks>
                    /// Just in case data is formatted differently, test to make sure 
                    /// everything is working fine, default will be changed after
                    /// </remarks>
                    default:
                        Console.WriteLine("weird datatype: \"" + iterate.Key.ToString()) + "\"";
                        break;
                }
            }

        }
    }
}
	

