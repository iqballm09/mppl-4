const sequelize = require("./database/connection");
const bodyParser = require("body-parser");
const routes = require("./routes");
const express = require("express");

const app = express();
app.use(bodyParser.json());

app.use("/api", routes);

// Testing connection
app.listen(process.env.PORT || 5000, async () => {
    try {
        await sequelize.authenticate();
        console.log("Database has been connected!");
    } catch (error) {
        console.error("Unable to connect to the database:", error);
    }
});

