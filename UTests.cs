using System;
using NUnit.Framework;
using JsonUserVariable;
using Npgsql;
using NpgsqlTypes;
using PgsqlDriver;
using System.Collections.Generic;

namespace UTests
{
    [TestFixture]
    public class Tests
    {

        /// <summary>
        /// Test to see how the database reacts with null values inserted
        /// </summary>
        
        [Test]
        public void JsonNull()
        {
            /// <remarks>
            /// Since data is often missing all fields are initialized to null if they do not have another value;
            /// </remarks>
            Json311 test = new Json311();
            List<Json311> JList = new List<Json311>();
            JList.Add(test);
            SqlConnect DBTest = new SqlConnect();
            /// <remarks>
            /// Uses a test user who has only insert and select privelege in testtable
            /// </remarks>
            String connString = DBTest.Connect("test", "test", false);
            NpgsqlConnection conn = new NpgsqlConnection(connString);
            conn.Open();
            /// <remarks>
            /// Check The connection
            /// </remarks>
            Assert.AreEqual(conn.State, System.Data.ConnectionState.Open);
            
            /// <remarks>
            /// Test the import, making sure the data is added to the right table
            /// and our date is not updated
            /// </remarks>
            
            DBTest.Import(JList,connString,"testtable",false);
            using (NpgsqlCommand checkValue = new NpgsqlCommand("SELECT * FROM testtable", conn))
            using (NpgsqlDataReader reader = checkValue.ExecuteReader())
            {
                while (reader.Read())
                {
                    /// <remarks>
                    /// Since the column is empty, it will throw an invalid cast exception when 
                    /// attemption to convert it to a string 
                    /// </remarks>
                    Assert.Throws<System.InvalidCastException>(() => { reader.GetString(0); });

                }
            }
            conn.Close();
            
        }
        
        
    


        /// <summary>
        /// test null password,
        /// because the function will assing an empty string by default 
        /// it will not throw a null object exception in testing
        /// </summary>
        [Test]
        public void NullCredentials()
        {
            SqlConnect DBTest = new SqlConnect();
            Assert.Throws<System.NullReferenceException>( () => { DBTest.Connect("test", null, false); });
            Assert.Throws<System.NullReferenceException>(() => { DBTest.Connect(null, null, false); });
            Assert.Throws<Npgsql.PostgresException>(() => { DBTest.Connect(null, "test", false); });

        }

        /// <summary>
        /// Test for empty and wrong user/password pair
        /// </summary>
        [Test]
        public void FakeCredentials()
        {
            SqlConnect DBTest = new SqlConnect();
            Assert.Throws<Npgsql.PostgresException>(() => { DBTest.Connect("test", "tes", false); });
            Assert.Throws<Npgsql.NpgsqlException>(() => { DBTest.Connect("test", "", false); });
            Assert.Throws<Npgsql.PostgresException>(() => { DBTest.Connect("tes", "tes", false); });
            Assert.Throws<Npgsql.PostgresException>(() => { DBTest.Connect("", "tes", false); });

        }

        /// <summary>
        /// The test user does not have permission for any table other
        /// than the test table so we test to make sure that 
        /// the database will not 
        /// </summary>
        [Test]
        public void CheckPermissions()
        {
            SqlConnect DBTest = new SqlConnect();
            String connString = DBTest.Connect("test", "test", false);
            Assert.Throws<Npgsql.PostgresException>(() => { DBTest.CheckDate(connString); });
        }
    }
}